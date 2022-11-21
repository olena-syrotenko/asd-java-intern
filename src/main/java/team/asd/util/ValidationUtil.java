package team.asd.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import team.asd.entity.ChannelPartner;
import team.asd.entity.Party;
import team.asd.exceptions.InvokeMethodException;

import java.lang.reflect.InvocationTargetException;

public class ValidationUtil {
	public static boolean isWrongId(Integer id) {
		return id == null || id <= 0;
	}

	public static boolean isWrongUpdateObject(Object object) {
		try {
			return object == null || isWrongId((Integer) MethodUtils.invokeMethod(object, "getId"));
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
			throw new InvokeMethodException(ex.getMessage());
		}
	}

	public static boolean isWrongRequiredFields(Party party) {
		return party == null || StringUtils.isBlank(party.getName());
	}

	public static boolean isWrongRequiredFields(ChannelPartner channelPartner) {
		return channelPartner == null || ValidationUtil.isWrongId(channelPartner.getPartyId()) || StringUtils.isAnyBlank(channelPartner.getAbbreviation(),
				channelPartner.getChannelName());
	}
}
