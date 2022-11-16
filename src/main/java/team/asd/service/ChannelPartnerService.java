package team.asd.service;

import org.apache.commons.lang3.StringUtils;
import team.asd.dao.ChannelPartnerDao;
import team.asd.entity.ChannelPartner;
import team.asd.exceptions.ValidationException;

public class ChannelPartnerService {
	private final ChannelPartnerDao channelPartnerDao;

	public ChannelPartnerService(ChannelPartnerDao channelPartnerDao) {
		this.channelPartnerDao = channelPartnerDao;
	}

	public ChannelPartner readById(Integer id) {
		if (isWrongId(id)) {
			throw new ValidationException("Wrong id was provided");
		}
		return channelPartnerDao.readById(id);
	}

	public void create(ChannelPartner channelPartner) {
		if (channelPartner == null || isWrongId(channelPartner.getPartyId()) || StringUtils.isBlank(channelPartner.getAbbreviation()) || StringUtils.isBlank(
				channelPartner.getChannelName())) {
			throw new ValidationException("Wrong ChannelPartner object was provided");
		}
		channelPartnerDao.create(channelPartner);
	}

	public void update(ChannelPartner channelPartner) {
		if (channelPartner == null || isWrongId(channelPartner.getId())) {
			throw new ValidationException("Wrong ChannelPartner object was provided");
		}
		channelPartnerDao.update(channelPartner);
	}

	public void delete(Integer id) {
		if (isWrongId(id)) {
			throw new ValidationException("Wrong id was provided");
		}
		channelPartnerDao.deleteById(id);
	}

	private boolean isWrongId(Integer id) {
		return id == null || id <= 0;
	}
}
