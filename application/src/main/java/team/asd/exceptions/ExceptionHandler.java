package team.asd.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage catchValidationException(ValidationException exception) {
        log.error(exception.getMessage(), exception);
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(RedisValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage catchRedisValidationException(RedisValidationException exception) {
        log.error(exception.getMessage(), exception);
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InvokeMethodException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage catchValidationException(InvokeMethodException exception) {
        log.error(exception.getMessage(), exception);
        return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage catchMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        String message = exception.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));
        log.error(message, exception);
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), message);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage catchMissingServletRequestParameterException(MissingServletRequestParameterException exception) {
        String message = "Missing required parameter " + exception.getParameterName();
        log.error(message, exception);
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), message);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage catchException(Exception exception) {
        log.error(exception.getMessage(), exception);
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Unknown exception");
    }
}
