package team.asd.mapper;

import org.apache.ibatis.annotations.Mapper;
import team.asd.entity.ManagerToChannel;

@Mapper
public interface ManagerToChannelMapper {
	ManagerToChannel readById(Integer id);

	void create(ManagerToChannel managerToChannel);

	void update(ManagerToChannel managerToChannel);

	void deleteById(Integer id);
}
