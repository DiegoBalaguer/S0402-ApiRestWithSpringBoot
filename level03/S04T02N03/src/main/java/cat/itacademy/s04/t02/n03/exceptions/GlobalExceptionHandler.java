package cat.itacademy.s04.t02.n03.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FruitAlreadyExistException.class)
    public ResponseEntity<String> handleFruitAlreadyExist(FruitAlreadyExistException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);//409
    }

    @ExceptionHandler(FruitNotFoundException.class)
    public String handleFruitNotFound(FruitNotFoundException e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "error/404";
    }

    @ExceptionHandler(FruitInvalidDataException.class)
    public ResponseEntity<String> handleInvalidFruitDataException(FruitInvalidDataException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);//400
    }
}