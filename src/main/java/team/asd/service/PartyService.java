package team.asd.service;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.asd.dao.PartyDao;
import team.asd.dto.PartyReportDto;
import team.asd.entity.Party;
import team.asd.exceptions.ValidationException;
import team.asd.util.ValidationUtil;

import java.util.List;

@Service
public class PartyService {
	private final PartyDao partyDao;

	@Autowired
	public PartyService(PartyDao partyDao) {
		this.partyDao = partyDao;
	}

	public Party readById(Integer id) {
		if (ValidationUtil.isWrongId(id)) {
			throw new ValidationException("Wrong id was provided");
		}
		return partyDao.readById(id);
	}

	public List<Party> readByUserTypeNameState(String userType, String name, String state) {
		if (StringUtils.isBlank(name)) {
			throw new ValidationException("Required parameter name was not provided");
		}
		return partyDao.readByUserTypeNameState(userType, name, state);
	}

	public List<Party> readByEmailUserTypeNameState(String emailAddress, String userType, String name, String state) {
		if (StringUtils.isBlank(emailAddress)) {
			throw new ValidationException("Required parameter email address was not provided");
		}
		return partyDao.readByEmailUserTypeNameState(emailAddress, userType, name, state);
	}

	public List<Party> readByChannelMaskUserType(String mask, String userType) {
		if (StringUtils.isAnyBlank(mask, userType)) {
			throw new ValidationException("Required parameters were not provided");
		}
		return partyDao.readByChannelMaskUserType(mask, userType);
	}

	public PartyReportDto readReportById(Integer id) {
		if (ValidationUtil.isWrongId(id)) {
			throw new ValidationException("Wrong id was provided");
		}
		return partyDao.readReportById(id);
	}

	public List<PartyReportDto> readReportByPageItems(Integer page, Integer itemsPerPage) {
		if (ObjectUtils.anyNull(page, itemsPerPage) || page < 1 || itemsPerPage < 1) {
			throw new ValidationException("Wrong parameters were provided");
		}
		return partyDao.readReportByPageItems(page, itemsPerPage);
	}

	public void create(Party party) {
		if (ValidationUtil.isWrongRequiredFields(party)) {
			throw new ValidationException("Wrong Party object was provided");
		}
		partyDao.create(party);
	}

	public void update(Party party) {
		if (ValidationUtil.isWrongUpdateObject(party)) {
			throw new ValidationException("Wrong Party object was provided");
		}
		partyDao.update(party);
	}

	public void delete(Integer id) {
		if (ValidationUtil.isWrongId(id)) {
			throw new ValidationException("Wrong id was provided");
		}
		partyDao.deleteById(id);
	}
}
