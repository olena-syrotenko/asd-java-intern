package team.asd.dao;

import team.asd.entity.Party;

import java.util.List;

public interface PartyDao {
	Party readById(Integer id);

	List<Party> readByUserTypeNameAndState(Party party);

	List<Party> readByEmailUserTypeNameAndState(Party party);

	void create(Party party);

	void update(Party party);

	void deleteById(Integer id);
}
