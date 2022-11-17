package team.asd.util;

import org.apache.commons.lang3.BooleanUtils;
import team.asd.constants.ChannelPartnerState;
import team.asd.dto.ChannelPartnerDto;
import team.asd.entity.ChannelPartner;

import java.util.stream.Stream;

public class ChannelPartnerUtil {
	public static ChannelPartner convertToEntity(ChannelPartnerDto channelPartnerDto) {
		if (channelPartnerDto == null) {
			return null;
		}
		return ChannelPartner.builder()
				.id(channelPartnerDto.getId())
				.partyId(channelPartnerDto.getPartyId())
				.abbreviation(channelPartnerDto.getAbbreviation())
				.channelName(channelPartnerDto.getChannelName())
				.state(convertStringIntoChannelPartnerState(channelPartnerDto.getState()))
				.commission(channelPartnerDto.getCommission())
				.bpCommission(channelPartnerDto.getBpCommission())
				.isFundsHolder(convertIntegerIntoBoolean(channelPartnerDto.getIsFundsHolder()))
				.build();
	}

	public static ChannelPartnerDto convertToDto(ChannelPartner channelPartner) {
		if (channelPartner == null) {
			return null;
		}
		return ChannelPartnerDto.builder()
				.id(channelPartner.getId())
				.partyId(channelPartner.getPartyId())
				.abbreviation(channelPartner.getAbbreviation())
				.channelName(channelPartner.getChannelName())
				.state(convertChannelPartnerStateIntoString(channelPartner.getState()))
				.commission(channelPartner.getCommission())
				.bpCommission(channelPartner.getBpCommission())
				.isFundsHolder(BooleanUtils.toIntegerObject(channelPartner.getIsFundsHolder()))
				.build();
	}

	private static ChannelPartnerState convertStringIntoChannelPartnerState(String channelPartnerState) {
		return Stream.of(ChannelPartnerState.values())
				.filter(state -> state.name()
						.equalsIgnoreCase(channelPartnerState))
				.findFirst()
				.orElse(ChannelPartnerState.Created);
	}

	private static Boolean convertIntegerIntoBoolean(Integer isFundsHolder) {
		return isFundsHolder != null && BooleanUtils.toBoolean(isFundsHolder);
	}

	private static String convertChannelPartnerStateIntoString(ChannelPartnerState channelPartnerState) {
		return channelPartnerState == null ? null : channelPartnerState.name();
	}
}
