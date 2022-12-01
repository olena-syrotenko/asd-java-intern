package team.asd.controller;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.asd.constants.PartyState;
import team.asd.constants.UserType;
import team.asd.dto.PartyDto;
import team.asd.entity.Party;
import team.asd.service.PartyService;
import team.asd.util.PartyUtil;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PartyController {
	private final PartyService partyService;

	@Autowired
	public PartyController(PartyService partyService) {
		this.partyService = partyService;
	}

	@GetMapping("/party/{id}")
	public PartyDto getPartyById(@PathVariable Integer id) {
		return PartyUtil.convertToDto(partyService.readById(id));
	}

	@GetMapping("/parties-by-name")
	public List<PartyDto> getPartyByUserTypeNameAndState(@RequestParam(name = "name") String name,
			@RequestParam(name = "userType", required = false) String userType, @RequestParam(name = "state", required = false) String state) {
		Party partyParameters = Party.builder()
				.userType(EnumUtils.getEnumIgnoreCase(UserType.class, userType))
				.name(name)
				.state(EnumUtils.getEnumIgnoreCase(PartyState.class, state))
				.build();
		return partyService.readByUserTypeNameAndState(partyParameters)
				.stream()
				.map(PartyUtil::convertToDto)
				.collect(Collectors.toList());
	}

	@GetMapping("/parties-by-email")
	public List<PartyDto> getPartyByEmailUserTypeNameAndState(@RequestParam(name = "emailAddress") String emailAddress,
			@RequestParam(name = "name", required = false) String name, @RequestParam(name = "userType", required = false) String userType,
			@RequestParam(name = "state", required = false) String state) {
		Party partyParameters = Party.builder()
				.emailAddress(emailAddress)
				.userType(EnumUtils.getEnumIgnoreCase(UserType.class, userType))
				.name(name)
				.state(EnumUtils.getEnumIgnoreCase(PartyState.class, state))
				.build();
		return partyService.readByEmailUserTypeNameAndState(partyParameters)
				.stream()
				.map(PartyUtil::convertToDto)
				.collect(Collectors.toList());
	}

	@PostMapping("/party/")
	public PartyDto createParty(@RequestBody @Valid PartyDto partyDto) {
		Party party = PartyUtil.convertToEntity(partyDto);
		partyService.create(party);
		return PartyUtil.convertToDto(party);
	}

	@PutMapping("/party/")
	public PartyDto updateParty(@RequestBody @Valid PartyDto partyDto) {
		Party party = PartyUtil.convertToEntity(partyDto);
		if (partyDto.getState() == null) {
			party.setState(null);
		}
		partyService.update(party);
		return PartyUtil.convertToDto(party);
	}

	@DeleteMapping("/party/{id}")
	public Integer deleteParty(@PathVariable Integer id) {
		partyService.delete(id);
		return id;
	}
}
