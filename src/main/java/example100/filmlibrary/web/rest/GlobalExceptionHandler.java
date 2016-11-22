package example100.filmlibrary.web.rest;

import example100.filmlibrary.util.exeption.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created on 22.11.2016.
 * Time 22:28.
 *
 * @author Volodymyr Portianko
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> processNotFoundException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> processValidationException(MethodArgumentNotValidException e) {

        StringBuilder stringBuilder = new StringBuilder();

        /* build message with all validation errors */
        e.getBindingResult().getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .map(s -> s + ", ")
                .forEach(stringBuilder::append);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        return new ResponseEntity<>(stringBuilder.toString().substring(0, stringBuilder.toString().length() - 2),
                                        headers, HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
