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
import team.asd.dto.ChannelPartnerDto;
import team.asd.dto.ManagerToChannelDto;
import team.asd.entity.ChannelPartner;
import team.asd.entity.ManagerToChannel;
import team.asd.service.ChannelPartnerService;
import team.asd.util.ChannelPartnerUtil;
import team.asd.util.ManagerToChannelUtil;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ChannelPartnerController {
	private final ChannelPartnerService channelPartnerService;

	@Autowired
	public ChannelPartnerController(ChannelPartnerService channelPartnerService) {
		this.channelPartnerService = channelPartnerService;
	}

	@ApiOperation(value = "Get a channel partner by id", notes = "Returns a channel partner as per the id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 400, message = "Bad request - The request was malformed") })
	@GetMapping("/channel-partner/{id}")
	public ChannelPartnerDto getChannelPartnerById(@PathVariable @ApiParam(name = "id", value = "Channel partner id", example = "1") Integer id) {
		return ChannelPartnerUtil.convertToDto(channelPartnerService.readById(id));
	}

	@ApiOperation(value = "Get a channel partner by abbreviation", notes = "Returns a channel partner as per the abbreviation")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 400, message = "Bad request - The request was malformed") })
	@GetMapping("/channel-partners")
	public List<ChannelPartnerDto> getChannelPartnersByAbbreviationMask(
			@RequestParam(name = "abbreviationMask") @ApiParam(name = "abbreviationMask", value = "Abbreviation of channel", example = "ABBR") String abbreviationMask) {
		return channelPartnerService.readByAbbreviationMask(abbreviationMask)
				.stream()
				.map(ChannelPartnerUtil::convertToDto)
				.collect(Collectors.toList());
	}

	@ApiOperation(value = "Get a channel partner by party id and state", notes = "Returns a channel partner as per the party id and state")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 400, message = "Bad request - The request was malformed") })
	@GetMapping("/channel-partner")
	public ChannelPartnerDto getChannelPartnerByPartyIdState(
			@RequestParam(name = "partyId", required = false) @ApiParam(name = "partyId", value = "Party id", example = "1") Integer partyId,
			@RequestParam(name = "state", required = false) @ApiParam(name = "state", value = "Channel partner state", example = "Created") String state) {
		return ChannelPartnerUtil.convertToDto(channelPartnerService.readByPartyIdState(partyId, state));
	}

	@ApiOperation(value = "Get a manager to channel by PM id and channel partner id", notes = "Returns a manager to channel as per the PM id and channel partner id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 400, message = "Bad request - The request was malformed") })
	@GetMapping("/manager-to-channel")
	public ManagerToChannelDto readManagerByPropertyManagerIdChannelPartnerId(
			@RequestParam(name = "propertyManagerId", required = false) @ApiParam(name = "propertyManagerId", value = "Property manager id", example = "1") Integer propertyManagerId,
			@RequestParam(name = "channelPartnerId", required = false) @ApiParam(name = "channelPartnerId", value = "Channel partner id", example = "2") Integer channelPartnerId) {

		return ManagerToChannelUtil.convertToDto(channelPartnerService.readManagerByPropManagerIdChanPartnerId(propertyManagerId, channelPartnerId));
	}

	@ApiOperation(value = "Get a managers to channel by channel partner id and net rate settings", notes = "Returns a managers to channel as per the channel partner id and net rate")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 400, message = "Bad request - The request was malformed") })
	@GetMapping("/managers-to-channel")
	public List<ManagerToChannelDto> readManagersByChannelPartnerIdNetRate(
			@RequestParam(name = "channelPartnerId") @ApiParam(name = "channelPartnerId", value = "Channel partner id", example = "2") Integer channelPartnerId,
			@RequestParam(name = "netRate", required = false) @ApiParam(name = "netRate", value = "Net rate settings", example = "0") Integer netRate) {
		return channelPartnerService.readManagersByChannelPartnerIdNetRate(channelPartnerId, netRate)
				.stream()
				.map(ManagerToChannelUtil::convertToDto)
				.collect(Collectors.toList());
	}

	@ApiOperation(value = "Create a new channel partner", notes = "Returns a channel partner with create options")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully created"),
			@ApiResponse(code = 400, message = "Bad request - The request was malformed and channel partner was not created") })
	@PostMapping("/channel-partner/")
	public ChannelPartnerDto createChannelPartner(
			@RequestBody @Valid @ApiParam(name = "channelPartnerDto", value = "Channel partner object that needs to be saved") ChannelPartnerDto channelPartnerDto) {
		ChannelPartner channelPartner = ChannelPartnerUtil.convertToEntity(channelPartnerDto);
		channelPartnerService.create(channelPartner);
		return ChannelPartnerUtil.convertToDto(channelPartner);
	}

	@ApiOperation(value = "Save a list of managers to channels", notes = "Returns a list of managers to channels with create options")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully created"),
			@ApiResponse(code = 400, message = "Bad request - The request was malformed and managers to channel were not created") })
	@PostMapping("/managers-to-channels/")
	public List<ManagerToChannelDto> createManagerToChannels(
			@RequestBody @ApiParam(name = "managerToChannelDto", value = "Managers to channels list that needs to be saved") List<@Valid ManagerToChannelDto> managerToChannelDto) {
		List<ManagerToChannel> managerToChannels = managerToChannelDto.stream()
				.map(ManagerToChannelUtil::convertToEntity)
				.collect(Collectors.toList());
		channelPartnerService.createManagerToChannels(managerToChannels);
		return managerToChannels.stream()
				.map(ManagerToChannelUtil::convertToDto)
				.collect(Collectors.toList());
	}

	@ApiOperation(value = "Update a channel partner", notes = "Returns a channel partner with update options")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully created"),
			@ApiResponse(code = 400, message = "Bad request - The request was malformed and channel partner was not updated") })
	@PutMapping("/channel-partner/")
	public ChannelPartnerDto updateChannelPartner(
			@RequestBody @Valid @ApiParam(name = "channelPartnerDto", value = "Channel partner information that needs to be updated") ChannelPartnerDto channelPartnerDto) {
		ChannelPartner channelPartner = ChannelPartnerUtil.convertToEntity(channelPartnerDto);
		if (channelPartnerDto.getState() == null) {
			channelPartner.setState(null);
		}
		if (channelPartnerDto.getBpCommission() == null) {
			channelPartner.setBpCommission(null);
		}
		if (channelPartnerDto.getIsFundsHolder() == null) {
			channelPartner.setIsFundsHolder(null);
		}
		channelPartnerService.update(channelPartner);
		return ChannelPartnerUtil.convertToDto(channelPartner);
	}

	@ApiOperation(value = "Delete a channel partner", notes = "Returns an id of channel partner that was deleted")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully deleted"),
			@ApiResponse(code = 400, message = "Bad request - The request was malformed and channel partner was not deleted") })
	@DeleteMapping("/channel-partner/{id}")
	public Integer deleteChannelPartnerById(@PathVariable Integer id) {
		channelPartnerService.delete(id);
		return id;
	}
}
