package team.asd.constants;

import java.util.stream.Stream;

public enum PaymentType {

	Percentage(1), FlatFee(2), Nights(3);

	private final Integer paymentType;

	PaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}

	public Integer getIntegerValue() {
		return this.paymentType;
	}

	public static PaymentType getByInteger(Integer value) {
		return Stream.of(PaymentType.values())
				.filter(type -> type.paymentType.equals(value))
				.findFirst()
				.orElse(null);
	}
}
