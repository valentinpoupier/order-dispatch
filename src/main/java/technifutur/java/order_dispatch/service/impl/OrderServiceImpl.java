package technifutur.java.order_dispatch.service.impl;

import org.springframework.stereotype.Service;
import technifutur.java.order_dispatch.exception.OrderNotFound;
import technifutur.java.order_dispatch.model.dto.OrderDTO;
import technifutur.java.order_dispatch.model.entity.Order;
import technifutur.java.order_dispatch.model.form.OrderForm;
import technifutur.java.order_dispatch.model.form.OrderItemForm;
import technifutur.java.order_dispatch.repository.OrderRepository;
import technifutur.java.order_dispatch.service.OrderService;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderDTO> getAll() {

        return orderRepository.findAll()
                .stream()
                .map(OrderDTO::from)
                .toList();

    }

    @Override
    public OrderDTO getOne(long id) {

        return orderRepository.findById(id)
                .map(OrderDTO::from)
                .orElseThrow();

    }

    public void delete(long id) {
        Order entity = this.orderRepository.getReferenceById(id);
        this.orderRepository.delete(entity);
    }

    @Override
    public void update(OrderForm orderForm, long id) {

        Order order = this.orderRepository.findById(id)
                .orElseThrow(
                        () -> new OrderNotFound("Order not found")
                );

        order.getProducts().clear();
        for (OrderItemForm orderItem : orderForm.getOrderItems()) {
            order.getProducts().put(
                    orderItem.getProductId(),
                    orderItem.getQuantity()
            );
        }

        order.setBillingAddress(orderForm.getBillingAddress());
        order.setShippingAddress(orderForm.getShippingAddress());


        this.orderRepository.save(order);
    }
}
