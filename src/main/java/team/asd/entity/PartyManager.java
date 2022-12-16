package team.asd.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartyManager {
	private Integer isFundsHolder;
	private Double paymentAmount;
	private Integer paymentType;
	private Double commission;
}
