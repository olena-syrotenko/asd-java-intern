package team.asd.mapper;

import org.apache.ibatis.annotations.Mapper;
import team.asd.entity.PropertyManagerInfo;

@Mapper
public interface PropertyManagerInfoMapper {
	PropertyManagerInfo readById(Integer id);

	void create(PropertyManagerInfo propertyManagerInfo);

	void update(PropertyManagerInfo propertyManagerInfo);

	void deleteById(Integer id);
}
