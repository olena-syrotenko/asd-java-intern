package team.asd.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.asd.entity.PropertyManagerInfo;
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
