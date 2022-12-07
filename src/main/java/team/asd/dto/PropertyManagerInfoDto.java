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
public class PropertyManagerInfoDto {
	@JsonProperty("id")
	@Positive(message = "Id should be positive")
	@ApiModelProperty(notes = "Property manager info id", example = "1")
	private Integer id;

	@JsonProperty("pm_id")
	@Positive(message = "PM id should be positive")
	@ApiModelProperty(notes = "Party id", example = "1")
	private Integer pmId;

	@JsonProperty("funds_holder")
	@Min(value = 0, message = "Funds holder configuration should be 0 or 1")
	@Max(value = 1, message = "Funds holder configuration should be 0 or 1")
	@ApiModelProperty(notes = "Configuration for PM if it will charge funds for reservation", example = "0")
	private Integer isFundsHolder;

	@JsonProperty("number_of_payments")
	@Min(value = 1, message = "Payment number type should be 1 or 2")
	@Max(value = 2, message = "Payment number type should be 1 or 2")
	@ApiModelProperty(notes = "Payment type for each product for PM", example = "1")
	private Integer numberOfPayments;

	@JsonProperty("payment_amount")
	@Positive(message = "Payment amount should be positive")
	@ApiModelProperty(notes = "Payment amount", example = "100.00")
	private Double paymentAmount;

	@JsonProperty("payment_type")
	@Min(value = 1, message = "Payment type should be between 1 and 3")
	@Max(value = 3, message = "Payment type should be between 1 and 3")
	@ApiModelProperty(notes = "Type of payment for split payments", example = "2")
	private Integer paymentType;

	@JsonProperty("state")
	@Size(max = 15, message = "State size cannot be more than 6 characters")
	@ApiModelProperty(notes = "Property manager state", example = "Created")
	private String state;

	@JsonProperty("commission")
	@PositiveOrZero(message = "Commission should be positive or zero")
	@ApiModelProperty(notes = "Commission of PM", example = "2.00")
	private Double commission;

	@JsonProperty("net_rate")
	@Min(value = 0, message = "Net rate configuration should be 0 or 1")
	@Max(value = 1, message = "Net rate configuration should be 0 or 1")
	@ApiModelProperty(notes = "Configuration for commission if it is included in base price", example = "0")
	private Integer isNetRate;
}
