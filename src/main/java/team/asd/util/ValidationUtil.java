package team.asd.util;

import org.apache.commons.lang3.StringUtils;
import team.asd.entity.ChannelPartner;
import team.asd.entity.Party;

public class ValidationUtil {
	public static boolean isWrongId(Integer id) {
		return id == null || id <= 0;
	}

	public static boolean isWrongRequiredFields(Party party) {
		return StringUtils.isBlank(party.getName());
	}

	public static boolean isWrongRequiredFields(ChannelPartner channelPartner) {
		return ValidationUtil.isWrongId(channelPartner.getPartyId()) || StringUtils.isBlank(channelPartner.getAbbreviation()) || StringUtils.isBlank(
				channelPartner.getChannelName());
	}
}
