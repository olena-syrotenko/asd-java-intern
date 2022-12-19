package team.asd.dao;

import team.asd.entity.ChannelPartner;

import java.util.List;

public class TestChannelPartnerDao implements ChannelPartnerDao {
	@Override
	public ChannelPartner readById(Integer id) {
		return null;
	}

	@Override
	public List<ChannelPartner> readByAbbreviationMask(String abbreviationMask) {
		return null;
	}

	@Override
	public ChannelPartner readByPartyIdState(Integer partyId, String state) {
		return null;
	}

	@Override
	public void create(ChannelPartner channelPartner) {

	}

	@Override
	public void update(ChannelPartner channelPartner) {

	}

	@Override
	public void deleteById(Integer id) {

	}
}
