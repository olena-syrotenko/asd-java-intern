package team.asd.service;

import team.asd.dao.ChannelPartnerDao;
import team.asd.entity.ChannelPartner;
import team.asd.exceptions.ValidationException;
import team.asd.util.ValidationUtil;

public class ChannelPartnerService {
	private final ChannelPartnerDao channelPartnerDao;

	public ChannelPartnerService(ChannelPartnerDao channelPartnerDao) {
		this.channelPartnerDao = channelPartnerDao;
	}

	public ChannelPartner readById(Integer id) {
		if (ValidationUtil.isWrongId(id)) {
			throw new ValidationException("Wrong id was provided");
		}
		return channelPartnerDao.readById(id);
	}

	public void create(ChannelPartner channelPartner) {
		if (ValidationUtil.isWrongRequiredFields(channelPartner)) {
			throw new ValidationException("Wrong ChannelPartner object was provided");
		}
		channelPartnerDao.create(channelPartner);
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
