package team.asd.data;

import team.asd.constants.ChannelPartnerState;
import team.asd.entity.ChannelPartner;

public class ChannelPartnerData {
	private static final Integer CREATED_ID = 10;
	private static final Integer UPDATED_ID = 11;
	private static final Integer DELETED_ID = 12;

	public static Integer getCreatedId() {
		return CREATED_ID;
	}

	public static Integer getUpdatedId() {
		return UPDATED_ID;
	}

	public static Integer getDeletedId() {
		return DELETED_ID;
	}

	public static ChannelPartner getChannelPartnerAfterCreate() {
		return ChannelPartner.builder()
				.id(CREATED_ID)
				.partyId(1)
				.channelName("created channel partner")
				.abbreviation("abbr")
				.build();
	}

	public static ChannelPartner getChannelPartnerBeforeUpdate() {
		return ChannelPartner.builder()
				.id(UPDATED_ID)
				.partyId(1)
				.channelName("channel name")
				.abbreviation("abbr")
				.build();
	}

	public static ChannelPartner getChannelPartnerAfterUpdate() {
		return ChannelPartner.builder()
				.id(UPDATED_ID)
				.partyId(1)
				.channelName("updated channel name")
				.abbreviation("abbr")
				.build();
	}

	public static ChannelPartner getChannelPartnerBeforeDelete() {
		return ChannelPartner.builder()
				.id(DELETED_ID)
				.state(ChannelPartnerState.Created)
				.build();
	}

	public static ChannelPartner getChannelPartnerAfterDelete() {
		return ChannelPartner.builder()
				.id(DELETED_ID)
				.state(ChannelPartnerState.Suspended)
				.build();
	}
}
