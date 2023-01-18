package team.asd.service;

import com.antkorwin.xsync.XSync;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.asd.constants.PaymentNumber;
import team.asd.dao.ManagerToChannelDao;
import team.asd.dao.PartyDao;
import team.asd.dao.PaymentTransactionDao;
import team.asd.dao.ProductDao;
import team.asd.dao.PropertyManagerInfoDao;
import team.asd.dto.PartyProductTransactionDto;
import team.asd.entity.ManagerToChannel;
import team.asd.entity.Party;
import team.asd.entity.PartyReport;
import team.asd.entity.PropertyManagerInfo;
import team.asd.exceptions.ValidationException;
import team.asd.util.ValidationUtil;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class PartyService {
	private static final Long AUTH_EXPIRE = 3600L;
	private static final String AUTH_USER_TYPE = "PropertyManager";

	@Autowired
	private XSync<Integer> intXSync;
	@Autowired
	private XSync<String> stringXSync;
	private final PartyDao partyDao;
	private final PropertyManagerInfoDao propertyManagerInfoDao;
	private final ProductDao productDao;
	private final PaymentTransactionDao paymentTransactionDao;
	private final ManagerToChannelDao managerToChannelDao;
	private final RedisClient redisClient;

	@Autowired
	public PartyService(PartyDao partyDao, PropertyManagerInfoDao propertyManagerInfoDao, ProductDao productDao, PaymentTransactionDao paymentTransactionDao,
			ManagerToChannelDao managerToChannelDao, RedisClient redisClient) {
		this.partyDao = partyDao;
		this.propertyManagerInfoDao = propertyManagerInfoDao;
		this.productDao = productDao;
		this.paymentTransactionDao = paymentTransactionDao;
		this.managerToChannelDao = managerToChannelDao;
		this.redisClient = redisClient;
	}

	public Party readById(Integer id) {
		if (ValidationUtil.isWrongId(id)) {
			throw new ValidationException("Wrong id was provided");
		}
		return partyDao.readById(id);
	}

	public List<Party> readByUserTypeNameState(String userType, String name, String state) {
		if (StringUtils.isBlank(name)) {
			throw new ValidationException("Required parameter name was not provided");
		}
		return partyDao.readByUserTypeNameState(userType, name, state);
	}

	public List<Party> readByEmailUserTypeNameState(String emailAddress, String userType, String name, String state) {
		if (StringUtils.isBlank(emailAddress)) {
			throw new ValidationException("Required parameter email address was not provided");
		}
		return partyDao.readByEmailUserTypeNameState(emailAddress, userType, name, state);
	}

	public List<Party> readByChannelMaskUserType(String mask, String userType) {
		if (StringUtils.isAnyBlank(mask, userType)) {
			throw new ValidationException("Required parameters were not provided");
		}
		return partyDao.readByChannelMaskUserType(mask, userType);
	}

	public PartyReport readReportById(Integer id) {
		if (ValidationUtil.isWrongId(id)) {
			throw new ValidationException("Wrong id was provided");
		}
		return partyDao.readReportById(id);
	}

	public List<PartyReport> readReportByPageItems(Integer page, Integer itemsPerPage) {
		if (ObjectUtils.anyNull(page, itemsPerPage) || page < 1 || itemsPerPage < 1) {
			throw new ValidationException("Wrong parameters were provided");
		}
		return partyDao.readReportByPageItems(page, itemsPerPage);
	}

	public PartyProductTransactionDto readPartyProductTransactionInfoById(Integer id) {
		if (ValidationUtil.isWrongId(id)) {
			throw new ValidationException("Wrong id was provided");
		}
		Party party = partyDao.readById(id);
		if (party == null) {
			return null;
		}
		Optional<PropertyManagerInfo> propertyManagerInfo = Optional.ofNullable(propertyManagerInfoDao.readByPmIdState(id, null));
		return PartyProductTransactionDto.builder()
				.partyId(party.getId())
				.partyName(party.getName())
				.numberOfPayment(propertyManagerInfo.map(PropertyManagerInfo::getNumberOfPayments)
						.map(PaymentNumber::getIntegerValue)
						.orElse(null))
				.commissionAmount(propertyManagerInfo.map(PropertyManagerInfo::getCommission)
						.orElse(null))
				.productsCountBySupplier(productDao.readProductsByParams(id, null, null)
						.size())
				.paymentTransactionCountByPartner(paymentTransactionDao.readByChargeTypePartnerIdFundsHolderStatus(null, id, null, null)
						.size())
				.build();
	}

	public void create(Party party) {
		if (ValidationUtil.isWrongRequiredFields(party)) {
			throw new ValidationException("Wrong Party object was provided");
		}
		party.setPassword(encodePassword(party.getPassword()));
		partyDao.create(party);
	}

	public void update(Party party) {
		if (ValidationUtil.isWrongUpdateObject(party)) {
			throw new ValidationException("Wrong Party object was provided");
		}
		party.setPassword(encodePassword(party.getPassword()));
		partyDao.update(party);
	}

	public void updateWithDelay(Party party) {
		if (ValidationUtil.isWrongUpdateObject(party)) {
			throw new ValidationException("Wrong Party object was provided");
		}
		intXSync.execute(party.getId(), () -> {
			ExecutorService executorService = Executors.newSingleThreadExecutor();
			executorService.execute(() -> partyDao.updateWithDelay(party));
		});
	}

	public void delete(Integer id) {
		if (ValidationUtil.isWrongId(id)) {
			throw new ValidationException("Wrong id was provided");
		}
		partyDao.deleteById(id);
	}

	public String auth(String email, String password, Integer channelPartnerId) {
		if (ObjectUtils.anyNull(email, password, channelPartnerId)) {
			throw new ValidationException("Wrong auth parameters were provided");
		}

		Party party = partyDao.readByEmailUserTypeNameState(email, AUTH_USER_TYPE, null, null)
				.stream()
				.findFirst()
				.orElse(null);

		if (party == null || !StringUtils.equals(encodePassword(password), party.getPassword())) {
			throw new ValidationException("Incorrect email or password");
		}

		return getOrGenerateToken(party.getId(), channelPartnerId);
	}

	private String getOrGenerateToken(Integer partyId, Integer channelPartnerId) {
		AtomicReference<String> token = new AtomicReference<>();
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		executorService.submit(() -> stringXSync.execute(partyId + " " + channelPartnerId, () -> {
			token.getAndSet(redisClient.retrieveValueFromHashMap("auth_token:" + partyId, channelPartnerId.toString()));
			if (token.get() == null && isRelation(partyId, channelPartnerId)) {
				token.getAndSet(UUID.randomUUID()
						.toString());
				redisClient.saveValueInHashMap("auth_token:" + partyId, channelPartnerId.toString(), token.get(), AUTH_EXPIRE);
			}
		}));
		awaitExecutorService(executorService);
		return token.get();
	}

	private void awaitExecutorService(ExecutorService executorService) {
		executorService.shutdown();
		try {
			if (!executorService.awaitTermination(20, TimeUnit.SECONDS)) {
				executorService.shutdownNow();
			}
		} catch (InterruptedException e) {
			executorService.shutdownNow();
			Thread.currentThread()
					.interrupt();
		}
	}

	private boolean isRelation(Integer partyId, Integer channelPartnerId) {
		Integer propertyManagerInfoId = Optional.ofNullable(propertyManagerInfoDao.readByPmIdState(partyId, null))
				.map(PropertyManagerInfo::getId)
				.orElse(null);
		ManagerToChannel managerToChannel = managerToChannelDao.readByPropManagerIdChanPartnerId(propertyManagerInfoId, channelPartnerId);
		return managerToChannel != null;
	}

	private String encodePassword(String password) {
		if (StringUtils.isBlank(password)) {
			return null;
		}
		return Base64.getEncoder()
				.encodeToString(password.getBytes());
	}
}
