package team.asd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartyChannelDto {
	@JsonProperty("abbreviation")
	@Size(min = 3, max = 6, message = "Abbreviation size cannot be more than 6 characters")
	@ApiModelProperty(notes = "A short identifier of channel partner", example = "ABBR")
	private String abbreviation;

	@JsonProperty("channel_name")
	@Size(max = 45, message = "Channel name size cannot be more than 6 characters")
	@ApiModelProperty(notes = "Channel name", example = "some channel")
	private String channelName;

	@JsonProperty("commission")
	@PositiveOrZero(message = "Commission should be positive or zero")
	@ApiModelProperty(notes = "Commission of channel", example = "1.5")
	private Double commission;

	@JsonProperty("funds_holder")
	@Min(value = 0, message = "Funds holder configuration should be 0 or 1")
	@Max(value = 1, message = "Funds holder configuration should be 0 or 1")
	@ApiModelProperty(notes = "Configuration for channel if it will charge funds for reservation", example = "0")
	private Integer isFundsHolder;
}
