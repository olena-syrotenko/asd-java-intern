package team.asd.dao;

import team.asd.entity.Party;
import team.asd.entity.PartyReport;

import java.util.List;

public interface PartyDao {
	Party readById(Integer id);

	List<Party> readByUserTypeNameState(String userType, String name, String state);

	List<Party> readByEmailUserTypeNameState(String emailAddress, String userType, String name, String state);

	List<Party> readByChannelMaskUserType(String mask, String userType);

	PartyReport readReportById(Integer id);

	List<PartyReport> readReportByPageItems(Integer page, Integer itemsPerPage);

	void create(Party party);

	void update(Party party);

	void updateWithDelay(Party party);

	void deleteById(Integer id);
}
