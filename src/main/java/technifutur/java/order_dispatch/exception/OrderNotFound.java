package technifutur.java.order_dispatch.exception;

import lombok.Getter;

@Getter
public class OrderNotFound extends RuntimeException {
    public OrderNotFound(String message) {
        super(message);
    }
}
