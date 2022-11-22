package team.asd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.asd.dto.ChannelPartnerDto;
import team.asd.entity.ChannelPartner;
import team.asd.service.ChannelPartnerService;
import team.asd.util.ChannelPartnerUtil;

import javax.validation.Valid;

@RestController
@RequestMapping("/channel_partner")
public class ChannelPartnerController {
	private final ChannelPartnerService channelPartnerService;

	@Autowired
	public ChannelPartnerController(ChannelPartnerService channelPartnerService) {
		this.channelPartnerService = channelPartnerService;
	}

	@GetMapping("/{id}")
	public ChannelPartnerDto getChannelPartnerById(@PathVariable Integer id) {
		return ChannelPartnerUtil.convertToDto(channelPartnerService.readById(id));
	}

	@PostMapping("/")
	public ChannelPartnerDto createChannelPartner(@RequestBody @Valid ChannelPartnerDto channelPartnerDto) {
		ChannelPartner channelPartner = ChannelPartnerUtil.convertToEntity(channelPartnerDto);
		channelPartnerService.create(channelPartner);
		return ChannelPartnerUtil.convertToDto(channelPartner);
	}

	@PutMapping("/")
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

	@DeleteMapping("/{id}")
	public Integer deleteChannelPartnerById(@PathVariable Integer id) {
		channelPartnerService.delete(id);
		return id;
	}
}
