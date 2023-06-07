package pl.it.conf.scheduler.exception;

import jakarta.validation.ValidationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.it.conf.scheduler.exception.type.ForbiddenException;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({ValidationException.class})
    public final ResponseEntity<ExceptionBody> handleLoginValidation(ValidationException e) {
        return handleResponse(HttpStatus.UNAUTHORIZED, e.getMessage(), e.getClass().getSimpleName());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public final ResponseEntity<ExceptionBody> handleEmailValidation(MethodArgumentNotValidException e) {
        return handleResponse(HttpStatus.UNAUTHORIZED, "Not valid email", e.getClass().getSimpleName());
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public final ResponseEntity<ExceptionBody> handleUserNotFound(IllegalArgumentException e) {
        return handleResponse(HttpStatus.NOT_FOUND, e.getMessage(), e.getClass().getSimpleName());
    }

    @ExceptionHandler({ForbiddenException.class})
    public final ResponseEntity<ExceptionBody> handleForbidden(ForbiddenException e) {
        return handleResponse(HttpStatus.FORBIDDEN, e.getMessage(), e.getClass().getSimpleName());
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    public final ResponseEntity<ExceptionBody> handleMissingKey(MissingServletRequestParameterException e) {
        return handleResponse(HttpStatus.FORBIDDEN, "Missing organizer key", e.getClass().getSimpleName());
    }

    private ResponseEntity<ExceptionBody> handleResponse(HttpStatus httpStatus, String message, String error) {
        return new ResponseEntity<>(new ExceptionBody(
                httpStatus.value(),
                message,
                error),
                new HttpHeaders(),
                httpStatus);
    }

}
