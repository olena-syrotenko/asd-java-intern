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
public class PropertyManagerInfoDto {
	@JsonProperty("id")
	@Positive(message = "Id should be positive")
	private Integer id;

	@JsonProperty("pm_id")
	@Positive(message = "PM id should be positive")
	private Integer pmId;

	@JsonProperty("funds_holder")
	@Min(value = 0, message = "Funds holder configuration should be 0 or 1")
	@Max(value = 1, message = "Funds holder configuration should be 0 or 1")
	private Integer isFundsHolder;

	@JsonProperty("number_of_payments")
	@Min(value = 1, message = "Payment number type should be 1 or 2")
	@Max(value = 2, message = "Payment number type should be 1 or 2")
	private Integer numberOfPayments;

	@JsonProperty("payment_amount")
	@Positive(message = "Payment amount should be positive")
	private Double paymentAmount;

	@JsonProperty("payment_type")
	@Min(value = 1, message = "Payment type should be between 1 and 3")
	@Max(value = 3, message = "Payment type should be between 1 and 3")
	private Integer paymentType;

	@JsonProperty("state")
	@Size(max = 15, message = "State size cannot be more than 6 characters")
	private String state;

	@JsonProperty("commission")
	@PositiveOrZero(message = "Commission should be positive or zero")
	private Double commission;

	@JsonProperty("net_rate")
	@Min(value = 0, message = "Net rate configuration should be 0 or 1")
	@Max(value = 1, message = "Net rate configuration should be 0 or 1")
	private Integer isNetRate;
}
