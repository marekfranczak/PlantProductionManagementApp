package garden.database.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Class that handle exception in REST controller.
 * @author Marek Frańczak
 * @since 2.0.0
 */
@ControllerAdvice
public class DataRestExceptionHandler {

    /**
     * Method that links DataNotFoundException with class that contain error message.
     * @param e Object of exception that will be handled.
     * @return Passing error as rest response
     */
    @ExceptionHandler
    public ResponseEntity<DataErrorResponse> handleException(DataNotFoundException e){

        DataErrorResponse error = new DataErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Method that links any kind of exception with class that contain error message.
     * @param e Object of exception that will be handled.
     * @return Passing error as rest response
     */
    @ExceptionHandler
    public ResponseEntity<DataErrorResponse> handleException(Exception e){

        DataErrorResponse error = new DataErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
