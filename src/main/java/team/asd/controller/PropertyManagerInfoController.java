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
import team.asd.dto.PropertyManagerInfoDto;
import team.asd.entity.PropertyManagerInfo;
import team.asd.service.PropertyManagerInfoService;
import team.asd.util.PropertyManagerInfoUtil;

import javax.validation.Valid;

@RestController
@RequestMapping("/property_manager_info")
public class PropertyManagerInfoController {
	private final PropertyManagerInfoService propertyManagerInfoService;

	@Autowired
	public PropertyManagerInfoController(PropertyManagerInfoService propertyManagerInfoService) {
		this.propertyManagerInfoService = propertyManagerInfoService;
	}

	@GetMapping("/{id}")
	public PropertyManagerInfoDto getPropertyManageInfoById(@PathVariable Integer id) {
		return PropertyManagerInfoUtil.convertToDto(propertyManagerInfoService.readById(id));
	}

	@PostMapping("/")
	public PropertyManagerInfoDto createPropertyManageInfo(@RequestBody @Valid PropertyManagerInfoDto propertyManagerInfoDto) {
		PropertyManagerInfo propertyManagerInfo = PropertyManagerInfoUtil.convertToEntity(propertyManagerInfoDto);
		propertyManagerInfoService.create(propertyManagerInfo);
		return PropertyManagerInfoUtil.convertToDto(propertyManagerInfo);
	}

	@PutMapping("/")
	public PropertyManagerInfoDto updatePropertyManageInfo(@RequestBody @Valid PropertyManagerInfoDto propertyManagerInfoDto) {
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

	@DeleteMapping("/{id}")
	public Integer deletePropertyManageInfoById(@PathVariable Integer id) {
		propertyManagerInfoService.delete(id);
		return id;
	}
}
