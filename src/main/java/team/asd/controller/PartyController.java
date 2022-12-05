package team.asd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
	public List<PartyDto> getPartyByUserTypeNameState(@RequestParam(name = "userType", required = false) String userType,
			@RequestParam(name = "name") String name, @RequestParam(name = "state", required = false) String state) {
		return partyService.readByUserTypeNameState(userType, name, state)
				.stream()
				.map(PartyUtil::convertToDto)
				.collect(Collectors.toList());
	}

	@GetMapping("/parties-by-email")
	public List<PartyDto> getPartyByEmailUserTypeNameState(@RequestParam(name = "emailAddress") String emailAddress,
			@RequestParam(name = "userType", required = false) String userType, @RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "state", required = false) String state) {
		return partyService.readByEmailUserTypeNameState(emailAddress, userType, name, state)
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
