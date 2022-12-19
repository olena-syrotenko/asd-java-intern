package team.asd.service;

import team.asd.exceptions.ValidationException;
import team.asd.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.asd.dao.PropertyManagerInfoDao;
import team.asd.entity.PropertyManagerInfo;

@Service
public class PropertyManagerInfoService {
	private final PropertyManagerInfoDao propertyManagerInfoDao;

	@Autowired
	public PropertyManagerInfoService(PropertyManagerInfoDao propertyManagerInfoDao) {
		this.propertyManagerInfoDao = propertyManagerInfoDao;
	}

	public PropertyManagerInfo readById(Integer id) {
		if (ValidationUtil.isWrongId(id)) {
			throw new ValidationException("Wrong id was provided");
		}
		return propertyManagerInfoDao.readById(id);
	}

	public PropertyManagerInfo readByPmIdState(Integer pmId, String state) {
		if (ValidationUtil.isWrongId(pmId)) {
			throw new ValidationException("Wrong required parameter pmId");
		}
		return propertyManagerInfoDao.readByPmIdState(pmId, state);
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
