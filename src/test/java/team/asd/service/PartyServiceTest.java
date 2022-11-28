package team.asd.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import team.asd.constants.PartyState;
import team.asd.dao.PartyDao;
import team.asd.data.PartyData;
import team.asd.entity.Party;
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
class PartyServiceTest {
	@Mock
	private PartyDao partyDao;
	private PartyService partyService;
	private Party party;
	private Party createdParty;
	private Party updatedParty;
	private Party deletedParty;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		partyService = new PartyService(partyDao);
		party = new Party();
		createdParty = null;
		updatedParty = PartyData.getPartyBeforeUpdate();
		deletedParty = PartyData.getPartyBeforeDelete();

		when(partyDao.readById(1)).thenReturn(Party.builder()
				.id(1)
				.build());
		when(partyDao.readById(PartyData.getCreatedId())).thenAnswer(invocation -> createdParty);
		when(partyDao.readById(PartyData.getUpdatedId())).thenAnswer(invocation -> updatedParty);
		when(partyDao.readById(PartyData.getDeletedId())).thenAnswer(invocation -> deletedParty);

		doAnswer(invocation -> {
			createdParty = PartyData.getPartyAfterCreate();
			return null;
		}).when(partyDao)
				.create(any(Party.class));

		doAnswer(invocation -> {
			updatedParty = PartyData.getPartyAfterUpdate();
			return null;
		}).when(partyDao)
				.update(any(Party.class));

		doAnswer(invocation -> {
			deletedParty = PartyData.getPartyAfterDelete();
			return null;
		}).when(partyDao)
				.deleteById(any(Integer.class));
	}

	@Test
	void testReadByIdWithNull() {
		assertThrows(ValidationException.class, () -> partyService.readById(null), "Validation exception should be thrown");
	}

	@Test
	void testReadByIdWithZeroAndNegativeId() {
		assertThrows(ValidationException.class, () -> partyService.readById(0), "Validation exception should be thrown");
		assertThrows(ValidationException.class, () -> partyService.readById(-190), "Validation exception should be thrown");
	}

	@Test
	void testReadById() throws ValidationException {
		Party readParty = partyService.readById(1);
		assertNotNull(readParty, "Party object should be returned");
		assertEquals(1, readParty.getId(), "Ids should be equal");
		assertNull(partyService.readById(100), "Null should be returned");
		verify(partyDao, atLeast(2)).readById(any(Integer.class));
	}

	@Test
	void testCreateWithNull() {
		assertThrows(ValidationException.class, () -> partyService.create(null), "Validation exception should be thrown");
	}

	@Test
	void testCreateWithNullAndBlankName() {
		party.setName(null);
		assertThrows(ValidationException.class, () -> partyService.create(party), "Validation exception should be thrown");
		party.setName("  ");
		assertThrows(ValidationException.class, () -> partyService.create(party), "Validation exception should be thrown");
	}

	@Test
	void testCreate() throws ValidationException {
		assertNull(partyService.readById(PartyData.getCreatedId()), "Created party object should be null before create");

		party.setName("created party");
		partyService.create(party);
		Party insertedParty = partyService.readById(PartyData.getCreatedId());

		assertNotNull(insertedParty, "Created party object should be returned");
		assertEquals(PartyData.getCreatedId(), insertedParty.getId(), "Ids should be equal");
		assertEquals("created party", insertedParty.getName(), "Names should be equal");
		verify(partyDao, atLeast(1)).create(any(Party.class));
		verify(partyDao, atLeast(2)).readById(any(Integer.class));
	}

	@Test
	void testUpdateWithNull() {
		assertThrows(ValidationException.class, () -> partyService.update(null), "Validation exception should be thrown");
		assertThrows(ValidationException.class, () -> partyService.update(party), "Validation exception should be thrown");
	}

	@Test
	void testUpdateWithZeroAndNegativeId() {
		party.setId(0);
		assertThrows(ValidationException.class, () -> partyService.update(party), "Validation exception should be thrown");
		party.setId(-100);
		assertThrows(ValidationException.class, () -> partyService.update(party), "Validation exception should be thrown");
	}

	@Test
	void testUpdate() throws ValidationException {
		assertNotEquals("updated party", partyService.readById(PartyData.getUpdatedId())
				.getName(), "Party name should not be changed before update");

		party.setId(PartyData.getUpdatedId());
		party.setName("updated party");
		partyService.update(party);
		Party changedParty = partyService.readById(PartyData.getUpdatedId());

		assertEquals(PartyData.getUpdatedId(), changedParty.getId(), "Ids should be equal");
		assertEquals("updated party", changedParty.getName(), "Party name should be updated");
		verify(partyDao, atLeast(1)).update(any(Party.class));
		verify(partyDao, atLeast(2)).readById(any(Integer.class));
	}

	@Test
	void testDeleteWithNull() {
		assertThrows(ValidationException.class, () -> partyService.delete(null), "Validation exception should be thrown");
	}

	@Test
	void testDeleteWithZeroAndNegativeId() {
		assertThrows(ValidationException.class, () -> partyService.delete(0), "Validation exception should be thrown");
		assertThrows(ValidationException.class, () -> partyService.delete(-10), "Validation exception should be thrown");
	}

	@Test
	void testDelete() throws ValidationException {
		assertNotEquals(PartyState.Final, partyService.readById(PartyData.getDeletedId())
				.getState(), "State should not be final before delete");

		partyService.delete(PartyData.getDeletedId());
		Party removedParty = partyService.readById(PartyData.getDeletedId());

		assertEquals(PartyData.getDeletedId(), removedParty.getId(), "Ids should be equal");
		assertEquals(PartyState.Final, removedParty.getState(), "Party state should be changed to Final");
		verify(partyDao, atLeast(1)).deleteById(any(Integer.class));
		verify(partyDao, atLeast(2)).readById(any(Integer.class));
	}
}
