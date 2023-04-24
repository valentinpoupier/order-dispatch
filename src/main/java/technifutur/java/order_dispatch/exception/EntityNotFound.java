package technifutur.java.order_dispatch.exception;

import lombok.Getter;

@Getter
public class EntityNotFound extends RuntimeException {
    public EntityNotFound(String message) {
        super(message);
    }
}
