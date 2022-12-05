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
	private static PropertyManagerInfo mockPropertyManagerInfo;

	@BeforeEach
	public void initPropertyManagerInfo() {
		MockitoAnnotations.openMocks(this);
		propertyManagerInfoService = new PropertyManagerInfoService(propertyManagerInfoDao);
		propertyManagerInfo = new PropertyManagerInfo();
		mockPropertyManagerInfo = null;
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
		when(propertyManagerInfoDao.readById(1)).thenReturn(PropertyManagerInfo.builder()
				.id(1)
				.build());
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
		doAnswer(invocation -> {
			mockPropertyManagerInfo = PropertyManagerInfoData.getPropertyManagerInfoAfterCreate(invocation.getArgument(0, PropertyManagerInfo.class));
			return null;
		}).when(propertyManagerInfoDao)
				.create(any(PropertyManagerInfo.class));

		assertNull(mockPropertyManagerInfo, "Created property manager info object should be null before create");

		propertyManagerInfo.setPmId(1);
		propertyManagerInfoService.create(propertyManagerInfo);

		assertNotNull(mockPropertyManagerInfo, "Created PropertyManagerInfo object should be returned");
		assertEquals(PropertyManagerInfoData.getCreatedId(), mockPropertyManagerInfo.getId(), "Ids should be equal");
		assertEquals(1, mockPropertyManagerInfo.getPmId(), "Pm ids should be equal");
		verify(propertyManagerInfoDao, atLeast(1)).create(any(PropertyManagerInfo.class));
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
		mockPropertyManagerInfo = PropertyManagerInfo.builder()
				.id(1)
				.paymentAmount(100.0)
				.build();
		doAnswer(invocation -> {
			mockPropertyManagerInfo = PropertyManagerInfoData.getPropertyManagerInfoAfterUpdate(invocation.getArgument(0, PropertyManagerInfo.class));
			return null;
		}).when(propertyManagerInfoDao)
				.update(any(PropertyManagerInfo.class));

		assertNotEquals(500, mockPropertyManagerInfo.getPaymentAmount(), "Payment amount should not be changed before update");

		propertyManagerInfo.setId(1);
		propertyManagerInfo.setPaymentAmount(500.0);
		propertyManagerInfoService.update(propertyManagerInfo);

		assertEquals(1, mockPropertyManagerInfo.getId(), "Ids should be equal");
		assertEquals(500, mockPropertyManagerInfo.getPaymentAmount(), "Payment amount should be equal");
		verify(propertyManagerInfoDao, atLeast(1)).update(any(PropertyManagerInfo.class));
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
		mockPropertyManagerInfo = PropertyManagerInfo.builder()
				.id(1)
				.state(PropertyManagerState.Created)
				.build();
		doAnswer(invocation -> {
			mockPropertyManagerInfo = PropertyManagerInfoData.getPropertyManagerInfoAfterDelete(invocation.getArgument(0, Integer.class));
			return null;
		}).when(propertyManagerInfoDao)
				.deleteById(any(Integer.class));
		assertNotEquals(PropertyManagerState.Suspended, mockPropertyManagerInfo.getState(), "Property manager state should not be suspended before delete");

		propertyManagerInfoService.delete(1);

		assertEquals(1, mockPropertyManagerInfo.getId(), "Ids should be equal");
		assertEquals(PropertyManagerState.Suspended, mockPropertyManagerInfo.getState(), "PropertyManagerInfo state should be changed to Suspended");
		verify(propertyManagerInfoDao, atLeast(1)).deleteById(any(Integer.class));
	}
}
