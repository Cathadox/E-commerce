package finki.dimitrij.gjorgji.lab1.handlers;

import finki.dimitrij.gjorgji.lab1.exceptions.NotNullException;
import finki.dimitrij.gjorgji.lab1.exceptions.ResourceCannotBeModifiedException;
import finki.dimitrij.gjorgji.lab1.exceptions.ResourceNotFoundException;
import finki.dimitrij.gjorgji.lab1.handlers.errors.ApiError;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ApiError error = new ApiError(ex.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotNullException.class)
    public ResponseEntity<Object> handleNotNullException(NotNullException ex) {
        ApiError error = new ApiError(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OptimisticLockingFailureException.class)
    public ResponseEntity<Object> handleOptimisticLockingException() {
        ApiError error = new ApiError("Please refresh and try again", HttpStatus.CONFLICT);
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceCannotBeModifiedException.class)
    public ResponseEntity<Object> handleResourceCannotBeModifiedException(ResourceCannotBeModifiedException ex) {
        ApiError error = new ApiError(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
