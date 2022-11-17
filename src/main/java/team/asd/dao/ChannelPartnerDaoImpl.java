package team.asd.dao;

import org.springframework.stereotype.Repository;
import team.asd.entity.ChannelPartner;

@Repository
public class ChannelPartnerDaoImpl implements ChannelPartnerDao {
	@Override
	public ChannelPartner readById(Integer id) {
		return ChannelPartner.builder()
				.build();
	}

	@Override
	public void create(ChannelPartner channelPartner) {
		// insert new channel partner into db
	}

	@Override
	public void update(ChannelPartner channelPartner) {
		// update channel partner record by id
	}

	@Override
	public void deleteById(Integer id) {
		// set channel partner state to Final by id
	}
}
