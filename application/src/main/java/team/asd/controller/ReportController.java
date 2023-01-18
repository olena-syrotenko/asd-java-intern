package team.asd.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.asd.dto.PartyReportDto;
import team.asd.service.PartyService;
import team.asd.util.PartyUtil;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@ApiOperation("Report API")
public class ReportController {
	private final PartyService partyService;

	@Autowired
	public ReportController(PartyService partyService) {
		this.partyService = partyService;
	}

	@ApiOperation(value = "Get the party report by party id", notes = "For valid response provide id >= 1. Returns a party information as per the id with additional information about managers and channels that are related to the party")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"), @ApiResponse(code = 400, message = "Wrong id was provided") })
	@GetMapping("/report/party/{partyId}")
	public PartyReportDto getPartyReportById(@PathVariable @ApiParam(name = "partyId", value = "Party id", example = "1") Integer partyId) {
		return PartyUtil.convertToReportDto(partyService.readReportById(partyId));
	}

	@ApiOperation(value = "Get party reports list by page number and items per page value", notes = "For valid response provide parameters >= 1. Returns a list of party reports for pagination as per the page number and items per page value")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 400, message = "Wrong parameters were provided") })
	@GetMapping("/report/parties")
	public List<PartyReportDto> getPartyReportsByPageItemsPerPage(
			@RequestParam(name = "page") @ApiParam(name = "page", value = "Page number", example = "3") Integer page,
			@RequestParam(name = "items") @ApiParam(name = "items", value = "Number of items per page", example = "5") Integer items) {
		return partyService.readReportByPageItems(page, items)
				.stream()
				.map(PartyUtil::convertToReportDto)
				.collect(Collectors.toList());
	}
}
