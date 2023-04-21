package technifutur.java.order_dispatch.service.impl;

import org.springframework.stereotype.Service;

import technifutur.java.order_dispatch.model.entity.Order;
import technifutur.java.order_dispatch.repository.OrderRepository;
import technifutur.java.order_dispatch.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
private OrderRepository orderRepository;
public OrderServiceImpl(OrderRepository orderRepository){
    this.orderRepository = orderRepository;
}
    public void delete(long id){
        Order entity = this.orderRepository.getReferenceById(id);
        this.orderRepository.delete(entity);
    }
}
