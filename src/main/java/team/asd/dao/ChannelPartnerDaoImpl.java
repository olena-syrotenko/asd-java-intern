package team.asd.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.asd.entity.ChannelPartner;
import team.asd.mapper.ChannelPartnerMapper;

@Repository
public class ChannelPartnerDaoImpl implements ChannelPartnerDao {
	private final ChannelPartnerMapper channelPartnerMapper;

	@Autowired
	public ChannelPartnerDaoImpl(ChannelPartnerMapper channelPartnerMapper) {
		this.channelPartnerMapper = channelPartnerMapper;
	}

	@Override
	public ChannelPartner readById(Integer id) {
		return channelPartnerMapper.readById(id);
	}

	@Override
	public void create(ChannelPartner channelPartner) {
		channelPartnerMapper.create(channelPartner);
	}

	@Override
	public void update(ChannelPartner channelPartner) {
		channelPartnerMapper.update(channelPartner);
	}

	@Override
	public void deleteById(Integer id) {
		channelPartnerMapper.deleteById(id);
	}
}
