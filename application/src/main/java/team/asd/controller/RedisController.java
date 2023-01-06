package team.asd.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.asd.service.RedisClient;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@ApiOperation("Redis API")
public class RedisController {
	private final RedisClient redisClient;

	@Autowired
	public RedisController(RedisClient redisClient) {
		this.redisClient = redisClient;
	}

	@ApiOperation(value = "Get cache value by key", notes = "For valid response provide non-empty key. Returns a value as per the key")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"), @ApiResponse(code = 400, message = "Wrong key was provided") })
	@GetMapping("/cache/{key}")
	public String getValueByKey(@PathVariable @ApiParam(name = "key", value = "Key", example = "testKey") String key) {
		return redisClient.readByKey(key);
	}

	@ApiOperation(value = "Get cache values list by key", notes = "For valid response provide non-empty key. Returns a list of values as per the key")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"), @ApiResponse(code = 400, message = "Wrong key was provided") })
	@GetMapping("/caches-list/{key}")
	public List<String> getListByKey(@PathVariable @ApiParam(name = "key", value = "Key", example = "testKey") String key) {
		return redisClient.retrieveList(key);
	}

	@ApiOperation(value = "Get cache value from map by map and value keys", notes = "For valid response provide non-empty keys. Returns a value as per the keys")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"), @ApiResponse(code = 400, message = "Wrong keys were provided") })
	@GetMapping("/cache/{mapKey}/{valueKey}")
	public String getMapValueByKey(@PathVariable @ApiParam(name = "mapKey", value = "Key of map", example = "testMapKey") String mapKey,
			@PathVariable @ApiParam(name = "valueKey", value = "Key of value", example = "testKey") String valueKey) {
		return redisClient.retrieveValueFromHashMap(mapKey, valueKey);
	}

	@ApiOperation(value = "Get cache values map by key", notes = "For valid response provide non-empty key. Returns a map of values and its keys as per the map key")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"), @ApiResponse(code = 400, message = "Wrong key was provided") })
	@GetMapping("/caches-map/{key}")
	public Map<String, String> getMapByKey(@PathVariable @ApiParam(name = "key", value = "Key", example = "testKey") String key) {
		return redisClient.retrieveValueFromHashMap(key);
	}

	@ApiOperation(value = "Save cache with its key", notes = "For valid response provide non-empty parameters. Returns saved value")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 400, message = "Wrong parameters were provided") })
	@PostMapping("/cache")
	public String saveValueByKey(@RequestParam(name = "key") @ApiParam(name = "key", value = "Key", example = "testKey") String key,
			@RequestParam(name = "value") @ApiParam(name = "value", value = "Value", example = "testValue") String value) {
		redisClient.saveValueByKey(key, value);
		return value;
	}

	@ApiOperation(value = "Save cache list with its key", notes = "For valid response provide non-empty parameters. Returns saved list of values")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 400, message = "Wrong parameters were provided") })
	@PostMapping("/caches")
	public List<String> saveValueListByKey(@RequestParam(name = "key") @ApiParam(name = "key", value = "Key", example = "testKey") String key,
			@RequestBody @ApiParam(name = "values", value = "Value list", example = "testValue") List<String> values) {
		redisClient.saveList(key, values);
		return values;
	}

	@ApiOperation(value = "Save cache as element of list by key", notes = "For valid response provide non-empty parameters. Returns saved value")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 400, message = "Wrong parameters were provided") })
	@PostMapping("/cache-in-list")
	public String saveListElementByKey(@RequestParam(name = "key") @ApiParam(name = "key", value = "Key", example = "testKey") String key,
			@RequestParam(name = "value") @ApiParam(name = "value", value = "Value", example = "testValue") String value) {
		redisClient.saveElementIntoList(key, value);
		return value;
	}

	@ApiOperation(value = "Save cache as element of map with map key and value key", notes = "For valid response provide non-empty parameters. Returns saved value")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 400, message = "Wrong parameters were provided") })
	@PostMapping("/cache-in-map")
	public String saveMapValueByKey(@RequestParam(name = "mapKey") @ApiParam(name = "mapKey", value = "Key of map", example = "testMapKey") String mapKey,
			@RequestParam(name = "valueKey") @ApiParam(name = "valueKey", value = "Key of value", example = "testKey") String valueKey,
			@RequestParam(name = "value") @ApiParam(name = "value", value = "Value", example = "testValue") String value) {
		redisClient.saveValueInHashMap(mapKey, valueKey, value);
		return value;
	}

	@ApiOperation(value = "Save expiring cache with its key", notes = "For valid response provide non-empty parameters and positive expire date. Returns saved value")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 400, message = "Wrong parameters were provided") })
	@PostMapping("/expire-cache")
	public String saveValueWithExpireByKey(@RequestParam(name = "key") @ApiParam(name = "key", value = "Key", example = "testKey") String key,
			@RequestParam(name = "value") @ApiParam(name = "value", value = "Value", example = "testValue") String value,
			@RequestParam(name = "expireDate") @ApiParam(name = "expireDate", value = "Expire date in seconds", example = "20") Long expireDate) {
		redisClient.saveValueWithExpireDate(key, value, expireDate);
		return value;
	}
}
