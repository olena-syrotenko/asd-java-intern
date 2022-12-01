package team.asd.dao;

import team.asd.entity.Party;

import java.util.List;

public class TestPartyDao implements PartyDao {
	@Override
	public Party readById(Integer id) {
		return null;
	}

	@Override
	public List<Party> readByUserTypeNameAndState(Party party) {
		return null;
	}

	@Override
	public List<Party> readByEmailUserTypeNameAndState(Party party) {
		return null;
	}

	@Override
	public void create(Party party) {

	}

	@Override
	public void update(Party party) {

	}

	@Override
	public void deleteById(Integer id) {

	}
}
