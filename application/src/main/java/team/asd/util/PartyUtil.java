package team.asd.util;

import org.apache.commons.collections4.CollectionUtils;
import team.asd.constants.PartyState;
import team.asd.constants.UserType;
import team.asd.dto.PartyChannelDto;
import team.asd.dto.PartyDto;
import team.asd.dto.PartyManagerDto;
import team.asd.dto.PartyReportDto;
import team.asd.entity.Party;
import team.asd.entity.PartyChannel;
import team.asd.entity.PartyManager;
import team.asd.entity.PartyReport;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PartyUtil {
	public static Party convertToEntity(PartyDto partyDto) {
		if (partyDto == null) {
			return null;
		}
		return Party.builder()
				.id(partyDto.getId())
				.name(partyDto.getName())
				.state(convertStringIntoPartyState(partyDto.getState()))
				.postalAddress(partyDto.getPostalAddress())
				.emailAddress(partyDto.getEmailAddress())
				.mobilePhone(partyDto.getMobilePhone())
				.password(partyDto.getPassword())
				.currency(partyDto.getCurrency())
				.userType(convertStringIntoUserType(partyDto.getUserType()))
				.build();
	}

	public static PartyDto convertToDto(Party party) {
		if (party == null) {
			return null;
		}
		return PartyDto.builder()
				.id(party.getId())
				.name(party.getName())
				.state(convertPartyStateIntoString(party.getState()))
				.postalAddress(party.getPostalAddress())
				.emailAddress(party.getEmailAddress())
				.mobilePhone(party.getMobilePhone())
				.password(party.getPassword())
				.currency(party.getCurrency())
				.userType(convertUserTypeIntoString(party.getUserType()))
				.build();
	}

	public static PartyReportDto convertToReportDto(PartyReport partyReport) {
		if (partyReport == null) {
			return null;
		}
		return PartyReportDto.builder()
				.id(partyReport.getId())
				.name(partyReport.getName())
				.state(partyReport.getState())
				.postalAddress(partyReport.getPostalAddress())
				.emailAddress(partyReport.getEmailAddress())
				.mobilePhone(partyReport.getMobilePhone())
				.password(partyReport.getPassword())
				.currency(partyReport.getCurrency())
				.userType(partyReport.getUserType())
				.channelPartner(convertToPartyChannelDto(partyReport.getChannelPartner()))
				.propertyManager(convertToPartyManagerDto(partyReport.getPropertyManager()))
				.managerToChannel(CollectionUtils.emptyIfNull(partyReport.getManagerToChannel())
						.stream()
						.map(ManagerToChannelUtil::convertToDto)
						.collect(Collectors.toList()))
				.build();
	}

	public static PartyState convertStringIntoPartyState(String partyState) {
		return Stream.of(PartyState.values())
				.filter(state -> state.name()
						.equalsIgnoreCase(partyState))
				.findFirst()
				.orElse(PartyState.Initial);
	}

	public static UserType convertStringIntoUserType(String userType) {
		return Stream.of(UserType.values())
				.filter(user -> user.name()
						.equalsIgnoreCase(userType))
				.findFirst()
				.orElse(null);
	}

	public static String convertPartyStateIntoString(PartyState partyState) {
		return partyState == null ? null : partyState.name();
	}

	public static String convertUserTypeIntoString(UserType userType) {
		return userType == null ? null : userType.name();
	}

	public static PartyChannelDto convertToPartyChannelDto(PartyChannel partyChannel) {
		if (partyChannel == null) {
			return null;
		}
		return PartyChannelDto.builder()
				.channelName(partyChannel.getChannelName())
				.abbreviation(partyChannel.getAbbreviation())
				.commission(partyChannel.getCommission())
				.isFundsHolder(partyChannel.getIsFundsHolder())
				.build();
	}

	public static PartyManagerDto convertToPartyManagerDto(PartyManager partyManager) {
		if (partyManager == null) {
			return null;
		}
		return PartyManagerDto.builder()
				.commission(partyManager.getCommission())
				.isFundsHolder(partyManager.getIsFundsHolder())
				.paymentAmount(partyManager.getPaymentAmount())
				.paymentType(partyManager.getPaymentType())
				.build();
	}
}
