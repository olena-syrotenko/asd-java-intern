package team.asd.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import team.asd.constants.ChannelPartnerState;
import team.asd.dao.ChannelPartnerDao;
import team.asd.data.ChannelPartnerData;
import team.asd.entity.ChannelPartner;
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
class ChannelPartnerServiceTest {
	@Mock
	private ChannelPartnerDao channelPartnerDao;
	private ChannelPartnerService channelPartnerService;
	private ChannelPartner channelPartner;
	private ChannelPartner createdChannelPartner;
	private ChannelPartner updatedChannelPartner;
	private ChannelPartner deletedChannelPartner;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		channelPartnerService = new ChannelPartnerService(channelPartnerDao);
		channelPartner = new ChannelPartner();
		createdChannelPartner = null;
		updatedChannelPartner = ChannelPartnerData.getChannelPartnerBeforeUpdate();
		deletedChannelPartner = ChannelPartnerData.getChannelPartnerBeforeDelete();

		when(channelPartnerDao.readById(1)).thenReturn(ChannelPartner.builder()
				.id(1)
				.build());
		when(channelPartnerDao.readById(ChannelPartnerData.getCreatedId())).thenAnswer(invocation -> createdChannelPartner);
		when(channelPartnerDao.readById(ChannelPartnerData.getUpdatedId())).thenAnswer(invocation -> updatedChannelPartner);
		when(channelPartnerDao.readById(ChannelPartnerData.getDeletedId())).thenAnswer(invocation -> deletedChannelPartner);

		doAnswer(invocation -> {
			createdChannelPartner = ChannelPartnerData.getChannelPartnerAfterCreate();
			return null;
		}).when(channelPartnerDao)
				.create(any(ChannelPartner.class));

		doAnswer(invocation -> {
			updatedChannelPartner = ChannelPartnerData.getChannelPartnerAfterUpdate();
			return null;
		}).when(channelPartnerDao)
				.update(any(ChannelPartner.class));

		doAnswer(invocation -> {
			deletedChannelPartner = ChannelPartnerData.getChannelPartnerAfterDelete();
			return null;
		}).when(channelPartnerDao)
				.deleteById(any(Integer.class));
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
		ChannelPartner readChannelPartner = channelPartnerService.readById(1);
		assertNotNull(readChannelPartner, "ChannelPartner object should be returned");
		assertEquals(1, readChannelPartner.getId(), "Ids should be equal");
		assertNull(channelPartnerService.readById(100), "Null value should be returned");
		verify(channelPartnerDao, atLeast(2)).readById(any(Integer.class));
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
		assertNull(channelPartnerService.readById(ChannelPartnerData.getCreatedId()), "Created channel partner object should be null before create");

		channelPartner.setPartyId(1);
		channelPartner.setChannelName("created channel partner");
		channelPartner.setAbbreviation("abbr");
		channelPartnerService.create(channelPartner);
		ChannelPartner insertedChannelPartner = channelPartnerService.readById(ChannelPartnerData.getCreatedId());

		assertNotNull(insertedChannelPartner, "Created ChannelPartner object should be returned");
		assertEquals(ChannelPartnerData.getCreatedId(), insertedChannelPartner.getId(), "Ids should be equal");
		assertEquals("created channel partner", insertedChannelPartner.getChannelName(), "Channel names should be equal");
		verify(channelPartnerDao, atLeast(1)).create(any(ChannelPartner.class));
		verify(channelPartnerDao, atLeast(2)).readById(any(Integer.class));
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
		assertNotEquals("updated channel name", channelPartnerService.readById(ChannelPartnerData.getUpdatedId())
				.getChannelName(), "Channel name should not be changed before update");

		channelPartner.setId(ChannelPartnerData.getUpdatedId());
		channelPartner.setChannelName("updated channel name");
		channelPartnerService.update(channelPartner);
		ChannelPartner changedChannelPartner = channelPartnerService.readById(ChannelPartnerData.getUpdatedId());

		assertEquals(ChannelPartnerData.getUpdatedId(), changedChannelPartner.getId(), "Ids should be equal");
		assertEquals("updated channel name", changedChannelPartner.getChannelName(), "Channel name should be updated");
		verify(channelPartnerDao, atLeast(1)).update(any(ChannelPartner.class));
		verify(channelPartnerDao, atLeast(2)).readById(any(Integer.class));
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
		assertNotEquals(ChannelPartnerState.Suspended, channelPartnerService.readById(ChannelPartnerData.getDeletedId())
				.getState(), "Channel partner state should not be suspended before delete");

		channelPartnerService.delete(ChannelPartnerData.getDeletedId());
		ChannelPartner removedChannelPartner = channelPartnerService.readById(ChannelPartnerData.getDeletedId());

		assertEquals(ChannelPartnerData.getDeletedId(), removedChannelPartner.getId(), "Ids should be equal");
		assertEquals(ChannelPartnerState.Suspended, removedChannelPartner.getState(), "ChannelPartner state should be changed to Suspended");
		verify(channelPartnerDao, atLeast(1)).deleteById(any(Integer.class));
		verify(channelPartnerDao, atLeast(2)).readById(any(Integer.class));
	}
}
