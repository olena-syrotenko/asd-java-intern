package team.asd.mapper;

import org.apache.ibatis.annotations.Mapper;
import team.asd.entity.Party;

import java.util.List;

@Mapper
public interface PartyMapper {
	Party readById(Integer id);

	List<Party> readByUserTypeNameAndState(Party party);

	List<Party> readByEmailUserTypeNameAndState(Party party);

	void create(Party party);

	void update(Party party);

	void deleteById(Integer id);
}
