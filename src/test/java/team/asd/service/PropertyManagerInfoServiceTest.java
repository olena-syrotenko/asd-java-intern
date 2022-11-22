package team.asd.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import team.asd.constants.PaymentNumber;
import team.asd.constants.PaymentType;
import team.asd.constants.PropertyManagerState;
import team.asd.dao.PropertyManagerInfoDao;
import team.asd.dao.TestPropertyManagerInfoDao;
import team.asd.entity.PropertyManagerInfo;
import team.asd.exceptions.ValidationException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PropertyManagerInfoServiceTest {
	private static final PropertyManagerInfoDao propertyManagerInfoDao = new TestPropertyManagerInfoDao();
	private static PropertyManagerInfoService propertyManagerInfoService;
	private PropertyManagerInfo propertyManagerInfo;

	@BeforeAll
	public static void setUp() {
		propertyManagerInfoService = new PropertyManagerInfoService(propertyManagerInfoDao);
	}

	@BeforeEach
	public void initPropertyManagerInfo() {
		propertyManagerInfo = PropertyManagerInfo.builder()
				.pmId(2)
				.isFundsHolder(false)
				.numberOfPayments(PaymentNumber.SplitPayment)
				.paymentAmount(1000.0)
				.paymentType(PaymentType.FlatFee)
				.state(PropertyManagerState.Created)
				.build();
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
		assertNull(propertyManagerInfoService.readById(1), "Null value should be returned");
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
		assertDoesNotThrow(() -> propertyManagerInfoService.create(propertyManagerInfo), "Validation should be passed");
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
		propertyManagerInfo.setId(10);
		assertDoesNotThrow(() -> propertyManagerInfoService.update(propertyManagerInfo), "Validation should be passed");
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
		assertDoesNotThrow(() -> propertyManagerInfoService.delete(1), "Validation should be passed");
	}
}
