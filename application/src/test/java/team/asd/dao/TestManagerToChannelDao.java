package team.asd.dao;

import team.asd.entity.ManagerToChannel;

import java.util.List;

public class TestManagerToChannelDao implements ManagerToChannelDao{
	@Override
	public ManagerToChannel readById(Integer id) {
		return null;
	}

	@Override
	public ManagerToChannel readByPropManagerIdChanPartnerId(Integer propertyManagerId, Integer channelPartnerId) {
		return null;
	}

	@Override
	public List<ManagerToChannel> readByChannelPartnerIdNetRate(Integer channelPartnerId, Integer netRate) {
		return null;
	}

	@Override
	public List<ManagerToChannel> readByFundsHolder(Integer fundsHolder) {
		return null;
	}

	@Override
	public void create(ManagerToChannel managerToChannel) {

	}

	@Override
	public void createList(List<ManagerToChannel> managerToChannels) {

	}

	@Override
	public void update(ManagerToChannel managerToChannel) {

	}

	@Override
	public void deleteById(Integer id) {

	}
}
