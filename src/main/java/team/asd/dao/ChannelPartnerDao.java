package team.asd.dao;

import team.asd.entity.ChannelPartner;

import java.util.List;

public interface ChannelPartnerDao {
	ChannelPartner readById(Integer id);

	List<ChannelPartner> readByAbbreviationMask(String abbreviationMask);

	ChannelPartner readByPartyIdState(Integer partyId, String state);

	void create(ChannelPartner channelPartner);

	void update(ChannelPartner channelPartner);

	void deleteById(Integer id);
}
