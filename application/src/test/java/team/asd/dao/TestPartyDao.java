package team.asd.dao;

import team.asd.entity.Party;
import team.asd.entity.PartyReport;

import java.util.List;

public class TestPartyDao implements PartyDao {
	@Override
	public Party readById(Integer id) {
		return null;
	}

	@Override
	public List<Party> readByUserTypeNameState(String userType, String name, String state) {
		return null;
	}

	@Override
	public List<Party> readByEmailUserTypeNameState(String emailAddress, String userType, String name, String state) {
		return null;
	}

	@Override
	public List<Party> readByChannelMaskUserType(String mask, String userType) {
		return null;
	}

	@Override
	public PartyReport readReportById(Integer id) {
		return null;
	}

	@Override
	public List<PartyReport> readReportByPageItems(Integer page, Integer itemsPerPage) {
		return null;
	}

	@Override
	public void create(Party party) {

	}

	@Override
	public void update(Party party) {

	}

	@Override
	public void updateWithDelay(Party party) {

	}

	@Override
	public void deleteById(Integer id) {

	}
}
