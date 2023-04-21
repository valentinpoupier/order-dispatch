package technifutur.java.order_dispatch.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import technifutur.java.order_dispatch.exception.OrderNotCreated;
import technifutur.java.order_dispatch.exception.OrderNotFound;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(OrderNotCreated.class)
    public ResponseEntity<?> handleOrderNotFound(OrderNotCreated e, BindingResult result) {
        result.rejectValue(
                e.getField(),
                e.getCode(),
                e.getMessage()
        );

        return ResponseEntity.status(404).body(result.getAllErrors());
    }

    @ExceptionHandler(OrderNotFound.class)
    public ResponseEntity<?> handleOrderNotFound(OrderNotFound e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }
}
