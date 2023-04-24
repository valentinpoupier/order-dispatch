package technifutur.java.order_dispatch.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import technifutur.java.order_dispatch.model.dto.OrderDTO;
import technifutur.java.order_dispatch.model.entity.StockResponse;
import technifutur.java.order_dispatch.repository.OrderRepository;
import technifutur.java.order_dispatch.service.OrderService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                .map( OrderDTO::from )
                .toList();

    }

    @Override
    public OrderDTO getOne(long id) {

        return orderRepository.findById(id)
                .map( OrderDTO::from )
                .orElseThrow();

    }

    @Override
    public int getStockQuantity(String productId) {
        Map<String, String> params = new HashMap<>();
        params.put("productId", productId);

        StockResponse stockResponse = restTemplate.getForObject(stockServiceUrl, StockResponse.class, params);

        return stockResponse.getQuantity();
    }

}
