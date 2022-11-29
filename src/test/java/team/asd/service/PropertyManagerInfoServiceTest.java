package team.asd.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import team.asd.constants.PropertyManagerState;
import team.asd.dao.PropertyManagerInfoDao;
import team.asd.data.PropertyManagerInfoData;
import team.asd.entity.PropertyManagerInfo;
import team.asd.exceptions.ValidationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class PropertyManagerInfoServiceTest {
	@Mock
	private PropertyManagerInfoDao propertyManagerInfoDao;
	private PropertyManagerInfoService propertyManagerInfoService;
	private PropertyManagerInfo propertyManagerInfo;
	private PropertyManagerInfo createdPropertyManagerInfo;
	private PropertyManagerInfo updatedPropertyManagerInfo;
	private PropertyManagerInfo deletedPropertyManagerInfo;

	@BeforeEach
	public void initPropertyManagerInfo() {
		MockitoAnnotations.openMocks(this);
		propertyManagerInfoService = new PropertyManagerInfoService(propertyManagerInfoDao);
		propertyManagerInfo = new PropertyManagerInfo();
		createdPropertyManagerInfo = null;
		updatedPropertyManagerInfo = PropertyManagerInfoData.getPropertyManagerInfoBeforeUpdate();
		deletedPropertyManagerInfo = PropertyManagerInfoData.getPropertyManagerInfoBeforeDelete();

		when(propertyManagerInfoDao.readById(1)).thenReturn(PropertyManagerInfo.builder()
				.id(1)
				.build());
		when(propertyManagerInfoDao.readById(PropertyManagerInfoData.getCreatedId())).thenAnswer(invocation -> createdPropertyManagerInfo);
		when(propertyManagerInfoDao.readById(PropertyManagerInfoData.getUpdatedId())).thenAnswer(invocation -> updatedPropertyManagerInfo);
		when(propertyManagerInfoDao.readById(PropertyManagerInfoData.getDeletedId())).thenAnswer(invocation -> deletedPropertyManagerInfo);

		doAnswer(invocation -> {
			createdPropertyManagerInfo = PropertyManagerInfoData.getPropertyManagerInfoAfterCreate();
			return null;
		}).when(propertyManagerInfoDao)
				.create(any(PropertyManagerInfo.class));

		doAnswer(invocation -> {
			updatedPropertyManagerInfo = PropertyManagerInfoData.getPropertyManagerInfoAfterUpdate();
			return null;
		}).when(propertyManagerInfoDao)
				.update(any(PropertyManagerInfo.class));

		doAnswer(invocation -> {
			deletedPropertyManagerInfo = PropertyManagerInfoData.getPropertyManagerInfoAfterDelete();
			return null;
		}).when(propertyManagerInfoDao)
				.deleteById(any(Integer.class));
	}

	@Test
	void testReadByIdWithNull() {
		assertThrows(ValidationException.class, () -> propertyManagerInfoService.readById(null), "Validation exception should be thrown");
	}

	@Test
	void testReadByIdWithZeroAndNegativeId() {
		assertThrows(ValidationException.class, () -> propertyManagerInfoService.readById(0), "Validation exception should be thrown");
		assertThrows(ValidationException.class, () -> propertyManagerInfoService.readById(-1), "Validation exception should be thrown");
	}

	@Test
	void testReadById() throws ValidationException {
		PropertyManagerInfo readPropertyManagerInfo = propertyManagerInfoService.readById(1);
		assertNotNull(readPropertyManagerInfo, "PropertyManagerInfo object should be returned");
		assertEquals(1, readPropertyManagerInfo.getId(), "Ids should be equal");
		assertNull(propertyManagerInfoService.readById(100), "Null value should be returned");
		verify(propertyManagerInfoDao, atLeast(2)).readById(any(Integer.class));
	}

	@Test
	void testCreateWithNull() {
		assertThrows(ValidationException.class, () -> propertyManagerInfoService.create(null), "Validation exception should be thrown");
	}

	@Test
	void testCreateWithNullAndZeroAndNegativePmId() {
		propertyManagerInfo.setPmId(null);
		assertThrows(ValidationException.class, () -> propertyManagerInfoService.create(propertyManagerInfo), "Validation exception should be thrown");
		propertyManagerInfo.setPmId(0);
		assertThrows(ValidationException.class, () -> propertyManagerInfoService.create(propertyManagerInfo), "Validation exception should be thrown");
		propertyManagerInfo.setPmId(-2);
		assertThrows(ValidationException.class, () -> propertyManagerInfoService.create(propertyManagerInfo), "Validation exception should be thrown");
	}

	@Test
	void testCreate() throws ValidationException {
		assertNull(propertyManagerInfoService.readById(PropertyManagerInfoData.getCreatedId()),
				"Created property manager info object should be null before create");

		propertyManagerInfo.setPmId(1);
		propertyManagerInfoService.create(propertyManagerInfo);
		PropertyManagerInfo insertedPropertyManagerInfo = propertyManagerInfoService.readById(PropertyManagerInfoData.getCreatedId());

		assertNotNull(insertedPropertyManagerInfo, "Created PropertyManagerInfo object should be returned");
		assertEquals(PropertyManagerInfoData.getCreatedId(), insertedPropertyManagerInfo.getId(), "Ids should be equal");
		assertEquals(1, insertedPropertyManagerInfo.getPmId(), "Pm ids should be equal");
		verify(propertyManagerInfoDao, atLeast(1)).create(any(PropertyManagerInfo.class));
		verify(propertyManagerInfoDao, atLeast(2)).readById(any(Integer.class));
	}

	@Test
	void testUpdateWithNull() {
		assertThrows(ValidationException.class, () -> propertyManagerInfoService.update(null), "Validation exception should be thrown");
		assertThrows(ValidationException.class, () -> propertyManagerInfoService.update(propertyManagerInfo), "Validation exception should be thrown");
	}

	@Test
	void testUpdateWithZeroAndNegativeId() {
		propertyManagerInfo.setId(0);
		assertThrows(ValidationException.class, () -> propertyManagerInfoService.update(propertyManagerInfo), "Validation exception should be thrown");
		propertyManagerInfo.setId(-5);
		assertThrows(ValidationException.class, () -> propertyManagerInfoService.update(propertyManagerInfo), "Validation exception should be thrown");
	}

	@Test
	void testUpdate() throws ValidationException {
		assertNotEquals(100, propertyManagerInfoService.readById(PropertyManagerInfoData.getUpdatedId())
				.getPaymentAmount(), "Payment amount should not be changed before update");

		propertyManagerInfo.setId(PropertyManagerInfoData.getUpdatedId());
		propertyManagerInfo.setPaymentAmount(100.0);
		propertyManagerInfoService.update(propertyManagerInfo);

		PropertyManagerInfo changedPropertyManagerInfo = propertyManagerInfoService.readById(PropertyManagerInfoData.getUpdatedId());
		assertEquals(PropertyManagerInfoData.getUpdatedId(), changedPropertyManagerInfo.getId(), "Ids should be equal");
		assertEquals(100, changedPropertyManagerInfo.getPaymentAmount(), "Payment amount should be equal");
		verify(propertyManagerInfoDao, atLeast(1)).update(any(PropertyManagerInfo.class));
		verify(propertyManagerInfoDao, atLeast(2)).readById(any(Integer.class));
	}

	@Test
	void testDeleteWithNull() {
		assertThrows(ValidationException.class, () -> propertyManagerInfoService.delete(null), "Validation exception should be thrown");
	}

	@Test
	void testDeleteWithZeroAndNegativeId() {
		assertThrows(ValidationException.class, () -> propertyManagerInfoService.delete(0), "Validation exception should be thrown");
		assertThrows(ValidationException.class, () -> propertyManagerInfoService.delete(-1), "Validation exception should be thrown");
	}

	@Test
	void testDelete() throws ValidationException {
		assertNotEquals(PropertyManagerState.Suspended, propertyManagerInfoService.readById(PropertyManagerInfoData.getDeletedId())
				.getState(), "Property manager state should not be suspended before delete");

		propertyManagerInfoService.delete(PropertyManagerInfoData.getDeletedId());
		PropertyManagerInfo removedPropertyManagerInfo = propertyManagerInfoService.readById(PropertyManagerInfoData.getDeletedId());

		assertEquals(PropertyManagerInfoData.getDeletedId(), removedPropertyManagerInfo.getId(), "Ids should be equal");
		assertEquals(PropertyManagerState.Suspended, removedPropertyManagerInfo.getState(), "PropertyManagerInfo state should be changed to Suspended");
		verify(propertyManagerInfoDao, atLeast(1)).deleteById(any(Integer.class));
		verify(propertyManagerInfoDao, atLeast(2)).readById(any(Integer.class));
	}
}
