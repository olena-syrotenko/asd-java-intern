package team.asd.util;

import org.apache.commons.lang3.BooleanUtils;
import team.asd.dto.ManagerToChannelDto;
import team.asd.entity.ManagerToChannel;

public class ManagerToChannelUtil {
	public static ManagerToChannel convertToEntity(ManagerToChannelDto managerToChannelDto) {
		if (managerToChannelDto == null) {
			return null;
		}
		return ManagerToChannel.builder()
				.id(managerToChannelDto.getId())
				.propertyManagerId(managerToChannelDto.getPropertyManagerId())
				.channelPartnerId(managerToChannelDto.getChannelPartnerId())
				.channelPartnerCommission(managerToChannelDto.getChannelPartnerCommission())
				.isFundsHolder(BooleanUtils.toBooleanObject(managerToChannelDto.getIsFundsHolder()))
				.isNetRate(BooleanUtils.toBooleanObject(managerToChannelDto.getIsFundsHolder()))
				.build();
	}

	public static ManagerToChannelDto convertToDto(ManagerToChannel managerToChannel) {
		if (managerToChannel == null) {
			return null;
		}
		return ManagerToChannelDto.builder()
				.id(managerToChannel.getId())
				.propertyManagerId(managerToChannel.getPropertyManagerId())
				.channelPartnerId(managerToChannel.getChannelPartnerId())
				.channelPartnerCommission(managerToChannel.getChannelPartnerCommission())
				.isFundsHolder(BooleanUtils.toIntegerObject(managerToChannel.getIsFundsHolder()))
				.isNetRate(BooleanUtils.toIntegerObject(managerToChannel.getIsFundsHolder()))
				.build();
	}
}
