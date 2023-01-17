package team.asd.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import team.asd.entity.PropertyManagerInfo;

@Mapper
public interface PropertyManagerInfoMapper {
	PropertyManagerInfo readById(Integer id);

	PropertyManagerInfo readByPmIdState(@Param("pmId") Integer pmId, @Param("state") String state);

	void create(PropertyManagerInfo propertyManagerInfo);

	void update(PropertyManagerInfo propertyManagerInfo);

	void deleteById(Integer id);
}
