package team.asd.util;

import org.apache.commons.lang3.BooleanUtils;
import team.asd.constants.PaymentNumber;
import team.asd.constants.PaymentType;
import team.asd.constants.PropertyManagerState;
import team.asd.dto.PropertyManagerInfoDto;
import team.asd.entity.PropertyManagerInfo;

import java.util.stream.Stream;

public class PropertyManagerInfoUtil {
	public static PropertyManagerInfo convertToEntity(PropertyManagerInfoDto propertyManagerInfoDto) {
		return PropertyManagerInfo.builder()
				.id(propertyManagerInfoDto.getId())
				.pmId(propertyManagerInfoDto.getPmId())
				.isFundsHolder(BooleanUtils.toBooleanObject(propertyManagerInfoDto.getIsFundsHolder()))
				.numberOfPayments(PaymentNumber.getByInteger(propertyManagerInfoDto.getNumberOfPayments()))
				.paymentAmount(propertyManagerInfoDto.getPaymentAmount())
				.paymentType(PaymentType.getByInteger(propertyManagerInfoDto.getPaymentType()))
				.state(convertStringIntoPropertyManagerState(propertyManagerInfoDto.getState()))
				.commission(propertyManagerInfoDto.getCommission())
				.isNetRate(convertIntegerIntoBooleanNetRate(propertyManagerInfoDto.getIsNetRate()))
				.build();
	}

	public static PropertyManagerInfoDto convertToDto(PropertyManagerInfo propertyManagerInfo) {
		return PropertyManagerInfoDto.builder()
				.id(propertyManagerInfo.getId())
				.pmId(propertyManagerInfo.getPmId())
				.isFundsHolder(BooleanUtils.toIntegerObject(propertyManagerInfo.getIsFundsHolder()))
				.numberOfPayments(convertPaymentNumberIntoInteger(propertyManagerInfo.getNumberOfPayments()))
				.paymentAmount(propertyManagerInfo.getPaymentAmount())
				.paymentType(convertPaymentTypeIntoInteger(propertyManagerInfo.getPaymentType()))
				.state(convertPropertyManagerStateIntoString(propertyManagerInfo.getState()))
				.commission(propertyManagerInfo.getCommission())
				.isNetRate(BooleanUtils.toIntegerObject(propertyManagerInfo.getIsNetRate()))
				.build();
	}

	private static PropertyManagerState convertStringIntoPropertyManagerState(String propertyManagerState) {
		return Stream.of(PropertyManagerState.values())
				.filter(state -> state.name()
						.equalsIgnoreCase(propertyManagerState))
				.findFirst()
				.orElse(PropertyManagerState.Created);
	}

	private static Boolean convertIntegerIntoBooleanNetRate(Integer netRate) {
		return netRate != null && BooleanUtils.toBoolean(netRate);
	}

	private static Integer convertPaymentNumberIntoInteger(PaymentNumber paymentNumber) {
		return paymentNumber == null ? null : paymentNumber.getIntegerValue();
	}

	private static Integer convertPaymentTypeIntoInteger(PaymentType paymentType) {
		return paymentType == null ? null : paymentType.getIntegerValue();
	}

	private static String convertPropertyManagerStateIntoString(PropertyManagerState propertyManagerState) {
		return propertyManagerState == null ? null : propertyManagerState.name();
	}
}
