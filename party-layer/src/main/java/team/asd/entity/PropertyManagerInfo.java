package team.asd.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import team.asd.constants.PaymentNumber;
import team.asd.constants.PaymentType;
import team.asd.constants.PropertyManagerState;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PropertyManagerInfo {
	private Integer id;
	private Integer pmId;
	private Boolean isFundsHolder;
	private PaymentNumber numberOfPayments;
	private Double paymentAmount;
	private PaymentType paymentType;
	private PropertyManagerState state;
	private Double commission;
	private Boolean isNetRate;
	private Date version;
}
