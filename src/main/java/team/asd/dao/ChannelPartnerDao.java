package team.asd.dao;

import team.asd.entity.ChannelPartner;

public interface ChannelPartnerDao {
	ChannelPartner readById(Integer id);

	void create(ChannelPartner channelPartner);

	void update(ChannelPartner channelPartner);

	void deleteById(Integer id);
}
