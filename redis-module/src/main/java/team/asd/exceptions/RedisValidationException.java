package team.asd.exceptions;

public class RedisValidationException extends RuntimeException {
	public RedisValidationException(String message) {
		super(message);
	}
}
