package team.asd.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import team.asd.dto.PartyReportDto;
import team.asd.service.PartyService;

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
		return partyService.readReportById(partyId);
	}
}
