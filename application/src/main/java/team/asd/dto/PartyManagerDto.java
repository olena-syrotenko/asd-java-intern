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
public class PartyManagerDto {
	@JsonProperty("funds_holder")
	@Min(value = 0, message = "Funds holder configuration should be 0 or 1")
	@Max(value = 1, message = "Funds holder configuration should be 0 or 1")
	@ApiModelProperty(notes = "Configuration for PM if it will charge funds for reservation", example = "0")
	private Integer isFundsHolder;

	@JsonProperty("payment_amount")
	@Positive(message = "Payment amount should be positive")
	@ApiModelProperty(notes = "Payment amount", example = "100.00")
	private Double paymentAmount;

	@JsonProperty("payment_type")
	@Min(value = 1, message = "Payment type should be between 1 and 3")
	@Max(value = 3, message = "Payment type should be between 1 and 3")
	@ApiModelProperty(notes = "Type of payment for split payments", example = "2")
	private Integer paymentType;

	@JsonProperty("commission")
	@PositiveOrZero(message = "Commission should be positive or zero")
	@ApiModelProperty(notes = "Commission of PM", example = "2.00")
	private Double commission;
}
