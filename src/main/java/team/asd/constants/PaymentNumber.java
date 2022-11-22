package team.asd.constants;

import java.util.stream.Stream;

public enum PaymentNumber {

	FullPayment(0), SplitPayment(1);

	private final Integer paymentNumber;

	PaymentNumber(Integer paymentNumber) {
		this.paymentNumber = paymentNumber;
	}

	public Integer getIntegerValue() {
		return this.paymentNumber;
	}

	public static PaymentNumber getByInteger(Integer value) {
		return Stream.of(PaymentNumber.values())
				.filter(paymentNumber -> paymentNumber.paymentNumber.equals(value))
				.findFirst()
				.orElse(null);
	}
}
