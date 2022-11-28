package team.asd.dao;

import team.asd.entity.PropertyManagerInfo;

public interface PropertyManagerInfoDao {
	PropertyManagerInfo readById(Integer id);

	void create(PropertyManagerInfo propertyManagerInfo);

	void update(PropertyManagerInfo propertyManagerInfo);

	void deleteById(Integer id);
}