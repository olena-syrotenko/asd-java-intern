package team.asd.data;

import team.asd.constants.PartyState;
import team.asd.entity.Party;

public class PartyData {
	private static final Integer CREATED_ID = 10;

	public static Integer getCreatedId() {
		return CREATED_ID;
	}

	public static Party getPartyAfterCreate(Party party) {
		return Party.builder()
				.id(CREATED_ID)
				.name(party.getName())
				.state(party.getState())
				.postalAddress(party.getPostalAddress())
				.emailAddress(party.getEmailAddress())
				.mobilePhone(party.getMobilePhone())
				.password(party.getPassword())
				.currency(party.getCurrency())
				.userType(party.getUserType())
				.build();
	}

	public static Party getPartyAfterUpdate(Party party) {
		return Party.builder()
				.id(party.getId())
				.name(party.getName())
				.state(party.getState())
				.postalAddress(party.getPostalAddress())
				.emailAddress(party.getEmailAddress())
				.mobilePhone(party.getMobilePhone())
				.password(party.getPassword())
				.currency(party.getCurrency())
				.userType(party.getUserType())
				.build();
	}

	public static Party getPartyAfterDelete(Integer id) {
		return Party.builder()
				.id(id)
				.state(PartyState.Final)
				.build();
	}
}
