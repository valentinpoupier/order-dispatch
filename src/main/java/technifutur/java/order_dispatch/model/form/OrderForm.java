package technifutur.java.order_dispatch.model.form;

import lombok.Data;
import technifutur.java.order_dispatch.model.entity.Order;

import java.util.List;

@Data
public class OrderForm {
    private List<OrderItemForm> orderItems;
    private String shippingAddress;
    private String billingAddress;
    private String User;

    public Order getEntity() {
        Order order = new Order();
        order.setShippingAddress(this.shippingAddress);
        order.setBillingAddress(this.billingAddress);
        for (OrderItemForm orderItemForm : this.orderItems) {
            order.getProducts().put(
                    orderItemForm.getProductId(),
                    orderItemForm.getQuantity()
            );
        }

        return order;
    }
}
