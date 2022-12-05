package team.asd.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@ApiOperation("Party API")
public class PartyController {
	private final PartyService partyService;

	@Autowired
	public PartyController(PartyService partyService) {
		this.partyService = partyService;
	}

	@ApiOperation(value = "Get a party by id", notes = "Returns a party as per the id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 400, message = "Bad request - The request was malformed") })
	@GetMapping("/party/{id}")
	public PartyDto getPartyById(@PathVariable @ApiParam(name = "id", value = "Party id", example = "1") Integer id) {
		return PartyUtil.convertToDto(partyService.readById(id));
	}

	@ApiOperation(value = "Get parties by name, user type and state", notes = "Returns a party as per name, user type and state")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 400, message = "Bad request - The request was malformed") })
	@GetMapping("/parties-by-name")
	public List<PartyDto> getPartyByUserTypeNameState(
			@RequestParam(name = "userType", required = false) @ApiParam(name = "userType", value = "User type", example = "customer") String userType,
			@RequestParam(name = "name") @ApiParam(name = "name", value = "Name of party", example = "test party") String name,
			@RequestParam(name = "state", required = false) @ApiParam(name = "state", value = "Party state", example = "created") String state) {
		return partyService.readByUserTypeNameState(userType, name, state)
				.stream()
				.map(PartyUtil::convertToDto)
				.collect(Collectors.toList());
	}

	@ApiOperation(value = "Get parties by email, name, user type and state", notes = "Returns a party as per email, name, user type and state")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 400, message = "Bad request - The request was malformed") })
	@GetMapping("/parties-by-email")
	public List<PartyDto> getPartyByEmailUserTypeNameState(
			@RequestParam(name = "emailAddress") @ApiParam(name = "emailAddress", value = "Email address of party", example = "john.smith@example.com") String emailAddress,
			@RequestParam(name = "userType", required = false) @ApiParam(name = "userType", value = "User type", example = "customer") String userType,
			@RequestParam(name = "name", required = false) @ApiParam(name = "name", value = "Name of party", example = "test party") String name,
			@RequestParam(name = "state", required = false) @ApiParam(name = "state", value = "Party state", example = "created") String state) {
		return partyService.readByEmailUserTypeNameState(emailAddress, userType, name, state)
				.stream()
				.map(PartyUtil::convertToDto)
				.collect(Collectors.toList());
	}

	@ApiOperation(value = "Create a new party", notes = "Returns a party with create options")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully created"),
			@ApiResponse(code = 400, message = "Bad request - The request was malformed and party was not created") })
	@PostMapping("/party/")
	public PartyDto createParty(@RequestBody @Valid @ApiParam(name = "partyDto", value = "Party object that needs to be saved") PartyDto partyDto) {
		Party party = PartyUtil.convertToEntity(partyDto);
		partyService.create(party);
		return PartyUtil.convertToDto(party);
	}

	@ApiOperation(value = "Update a party", notes = "Returns a party with update options")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated"),
			@ApiResponse(code = 400, message = "Bad request - The request was malformed and party was not updated") })
	@PutMapping("/party/")
	public PartyDto updateParty(@RequestBody @Valid @ApiParam(name = "partyDto", value = "Party information that needs to be updated") PartyDto partyDto) {
		Party party = PartyUtil.convertToEntity(partyDto);
		if (partyDto.getState() == null) {
			party.setState(null);
		}
		partyService.update(party);
		return PartyUtil.convertToDto(party);
	}

	@ApiOperation(value = "Delete a party", notes = "Returns a party id that was deleted")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully deleted"),
			@ApiResponse(code = 400, message = "Bad request - The request was malformed and party was not deleted") })
	@DeleteMapping("/party/{id}")
	public Integer deleteParty(@PathVariable @ApiParam(name = "id", value = "Party id", example = "1") Integer id) {
		partyService.delete(id);
		return id;
	}
}
