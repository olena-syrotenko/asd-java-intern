package team.asd.dao;

import team.asd.entity.ManagerToChannel;

public interface ManagerToChannelDao {
	ManagerToChannel readById(Integer id);

	void create(ManagerToChannel managerToChannel);

	void update(ManagerToChannel managerToChannel);

	void deleteById(Integer id);
}
