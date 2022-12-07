package team.asd.service;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.asd.dao.ChannelPartnerDao;
import team.asd.dao.ManagerToChannelDao;
import team.asd.entity.ChannelPartner;
import team.asd.entity.ManagerToChannel;
import team.asd.exceptions.ValidationException;
import team.asd.util.ValidationUtil;

import java.util.List;

@Service
public class ChannelPartnerService {
	private final ChannelPartnerDao channelPartnerDao;
	private final ManagerToChannelDao managerToChannelDao;

	@Autowired
	public ChannelPartnerService(ChannelPartnerDao channelPartnerDao, ManagerToChannelDao managerToChannelDao) {
		this.channelPartnerDao = channelPartnerDao;
		this.managerToChannelDao = managerToChannelDao;
	}

	public ChannelPartner readById(Integer id) {
		if (ValidationUtil.isWrongId(id)) {
			throw new ValidationException("Wrong id was provided");
		}
		return channelPartnerDao.readById(id);
	}

	public List<ChannelPartner> readByAbbreviationMask(String abbreviationMask) {
		if (StringUtils.isBlank(abbreviationMask)) {
			throw new ValidationException("Wrong abbreviation was provided");
		}
		return channelPartnerDao.readByAbbreviationMask(abbreviationMask);
	}

	public ChannelPartner readByPartyIdState(Integer partyId, String state) {
		return channelPartnerDao.readByPartyIdState(partyId, state);
	}

	public ManagerToChannel readManagerByPropManagerIdChanPartnerId(Integer propertyManagerId, Integer channelPartnerId) {
		if (ObjectUtils.anyNull(propertyManagerId, channelPartnerId)) {
			throw new ValidationException("Required parameter was not provided");
		}
		return managerToChannelDao.readByPropManagerIdChanPartnerId(propertyManagerId, channelPartnerId);
	}

	public List<ManagerToChannel> readManagersByChannelPartnerIdNetRate(Integer channelPartnerId, Integer netRate) {
		if (ValidationUtil.isWrongId(channelPartnerId)) {
			throw new ValidationException("Wrong channel partner id was provided");
		}
		return managerToChannelDao.readByChannelPartnerIdNetRate(channelPartnerId, netRate);
	}

	public List<ManagerToChannel> readManagersToChannelsByFundsHolder(Integer fundsHolder) {
		if (fundsHolder == null || fundsHolder > 1 || fundsHolder < 0) {
			throw new ValidationException("Wrong funds holder parameter was provided");
		}
		return managerToChannelDao.readByFundsHolder(fundsHolder);
	}

	public void create(ChannelPartner channelPartner) {
		if (ValidationUtil.isWrongRequiredFields(channelPartner)) {
			throw new ValidationException("Wrong ChannelPartner object was provided");
		}
		channelPartnerDao.create(channelPartner);
	}

	public void createManagerToChannels(List<ManagerToChannel> managerToChannels) {
		if (CollectionUtils.isEmpty(managerToChannels)) {
			throw new ValidationException("Empty ManagerToChannel list was provided");
		}
		if (managerToChannels.stream()
				.anyMatch(ValidationUtil::isWrongRequiredFields)) {
			throw new ValidationException("Wrong ManagerToChannel object was provided");
		}
		managerToChannelDao.createList(managerToChannels);
	}

	public void update(ChannelPartner channelPartner) {
		if (ValidationUtil.isWrongUpdateObject(channelPartner)) {
			throw new ValidationException("Wrong ChannelPartner object was provided");
		}
		channelPartnerDao.update(channelPartner);
	}

	public void delete(Integer id) {
		if (ValidationUtil.isWrongId(id)) {
			throw new ValidationException("Wrong id was provided");
		}
		channelPartnerDao.deleteById(id);
	}
}
