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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class PartyServiceTest {
	@Mock
	private PartyDao partyDao;
	private PartyService partyService;
	private Party party;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		partyService = new PartyService(partyDao);
		party = new Party();

		doAnswer(invocation -> {
			party = PartyData.getPartyAfterCreate();
			return null;
		}).when(partyDao)
				.create(any(Party.class));

		doAnswer(invocation -> {
			party = PartyData.getPartyAfterUpdate();
			return null;
		}).when(partyDao)
				.update(any(Party.class));

		doAnswer(invocation -> {
			party = PartyData.getPartyAfterDelete();
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
		when(partyDao.readById(1)).thenReturn(Party.builder()
				.id(1)
				.build());

		assertNotNull(partyService.readById(1), "Party object should be returned");
		assertEquals(1, partyService.readById(1)
				.getId(), "Id should be equal");
		assertNull(partyService.readById(100), "Null should be returned");
		verify(partyDao, times(3)).readById(any(Integer.class));
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
		party.setName("test name");
		partyService.create(party);
		when(partyDao.readById(PartyData.getCreatedId())).thenReturn(party);

		assertNotNull(partyService.readById(PartyData.getCreatedId()));
		assertEquals(PartyData.getCreatedId(), partyService.readById(PartyData.getCreatedId())
				.getId());
		verify(partyDao).create(any(Party.class));
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
		party.setId(PartyData.getUpdatedId());
		partyService.update(party);
		when(partyDao.readById(party.getId())).thenReturn(party);

		assertNotNull(partyService.readById(party.getId()));
		assertEquals("updated party", partyService.readById(party.getId())
				.getName());
		verify(partyDao).update(any(Party.class));
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
		partyService.delete(PartyData.getDeletedId());
		when(partyDao.readById(PartyData.getDeletedId())).thenReturn(party);

		assertNotNull(partyService.readById(PartyData.getDeletedId()));
		assertEquals(PartyState.Final, partyService.readById(PartyData.getDeletedId())
				.getState());
		verify(partyDao).deleteById(any(Integer.class));
	}
}
