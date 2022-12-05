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

	@GetMapping("/channel-partner/{id}")
	public ChannelPartnerDto getChannelPartnerById(@PathVariable Integer id) {
		return ChannelPartnerUtil.convertToDto(channelPartnerService.readById(id));
	}

	@GetMapping("/channel-partners")
	public List<ChannelPartnerDto> getChannelPartnersByAbbreviationMask(@RequestParam(name = "abbreviationMask") String abbreviationMask) {
		return channelPartnerService.readByAbbreviationMask(abbreviationMask)
				.stream()
				.map(ChannelPartnerUtil::convertToDto)
				.collect(Collectors.toList());
	}

	@GetMapping("/channel-partner")
	public ChannelPartnerDto getChannelPartnerByPartyIdState(@RequestParam(name = "partyId", required = false) Integer partyId,
			@RequestParam(name = "state", required = false) String state) {
		return ChannelPartnerUtil.convertToDto(channelPartnerService.readByPartyIdState(partyId, state));
	}

	@GetMapping("/manager-to-channel")
	public ManagerToChannelDto readManagerByPropertyManagerIdChannelPartnerId(
			@RequestParam(name = "propertyManagerId", required = false) Integer propertyManagerId,
			@RequestParam(name = "channelPartnerId", required = false) Integer channelPartnerId) {

		return ManagerToChannelUtil.convertToDto(channelPartnerService.readManagerByPropManagerIdChanPartnerId(propertyManagerId, channelPartnerId));
	}

	@GetMapping("/managers-to-channel")
	public List<ManagerToChannelDto> readManagersByChannelPartnerIdNetRate(@RequestParam(name = "channelPartnerId") Integer channelPartnerId,
			@RequestParam(name = "netRate", required = false) Integer netRate) {
		return channelPartnerService.readManagersByChannelPartnerIdNetRate(channelPartnerId, netRate)
				.stream()
				.map(ManagerToChannelUtil::convertToDto)
				.collect(Collectors.toList());
	}

	@PostMapping("/channel-partner/")
	public ChannelPartnerDto createChannelPartner(@RequestBody @Valid ChannelPartnerDto channelPartnerDto) {
		ChannelPartner channelPartner = ChannelPartnerUtil.convertToEntity(channelPartnerDto);
		channelPartnerService.create(channelPartner);
		return ChannelPartnerUtil.convertToDto(channelPartner);
	}

	@PostMapping("/managers-to-channels/")
	public List<ManagerToChannelDto> createManagerToChannels(@RequestBody List<@Valid ManagerToChannelDto> managerToChannelDto) {
		List<ManagerToChannel> managerToChannels = managerToChannelDto.stream()
				.map(ManagerToChannelUtil::convertToEntity)
				.collect(Collectors.toList());
		channelPartnerService.createManagerToChannels(managerToChannels);
		return managerToChannels.stream()
				.map(ManagerToChannelUtil::convertToDto)
				.collect(Collectors.toList());
	}

	@PutMapping("/channel-partner/")
	public ChannelPartnerDto updateChannelPartner(@RequestBody @Valid ChannelPartnerDto channelPartnerDto) {
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

	@DeleteMapping("/channel-partner/{id}")
	public Integer deleteChannelPartnerById(@PathVariable Integer id) {
		channelPartnerService.delete(id);
		return id;
	}
}
