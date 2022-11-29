package team.asd.data;

import team.asd.constants.PropertyManagerState;
import team.asd.entity.PropertyManagerInfo;

public class PropertyManagerInfoData {
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

	public static PropertyManagerInfo getPropertyManagerInfoAfterCreate() {
		return PropertyManagerInfo.builder()
				.id(CREATED_ID)
				.pmId(1)
				.build();
	}

	public static PropertyManagerInfo getPropertyManagerInfoBeforeUpdate() {
		return PropertyManagerInfo.builder()
				.id(UPDATED_ID)
				.pmId(1)
				.paymentAmount(120.0)
				.build();
	}

	public static PropertyManagerInfo getPropertyManagerInfoAfterUpdate() {
		return PropertyManagerInfo.builder()
				.id(UPDATED_ID)
				.pmId(1)
				.paymentAmount(100.0)
				.build();
	}

	public static PropertyManagerInfo getPropertyManagerInfoBeforeDelete() {
		return PropertyManagerInfo.builder()
				.id(DELETED_ID)
				.state(PropertyManagerState.Created)
				.build();
	}

	public static PropertyManagerInfo getPropertyManagerInfoAfterDelete() {
		return PropertyManagerInfo.builder()
				.id(DELETED_ID)
				.state(PropertyManagerState.Suspended)
				.build();
	}
}
