package team.asd.dao;

import team.asd.entity.ManagerToChannel;

import java.util.List;

public interface ManagerToChannelDao {
	ManagerToChannel readById(Integer id);

	ManagerToChannel readByPropManagerIdChanPartnerId(Integer propertyManagerId, Integer channelPartnerId);

	List<ManagerToChannel> readByChannelPartnerIdNetRate(Integer channelPartnerId, Integer netRate);

	void create(ManagerToChannel managerToChannel);

	void createList(List<ManagerToChannel> managerToChannels);

	void update(ManagerToChannel managerToChannel);

	void deleteById(Integer id);
}
