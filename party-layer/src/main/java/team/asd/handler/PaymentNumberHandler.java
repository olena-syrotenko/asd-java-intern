package team.asd.handler;

import team.asd.constants.PaymentNumber;
import org.apache.ibatis.type.EnumTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes({ PaymentNumber.class})
public class PaymentNumberHandler extends EnumTypeHandler<PaymentNumber> {
	public PaymentNumberHandler() {
		super(PaymentNumber.class);
	}

	public PaymentNumberHandler(Class<PaymentNumber> type) {
		super(type);
	}

	public void setNonNullParameter(PreparedStatement ps, int i, PaymentNumber parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(i, parameter.getIntegerValue());
	}

	public PaymentNumber getNullableResult(ResultSet rs, String columnName) throws SQLException {
		Integer value = rs.getInt(columnName);
		return rs.wasNull() ? null : PaymentNumber.getByInteger(value);
	}

	public PaymentNumber getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		Integer value = cs.getInt(columnIndex);
		return cs.wasNull() ? null : PaymentNumber.getByInteger(value);
	}
}
