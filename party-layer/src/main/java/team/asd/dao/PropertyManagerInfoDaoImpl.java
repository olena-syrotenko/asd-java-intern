package team.asd.dao;

import team.asd.entity.PropertyManagerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.asd.mapper.PropertyManagerInfoMapper;

@Repository
public class PropertyManagerInfoDaoImpl implements PropertyManagerInfoDao {
	private final PropertyManagerInfoMapper propertyManagerInfoMapper;

	@Autowired
	public PropertyManagerInfoDaoImpl(PropertyManagerInfoMapper propertyManagerInfoMapper) {
		this.propertyManagerInfoMapper = propertyManagerInfoMapper;
	}

	@Override
	public PropertyManagerInfo readById(Integer id) {
		return propertyManagerInfoMapper.readById(id);
	}

	@Override
	public PropertyManagerInfo readByPmIdState(Integer pmId, String state) {
		return propertyManagerInfoMapper.readByPmIdState(pmId, state);
	}

	@Override
	public void create(PropertyManagerInfo propertyManagerInfo) {
		propertyManagerInfoMapper.create(propertyManagerInfo);
	}

	@Override
	public void update(PropertyManagerInfo propertyManagerInfo) {
		propertyManagerInfoMapper.update(propertyManagerInfo);
	}

	@Override
	public void deleteById(Integer id) {
		propertyManagerInfoMapper.deleteById(id);
	}
}
