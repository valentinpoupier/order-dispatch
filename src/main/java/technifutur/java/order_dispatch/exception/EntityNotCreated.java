package technifutur.java.order_dispatch.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EntityNotCreated extends RuntimeException {
    private String field;
    private String code;
    private String message;
}
