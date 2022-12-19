package team.asd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ManagerToChannelDto {
	@JsonProperty("id")
	@Positive(message = "Id should be positive")
	@ApiModelProperty(notes = "Id of manager to channel", example = "1")
	private Integer id;

	@JsonProperty("property_manager_id")
	@Positive(message = "Property manager id should be positive")
	@ApiModelProperty(notes = "Property manager id", example = "1")
	private Integer propertyManagerId;

	@JsonProperty("channel_partner_id")
	@Positive(message = "Channel partner id should be positive")
	@ApiModelProperty(notes = "Channel partner id", example = "2")
	private Integer channelPartnerId;

	@JsonProperty("channel_partner_commission")
	@PositiveOrZero(message = "Channel partner commission should be positive or zero")
	@ApiModelProperty(notes = "Channel partner's commission percent for linked PM", example = "2.5")
	private Double channelPartnerCommission;

	@JsonProperty("funds_holder")
	@Min(value = 0, message = "Funds holder configuration should be 0 or 1")
	@Max(value = 1, message = "Funds holder configuration should be 0 or 1")
	@ApiModelProperty(notes = "Configuration for channel partner and PM if it will charge funds for reservation", example = "0")
	private Integer isFundsHolder;

	@JsonProperty("net_rate")
	@Min(value = 0, message = "Net rate configuration should be 0 or 1")
	@Max(value = 1, message = "Net rate configuration should be 0 or 1")
	@ApiModelProperty(notes = "Configuration for channel partner and PM if commission is included in base price", example = "0")
	private Integer isNetRate;
}
