package team.asd.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.asd.dao.ChannelPartnerDao;
import team.asd.entity.ChannelPartner;
import team.asd.exceptions.ValidationException;
import team.asd.util.ValidationUtil;

import java.util.List;

@Service
public class ChannelPartnerService {
	private final ChannelPartnerDao channelPartnerDao;

	@Autowired
	public ChannelPartnerService(ChannelPartnerDao channelPartnerDao) {
		this.channelPartnerDao = channelPartnerDao;
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
