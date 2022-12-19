package team.asd.service;

import team.asd.data.PartyData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import team.asd.constants.PartyState;
import team.asd.dao.PartyDao;
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
	private static Party mockParty;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		partyService = new PartyService(partyDao);
		party = new Party();
		mockParty = null;
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
		when(partyDao.readById(1)).thenReturn(Party.builder()
				.id(1)
				.build());
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
		doAnswer(invocation -> {
			mockParty = PartyData.getPartyAfterCreate(invocation.getArgument(0, Party.class));
			return null;
		}).when(partyDao)
				.create(any(Party.class));

		assertNull(mockParty, "Created party object should be null before create");

		party.setName("created party");
		partyService.create(party);

		assertNotNull(mockParty, "Created party object should be returned");
		assertEquals(PartyData.getCreatedId(), mockParty.getId(), "Ids should be equal");
		assertEquals("created party", mockParty.getName(), "Names should be equal");
		verify(partyDao, atLeast(1)).create(any(Party.class));
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
		mockParty = Party.builder()
				.id(1)
				.name("test")
				.build();

		doAnswer(invocation -> {
			mockParty = PartyData.getPartyAfterUpdate(invocation.getArgument(0, Party.class));
			return null;
		}).when(partyDao)
				.update(any(Party.class));

		assertNotEquals("updated party", mockParty.getName(), "Party name should not be changed before update");

		party.setId(1);
		party.setName("updated party");
		partyService.update(party);

		assertEquals(party.getId(), mockParty.getId(), "Ids should be equal");
		assertEquals(party.getName(), mockParty.getName(), "Party name should be updated");
		verify(partyDao, atLeast(1)).update(any(Party.class));
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
		mockParty = Party.builder()
				.id(1)
				.state(PartyState.Created)
				.build();

		doAnswer(invocation -> {
			mockParty = PartyData.getPartyAfterDelete(invocation.getArgument(0, Integer.class));
			return null;
		}).when(partyDao)
				.deleteById(any(Integer.class));

		assertNotEquals(PartyState.Final, mockParty.getState(), "State should not be final before delete");

		partyService.delete(1);

		assertEquals(1, mockParty.getId(), "Ids should be equal");
		assertEquals(PartyState.Final, mockParty.getState(), "Party state should be changed to Final");
		verify(partyDao, atLeast(1)).deleteById(any(Integer.class));
	}
}
