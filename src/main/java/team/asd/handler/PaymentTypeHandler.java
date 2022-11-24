package team.asd.handler;

import org.apache.ibatis.type.EnumTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import team.asd.constants.PaymentType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes({PaymentType.class})
public class PaymentTypeHandler extends EnumTypeHandler<PaymentType> {
	public PaymentTypeHandler() {
		super(PaymentType.class);
	}

	public PaymentTypeHandler(Class<PaymentType> type) {
		super(type);
	}

	public void setNonNullParameter(PreparedStatement ps, int i, PaymentType parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(i, parameter.getIntegerValue());
	}

	public PaymentType getNullableResult(ResultSet rs, String columnName) throws SQLException {
		Integer value = rs.getInt(columnName);
		return rs.wasNull() ? null : PaymentType.getByInteger(value);
	}

	public PaymentType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		Integer value = cs.getInt(columnIndex);
		return cs.wasNull() ? null : PaymentType.getByInteger(value);
	}
}
