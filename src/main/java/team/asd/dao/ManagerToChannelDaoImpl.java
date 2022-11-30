package team.asd.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.asd.entity.ManagerToChannel;
import team.asd.mapper.ManagerToChannelMapper;

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
	public void create(ManagerToChannel managerToChannel) {
		managerToChannelMapper.create(managerToChannel);
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
