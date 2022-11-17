package team.asd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
	private Integer id;

	@JsonProperty("party_id")
	@Positive(message = "Party id should be positive")
	private Integer partyId;

	@JsonProperty("abbreviation")
	@Size(max = 6, message = "Abbreviation size cannot be more than 6 characters")
	private String abbreviation;

	@JsonProperty("channel_name")
	@Size(max = 45, message = "Channel name size cannot be more than 6 characters")
	private String channelName;

	@JsonProperty("state")
	@Size(max = 15, message = "State size cannot be more than 6 characters")
	private String state;

	@JsonProperty("commission")
	@PositiveOrZero(message = "Commission should be positive or zero")
	private Double commission;

	@JsonProperty("bp_commission")
	@PositiveOrZero(message = "Bp commission should be positive or zero")
	private Double bpCommission;

	@JsonProperty("funds_holder")
	@Min(value = 0, message = "Funds holder configuration should be 0 or 1")
	@Max(value = 1, message = "Funds holder configuration should be 0 or 1")
	private Integer isFundsHolder;
}
