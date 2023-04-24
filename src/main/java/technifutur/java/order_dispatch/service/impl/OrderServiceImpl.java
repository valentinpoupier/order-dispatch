package technifutur.java.order_dispatch.service.impl;

import org.springframework.stereotype.Service;
import technifutur.java.order_dispatch.model.dto.OrderDTO;
import technifutur.java.order_dispatch.model.form.OrderForm;
import technifutur.java.order_dispatch.repository.OrderRepository;
import technifutur.java.order_dispatch.service.OrderService;

import java.time.LocalDate;
import java.util.List;

import technifutur.java.order_dispatch.model.entity.Order;
import technifutur.java.order_dispatch.repository.OrderRepository;
import technifutur.java.order_dispatch.service.OrderService;

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
                .map( OrderDTO::from )
                .toList();

    }

    @Override
    public OrderDTO getOne(long id) {

        return orderRepository.findById(id)
                .map( OrderDTO::from )
                .orElseThrow();

    }

    public void delete(long id){
        Order entity = this.orderRepository.getReferenceById(id);
        this.orderRepository.delete(entity);
    }

    @Override
    public void create(OrderForm orderForm) {
        Order entity = orderForm.getEntity();
        String concat = String.valueOf(LocalDate.now().getYear()+LocalDate.now().getMonthValue()+LocalDate.now().getDayOfMonth()+ this.orderRepository.count());
        entity.setNumOrder(Integer.parseInt(concat));
        entity.setOrderAt(LocalDate.now());
        entity.setStatus(technifutur.java.order_dispatch.model.OrderStatus.PROCESSING);
        this.orderRepository.save(entity);
    }
}
