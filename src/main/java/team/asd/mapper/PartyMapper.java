package team.asd.mapper;

import org.apache.ibatis.annotations.Mapper;
import team.asd.entity.Party;

@Mapper
public interface PartyMapper {
	Party readById(Integer id);

	void create(Party party);

	void update(Party party);

	void deleteById(Integer id);
}
