package technifutur.java.order_dispatch.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import technifutur.java.order_dispatch.exception.EntityNotCreated;
import technifutur.java.order_dispatch.exception.EntityNotFound;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(EntityNotCreated.class)
    public ResponseEntity<?> handleOrderNotFound(EntityNotCreated e, BindingResult result) {
        result.rejectValue(
                e.getField(),
                e.getCode(),
                e.getMessage()
        );

        return ResponseEntity.status(404).body(result.getAllErrors());
    }

    @ExceptionHandler(EntityNotFound.class)
    public ResponseEntity<?> handleOrderNotFound(EntityNotFound e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }
}
