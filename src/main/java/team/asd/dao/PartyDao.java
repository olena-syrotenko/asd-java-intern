package team.asd.dao;

import team.asd.entity.Party;

import java.util.List;

public interface PartyDao {
	Party readById(Integer id);

	List<Party> readByUserTypeNameState(String userType, String name, String state);

	List<Party> readByEmailUserTypeNameState(String emailAddress, String userType, String name, String state);

	void create(Party party);

	void update(Party party);

	void deleteById(Integer id);
}
