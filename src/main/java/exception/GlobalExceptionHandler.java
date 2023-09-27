package exception;

import com.api.forum.payload.ExceptionDTO;

import exception.types.NotFoundException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    // to be inherited
    static protected ResponseEntity<ExceptionDTO> makeResponseEntity(Exception exception, WebRequest webRequest, HttpStatus responseStatus) {
        ExceptionDTO ExceptionDTO = new ExceptionDTO(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<ExceptionDTO>(ExceptionDTO, responseStatus);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionDTO> handleAccessDeniedException(AccessDeniedException exception, WebRequest webRequest) {
        return makeResponseEntity(exception, webRequest, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleAccessDeniedException(NotFoundException exception, WebRequest webRequest) {
        return makeResponseEntity(exception, webRequest, HttpStatus.NOT_FOUND);
    }

    // Global catch
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDTO> handleGlobalException(Exception exception, WebRequest webRequest){
        return makeResponseEntity(exception, webRequest, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) ->{
            String fieldName = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
