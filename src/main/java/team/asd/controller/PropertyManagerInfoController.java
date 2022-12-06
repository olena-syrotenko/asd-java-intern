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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.asd.dto.PropertyManagerInfoDto;
import team.asd.entity.PropertyManagerInfo;
import team.asd.service.PropertyManagerInfoService;
import team.asd.util.PropertyManagerInfoUtil;

import javax.validation.Valid;

@RestController
@RequestMapping("/property-manager-info")
public class PropertyManagerInfoController {
	private final PropertyManagerInfoService propertyManagerInfoService;

	@Autowired
	public PropertyManagerInfoController(PropertyManagerInfoService propertyManagerInfoService) {
		this.propertyManagerInfoService = propertyManagerInfoService;
	}

	@ApiOperation(value = "Get a property manager info by id", notes = "Returns a property manager as per the id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 400, message = "Bad request - The request was malformed") })
	@GetMapping("/{id}")
	public PropertyManagerInfoDto getPropertyManagerInfoById(
			@PathVariable @ApiParam(name = "id", value = "Property manager info id", example = "1") Integer id) {
		return PropertyManagerInfoUtil.convertToDto(propertyManagerInfoService.readById(id));
	}

	@ApiOperation(value = "Get property managers by PM id and state", notes = "Returns property managers as per the PM id and state")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 400, message = "Bad request - The request was malformed") })
	@GetMapping("")
	public PropertyManagerInfoDto getPropertyManagerInfoByPmIdState(
			@RequestParam(name = "pmId") @ApiParam(name = "pmId", value = "Related party id", example = "1") Integer pmId,
			@RequestParam(name = "state", required = false) @ApiParam(name = "state", value = "Property manager info state", example = "created") String state) {
		return PropertyManagerInfoUtil.convertToDto(propertyManagerInfoService.readByPmIdState(pmId, state));
	}

	@ApiOperation(value = "Create a new property manager info record", notes = "Returns a property manager with create options")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully created"),
			@ApiResponse(code = 400, message = "Bad request - The request was malformed and property manager info record was not created") })
	@PostMapping("/")
	public PropertyManagerInfoDto createPropertyManagerInfo(
			@RequestBody @Valid @ApiParam(name = "propertyManagerInfoDto", value = "Property manager info that needs to be saved") PropertyManagerInfoDto propertyManagerInfoDto) {
		PropertyManagerInfo propertyManagerInfo = PropertyManagerInfoUtil.convertToEntity(propertyManagerInfoDto);
		propertyManagerInfoService.create(propertyManagerInfo);
		return PropertyManagerInfoUtil.convertToDto(propertyManagerInfo);
	}

	@ApiOperation(value = "Update a property manager info", notes = "Returns a property manager with update options")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully created"),
			@ApiResponse(code = 400, message = "Bad request - The request was malformed and property manager info record was not updated") })
	@PutMapping("/")
	public PropertyManagerInfoDto updatePropertyManagerInfo(
			@RequestBody @Valid @ApiParam(name = "propertyManagerInfoDto", value = "Property manager info that needs to be updated") PropertyManagerInfoDto propertyManagerInfoDto) {
		PropertyManagerInfo propertyManagerInfo = PropertyManagerInfoUtil.convertToEntity(propertyManagerInfoDto);
		if (propertyManagerInfoDto.getState() == null) {
			propertyManagerInfo.setState(null);
		}
		if (propertyManagerInfoDto.getIsNetRate() == null) {
			propertyManagerInfo.setIsNetRate(null);
		}
		propertyManagerInfoService.update(propertyManagerInfo);
		return PropertyManagerInfoUtil.convertToDto(propertyManagerInfo);
	}

	@ApiOperation(value = "Delete a property manager info", notes = "Returns a property manager info id that was deleted")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully deleted"),
			@ApiResponse(code = 400, message = "Bad request - The request was malformed and property manager info was not deleted") })
	@DeleteMapping("/{id}")
	public Integer deletePropertyManagerInfoById(@PathVariable @ApiParam(name = "id", value = "Property manager info id", example = "1") Integer id) {
		propertyManagerInfoService.delete(id);
		return id;
	}
}
