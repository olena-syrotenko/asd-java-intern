package team.asd.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import team.asd.dao.ChannelPartnerDao;
import team.asd.dao.ManagerToChannelDao;
import team.asd.dao.TestChannelPartnerDao;
import team.asd.dao.TestManagerToChannelDao;
import team.asd.entity.ChannelPartner;
import team.asd.exceptions.ValidationException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ChannelPartnerServiceTest {
	private static final ChannelPartnerDao channelPartnerDao = new TestChannelPartnerDao();
	private static final ManagerToChannelDao managerToChannelDao = new TestManagerToChannelDao();
	private static ChannelPartnerService channelPartnerService;
	private ChannelPartner channelPartner;

	@BeforeAll
	public static void setUp(){
		channelPartnerService = new ChannelPartnerService(channelPartnerDao, managerToChannelDao);
	}

	@BeforeEach
	public void initChannelPartner(){
		channelPartner = ChannelPartner.builder()
				.partyId(1)
				.channelName("test channel name")
				.abbreviation("test")
				.bpCommission(2.0)
				.isFundsHolder(true)
				.build();
	}

	@Test
	void testReadByIdWithNull() {
		assertThrows(ValidationException.class, () -> channelPartnerService.readById(null), "Validation exception should be thrown");
	}

	@Test
	void testReadByIdWithZeroAndNegativeId() {
		assertThrows(ValidationException.class, () -> channelPartnerService.readById(0), "Validation exception should be thrown");
		assertThrows(ValidationException.class, () -> channelPartnerService.readById(-1), "Validation exception should be thrown");
	}

	@Test
	void testReadById() throws ValidationException {
		assertNull(channelPartnerService.readById(10), "Null value should be returned");
	}

	@Test
	void testCreateWithNull() {
		assertThrows(ValidationException.class, () -> channelPartnerService.create(null), "Validation exception should be thrown");
	}

	@Test
	void testCreateWithNullAndZeroAndNegativePartyId() {
		channelPartner.setPartyId(null);
		assertThrows(ValidationException.class, () -> channelPartnerService.create(channelPartner), "Validation exception should be thrown");
		channelPartner.setPartyId(0);
		assertThrows(ValidationException.class, () -> channelPartnerService.create(channelPartner), "Validation exception should be thrown");
		channelPartner.setPartyId(-20);
		assertThrows(ValidationException.class, () -> channelPartnerService.create(channelPartner), "Validation exception should be thrown");
	}

	@Test
	void testCreateWithNullAndBlankChannelName() {
		channelPartner.setChannelName(null);
		assertThrows(ValidationException.class, () -> channelPartnerService.create(channelPartner), "Validation exception should be thrown");
		channelPartner.setChannelName("  ");
		assertThrows(ValidationException.class, () -> channelPartnerService.create(channelPartner), "Validation exception should be thrown");
	}

	@Test
	void testCreateWithNullAndBlankAbbreviation() {
		channelPartner.setAbbreviation(null);
		assertThrows(ValidationException.class, () -> channelPartnerService.create(channelPartner), "Validation exception should be thrown");
		channelPartner.setAbbreviation("  ");
		assertThrows(ValidationException.class, () -> channelPartnerService.create(channelPartner), "Validation exception should be thrown");
	}

	@Test
	void testCreate() throws ValidationException {
		assertDoesNotThrow(() -> channelPartnerService.create(channelPartner), "Validation should be passed");
	}

	@Test
	void testUpdateWithNull() {
		assertThrows(ValidationException.class, () -> channelPartnerService.update(null), "Validation exception should be thrown");
		assertThrows(ValidationException.class, () -> channelPartnerService.update(channelPartner), "Validation exception should be thrown");
	}

	@Test
	void testUpdateWithZeroAndNegativeId() {
		channelPartner.setId(0);
		assertThrows(ValidationException.class, () -> channelPartnerService.update(channelPartner), "Validation exception should be thrown");
		channelPartner.setId(-1);
		assertThrows(ValidationException.class, () -> channelPartnerService.update(channelPartner), "Validation exception should be thrown");
	}

	@Test
	void testUpdate() throws ValidationException {
		channelPartner.setId(1);
		assertDoesNotThrow(() -> channelPartnerService.update(channelPartner), "Validation should be passed");
	}

	@Test
	void testDeleteWithNull() {
		assertThrows(ValidationException.class, () -> channelPartnerService.delete(null), "Validation exception should be thrown");
	}


	@Test
	void testDeleteWithZeroAndNegativeId() {
		assertThrows(ValidationException.class, () -> channelPartnerService.delete(0), "Validation exception should be thrown");
		assertThrows(ValidationException.class, () -> channelPartnerService.delete(-1), "Validation exception should be thrown");
	}

	@Test
	void testDelete() throws ValidationException {
		assertDoesNotThrow(() -> channelPartnerService.delete(1), "Validation should be passed");
	}
}
