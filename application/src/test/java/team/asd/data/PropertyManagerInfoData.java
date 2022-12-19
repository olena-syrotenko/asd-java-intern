package team.asd.data;

import team.asd.constants.PropertyManagerState;
import team.asd.entity.PropertyManagerInfo;

public class PropertyManagerInfoData {
	private static final Integer CREATED_ID = 10;

	public static Integer getCreatedId() {
		return CREATED_ID;
	}

	public static PropertyManagerInfo getPropertyManagerInfoAfterCreate(PropertyManagerInfo propertyManagerInfo) {
		return PropertyManagerInfo.builder()
				.id(CREATED_ID)
				.pmId(propertyManagerInfo.getPmId())
				.isFundsHolder(propertyManagerInfo.getIsFundsHolder())
				.numberOfPayments(propertyManagerInfo.getNumberOfPayments())
				.paymentAmount(propertyManagerInfo.getPaymentAmount())
				.paymentType(propertyManagerInfo.getPaymentType())
				.state(propertyManagerInfo.getState())
				.commission(propertyManagerInfo.getCommission())
				.isNetRate(propertyManagerInfo.getIsNetRate())
				.build();
	}

	public static PropertyManagerInfo getPropertyManagerInfoAfterUpdate(PropertyManagerInfo propertyManagerInfo) {
		return PropertyManagerInfo.builder()
				.id(propertyManagerInfo.getId())
				.pmId(propertyManagerInfo.getPmId())
				.isFundsHolder(propertyManagerInfo.getIsFundsHolder())
				.numberOfPayments(propertyManagerInfo.getNumberOfPayments())
				.paymentAmount(propertyManagerInfo.getPaymentAmount())
				.paymentType(propertyManagerInfo.getPaymentType())
				.state(propertyManagerInfo.getState())
				.commission(propertyManagerInfo.getCommission())
				.isNetRate(propertyManagerInfo.getIsNetRate())
				.build();
	}

	public static PropertyManagerInfo getPropertyManagerInfoAfterDelete(Integer id) {
		return PropertyManagerInfo.builder()
				.id(id)
				.state(PropertyManagerState.Suspended)
				.build();
	}
}
