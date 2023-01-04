package team.asd.service;

import com.antkorwin.xsync.XSync;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.asd.constants.PaymentNumber;
import team.asd.dao.PartyDao;
import team.asd.dao.PaymentTransactionDao;
import team.asd.dao.ProductDao;
import team.asd.dao.PropertyManagerInfoDao;
import team.asd.dto.PartyProductTransactionDto;
import team.asd.entity.Party;
import team.asd.entity.PartyReport;
import team.asd.entity.PropertyManagerInfo;
import team.asd.exceptions.ValidationException;
import team.asd.util.ValidationUtil;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class PartyService {
	@Autowired
	private XSync<Integer> intXSync;
	private final PartyDao partyDao;
	private final PropertyManagerInfoDao propertyManagerInfoDao;
	private final ProductDao productDao;
	private final PaymentTransactionDao paymentTransactionDao;

	@Autowired
	public PartyService(PartyDao partyDao, PropertyManagerInfoDao propertyManagerInfoDao, ProductDao productDao, PaymentTransactionDao paymentTransactionDao) {
		this.partyDao = partyDao;
		this.propertyManagerInfoDao = propertyManagerInfoDao;
		this.productDao = productDao;
		this.paymentTransactionDao = paymentTransactionDao;
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
		partyDao.create(party);
	}

	public void update(Party party) {
		if (ValidationUtil.isWrongUpdateObject(party)) {
			throw new ValidationException("Wrong Party object was provided");
		}
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
}
