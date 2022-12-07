package team.asd.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.asd.entity.ManagerToChannel;
import team.asd.mapper.ManagerToChannelMapper;

import java.util.List;

@Repository
public class ManagerToChannelDaoImpl implements ManagerToChannelDao {
	private final ManagerToChannelMapper managerToChannelMapper;

	@Autowired
	public ManagerToChannelDaoImpl(ManagerToChannelMapper managerToChannelMapper) {
		this.managerToChannelMapper = managerToChannelMapper;
	}

	@Override
	public ManagerToChannel readById(Integer id) {
		return managerToChannelMapper.readById(id);
	}

	@Override
	public ManagerToChannel readByPropManagerIdChanPartnerId(Integer propertyManagerId, Integer channelPartnerId) {
		return managerToChannelMapper.readByPropManagerIdChanPartnerId(propertyManagerId, channelPartnerId);
	}

	@Override
	public List<ManagerToChannel> readByChannelPartnerIdNetRate(Integer channelPartnerId, Integer netRate) {
		return managerToChannelMapper.readByChannelPartnerIdNetRate(channelPartnerId, netRate);
	}

	@Override
	public void create(ManagerToChannel managerToChannel) {
		managerToChannelMapper.create(managerToChannel);
	}

	@Override
	public void createList(List<ManagerToChannel> managerToChannels) {
		managerToChannelMapper.createList(managerToChannels);
	}

	@Override
	public void update(ManagerToChannel managerToChannel) {
		managerToChannelMapper.update(managerToChannel);
	}

	@Override
	public void deleteById(Integer id) {
		managerToChannelMapper.deleteById(id);
	}
}
