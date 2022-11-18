package team.asd.mapper;

import org.apache.ibatis.annotations.Mapper;
import team.asd.entity.ChannelPartner;

@Mapper
public interface ChannelPartnerMapper {
	ChannelPartner readById(Integer id);

	void create(ChannelPartner channelPartner);

	void update(ChannelPartner channelPartner);

	void deleteById(Integer id);
}
