package team.asd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartyProductTransactionDto {
	@JsonProperty("party_id")
	@ApiModelProperty(notes = "Party id", example = "1")
	private Integer partyId;

	@JsonProperty("party_name")
	@ApiModelProperty(notes = "Party name", example = "test party")
	private String partyName;

	@JsonProperty("number_of_payment")
	@ApiModelProperty(notes = "Payment type for products for PM", example = "1")
	private Integer numberOfPayment;

	@JsonProperty("commission_amount")
	@ApiModelProperty(notes = "Commission of PM", example = "1.5")
	private Double commissionAmount;

	@JsonProperty("products_by_supplier")
	@ApiModelProperty(notes = "The number of products for supplier", example = "1")
	private Integer productsCountBySupplier;

	@JsonProperty("payment_transaction_by_partner")
	@ApiModelProperty(notes = "The number of payment transaction for partner", example = "4")
	private Integer paymentTransactionCountByPartner;
}
