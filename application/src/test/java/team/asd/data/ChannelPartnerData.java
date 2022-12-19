package team.asd.data;

import team.asd.constants.ChannelPartnerState;
import team.asd.entity.ChannelPartner;

public class ChannelPartnerData {
	private static final Integer CREATED_ID = 10;

	public static Integer getCreatedId() {
		return CREATED_ID;
	}

	public static ChannelPartner getChannelPartnerAfterCreate(ChannelPartner channelPartner) {
		return ChannelPartner.builder()
				.id(CREATED_ID)
				.partyId(channelPartner.getPartyId())
				.channelName(channelPartner.getChannelName())
				.abbreviation(channelPartner.getAbbreviation())
				.state(channelPartner.getState())
				.commission(channelPartner.getCommission())
				.bpCommission(channelPartner.getBpCommission())
				.isFundsHolder(channelPartner.getIsFundsHolder())
				.build();
	}

	public static ChannelPartner getChannelPartnerAfterUpdate(ChannelPartner channelPartner) {
		return ChannelPartner.builder()
				.id(channelPartner.getId())
				.partyId(channelPartner.getPartyId())
				.channelName(channelPartner.getChannelName())
				.abbreviation(channelPartner.getAbbreviation())
				.state(channelPartner.getState())
				.commission(channelPartner.getCommission())
				.bpCommission(channelPartner.getBpCommission())
				.isFundsHolder(channelPartner.getIsFundsHolder())
				.build();
	}

	public static ChannelPartner getChannelPartnerAfterDelete(Integer id) {
		return ChannelPartner.builder()
				.id(id)
				.state(ChannelPartnerState.Suspended)
				.build();
	}
}
