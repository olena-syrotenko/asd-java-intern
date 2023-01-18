package team.asd.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import team.asd.entity.ChannelPartner;

import java.util.List;

@Mapper
public interface ChannelPartnerMapper {
	ChannelPartner readById(Integer id);

	List<ChannelPartner> readByAbbreviationMask(String abbreviationMask);

	ChannelPartner readByPartyIdState(@Param("partyId") Integer partyId, @Param("state") String state);

	void create(ChannelPartner channelPartner);

	void update(ChannelPartner channelPartner);

	void deleteById(Integer id);
}
