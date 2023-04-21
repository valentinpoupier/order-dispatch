package technifutur.java.order_dispatch.model.form;

import lombok.Data;

import java.util.List;

@Data
public class OrderForm {
    List<OrderItemForm> orderItems;
}
