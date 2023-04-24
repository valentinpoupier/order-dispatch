package technifutur.java.order_dispatch.service.impl;

import org.springframework.stereotype.Service;
import technifutur.java.order_dispatch.exception.OrderNotFound;
import org.springframework.web.client.RestTemplate;
import technifutur.java.order_dispatch.model.dto.OrderDTO;
import technifutur.java.order_dispatch.model.entity.StockResponse;
import technifutur.java.order_dispatch.repository.OrderRepository;
import technifutur.java.order_dispatch.service.OrderService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import technifutur.java.order_dispatch.model.entity.Order;
import technifutur.java.order_dispatch.model.form.OrderForm;
import technifutur.java.order_dispatch.model.form.OrderItemForm;
import technifutur.java.order_dispatch.repository.OrderRepository;
import technifutur.java.order_dispatch.service.OrderService;


import java.time.LocalDate;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final RestTemplate restTemplate;

    private final String stockServiceUrl;


    public OrderServiceImpl(OrderRepository orderRepository, RestTemplate restTemplate, String stockServiceUrl) {
        this.orderRepository = orderRepository;
        this.restTemplate = restTemplate;
        this.stockServiceUrl = stockServiceUrl;
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

    public void delete(long id){

        Order entity = this.orderRepository.getReferenceById(id);
        this.orderRepository.delete(entity);

    }


    @Override
    public int getStockQuantity(String productId) {

        Map<String, String> params = new HashMap<>();
        params.put("productId", productId);

        StockResponse stockResponse = restTemplate.getForObject(stockServiceUrl, StockResponse.class, params);

        return stockResponse.getQuantity();

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

    @Override
    public void create(OrderForm orderForm) {
        Order entity = orderForm.getEntity();
        String concat = String.valueOf(LocalDate.now().getYear() + LocalDate.now().getMonthValue() + LocalDate.now().getDayOfMonth() + this.orderRepository.count());
        entity.setNumOrder(Integer.parseInt(concat));
        entity.setOrderAt(LocalDate.now());
        entity.setStatus(technifutur.java.order_dispatch.model.OrderStatus.PROCESSING);
        this.orderRepository.save(entity);
    }
}
