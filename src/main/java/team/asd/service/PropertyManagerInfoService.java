package team.asd.service;

import team.asd.dao.PropertyManagerInfoDao;
import team.asd.entity.PropertyManagerInfo;
import team.asd.exceptions.ValidationException;
import team.asd.util.ValidationUtil;

public class PropertyManagerInfoService {
	private final PropertyManagerInfoDao propertyManagerInfoDao;

	public PropertyManagerInfoService(PropertyManagerInfoDao propertyManagerInfoDao) {
		this.propertyManagerInfoDao = propertyManagerInfoDao;
	}

	public PropertyManagerInfo readById(Integer id) {
		if (ValidationUtil.isWrongId(id)) {
			throw new ValidationException("Wrong id was provided");
		}
		return propertyManagerInfoDao.readById(id);
	}

	public void create(PropertyManagerInfo propertyManagerInfo) {
		if (ValidationUtil.isWrongRequiredFields(propertyManagerInfo)) {
			throw new ValidationException("Wrong PropertyManagerInfo object was provided");
		}
		propertyManagerInfoDao.create(propertyManagerInfo);
	}

	public void update(PropertyManagerInfo propertyManagerInfo) {
		if (ValidationUtil.isWrongUpdateObject(propertyManagerInfo)) {
			throw new ValidationException("Wrong PropertyManagerInfo object was provided");
		}
		propertyManagerInfoDao.update(propertyManagerInfo);
	}

	public void delete(Integer id) {
		if (ValidationUtil.isWrongId(id)) {
			throw new ValidationException("Wrong id was provided");
		}
		propertyManagerInfoDao.deleteById(id);
	}
}
