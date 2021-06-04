package directory.content.task.config.exception;

import directory.content.task.model.ErrorResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static directory.content.task.model.Status.ERROR;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Configuration
@ControllerAdvice
public class ExceptionHandlingAutoConfig {

    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handle(final Exception exception) {
        return handleException(exception);
    }

    private ResponseEntity<ErrorResponse> handleException(final Exception exception) {
        return new ResponseEntity<>(
                new ErrorResponse(ERROR.getStatus(), exception.getMessage()),
                new HttpHeaders(),
                BAD_REQUEST
        );
    }
}
