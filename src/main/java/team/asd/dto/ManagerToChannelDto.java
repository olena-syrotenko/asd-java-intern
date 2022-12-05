package team.asd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Data
@AllArgsConstructor
@Builder
public class ManagerToChannelDto {
	@JsonProperty("id")
	@Positive(message = "Id should be positive")
	private Integer id;

	@JsonProperty("property_manager_id")
	@Positive(message = "Property manager id should be positive")
	private Integer propertyManagerId;

	@JsonProperty("channel_partner_id")
	@Positive(message = "Channel partner id should be positive")
	private Integer channelPartnerId;

	@JsonProperty("channel_partner_commission")
	@PositiveOrZero(message = "Channel partner commission should be positive or zero")
	private Double channelPartnerCommission;

	@JsonProperty("funds_holder")
	@Min(value = 0, message = "Funds holder configuration should be 0 or 1")
	@Max(value = 1, message = "Funds holder configuration should be 0 or 1")
	private Integer isFundsHolder;

	@JsonProperty("net_rate")
	@Min(value = 0, message = "Net rate configuration should be 0 or 1")
	@Max(value = 1, message = "Net rate configuration should be 0 or 1")
	private Integer isNetRate;
}
