package team.asd.data;

import team.asd.constants.PartyState;
import team.asd.entity.Party;

public class PartyData {
	private static final Integer CREATED_ID = 10;
	private static final Integer UPDATED_ID = 1;
	private static final Integer DELETED_ID = 1;

	public static Integer getCreatedId() {
		return CREATED_ID;
	}

	public static Integer getUpdatedId() {
		return UPDATED_ID;
	}

	public static Integer getDeletedId() {
		return DELETED_ID;
	}

	public static Party getPartyAfterCreate() {
		return Party.builder()
				.id(CREATED_ID)
				.name("created party")
				.build();
	}

	public static Party getPartyAfterUpdate() {
		return Party.builder()
				.id(UPDATED_ID)
				.name("updated party")
				.build();
	}

	public static Party getPartyAfterDelete() {
		return Party.builder()
				.id(DELETED_ID)
				.state(PartyState.Final)
				.build();
	}
}
