package team.asd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@Builder
public class ChannelPartnerDto {
	@JsonProperty("id")
	@Positive(message = "Id should be positive")
	@ApiModelProperty(notes = "Channel partner id", example = "1")
	private Integer id;

	@JsonProperty("party_id")
	@Positive(message = "Party id should be positive")
	@ApiModelProperty(notes = "Party id", example = "1")
	private Integer partyId;

	@JsonProperty("abbreviation")
	@Size(min = 3, max = 6, message = "Abbreviation size cannot be more than 6 characters")
	@ApiModelProperty(notes = "A short identifier of channel partner", example = "ABBR")
	private String abbreviation;

	@JsonProperty("channel_name")
	@Size(max = 45, message = "Channel name size cannot be more than 6 characters")
	@ApiModelProperty(notes = "Channel name", example = "some channel")
	private String channelName;

	@JsonProperty("state")
	@Size(max = 15, message = "State size cannot be more than 6 characters")
	@ApiModelProperty(notes = "State of channel partner", example = "Created")
	private String state;

	@JsonProperty("commission")
	@PositiveOrZero(message = "Commission should be positive or zero")
	@ApiModelProperty(notes = "Commission of channel", example = "1.5")
	private Double commission;

	@JsonProperty("bp_commission")
	@PositiveOrZero(message = "Bp commission should be positive or zero")
	@ApiModelProperty(notes = "Commission for reservation changed by BookingPal", example = "3.0")
	private Double bpCommission;

	@JsonProperty("funds_holder")
	@Min(value = 0, message = "Funds holder configuration should be 0 or 1")
	@Max(value = 1, message = "Funds holder configuration should be 0 or 1")
	@ApiModelProperty(notes = "Configuration for channel if it will charge funds for reservation", example = "0")
	private Integer isFundsHolder;
}
