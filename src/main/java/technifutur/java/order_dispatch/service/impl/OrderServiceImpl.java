package technifutur.java.order_dispatch.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import technifutur.java.order_dispatch.exception.EntityNotFound;
import technifutur.java.order_dispatch.model.dto.OrderDTO;
import technifutur.java.order_dispatch.model.entity.Order;
import technifutur.java.order_dispatch.model.entity.Product;
import technifutur.java.order_dispatch.model.entity.ProductsInOrder;
import technifutur.java.order_dispatch.model.form.OrderForm;
import technifutur.java.order_dispatch.model.form.OrderItemForm;
import technifutur.java.order_dispatch.repository.OrderRepository;
import technifutur.java.order_dispatch.repository.ProductRepository;
import technifutur.java.order_dispatch.repository.ProductsInOrderRepository;
import technifutur.java.order_dispatch.service.OrderService;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ProductsInOrderRepository productsInOrderRepository;

    private final RestTemplate restTemplate;

    private final String stockServiceUrl;


    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository, ProductsInOrderRepository productsInOrderRepository, RestTemplate restTemplate, String stockServiceUrl) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.productsInOrderRepository = productsInOrderRepository;
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

        Order entity = this.orderRepository.getReferenceById(id);
        this.orderRepository.delete(entity);

    }


//    @Override
//    public int getStockQuantity(String productId) {
//
//        Map<String, String> params = new HashMap<>();
//        params.put("productId", productId);
//
//        StockResponse stockResponse = restTemplate.getForObject(stockServiceUrl, StockResponse.class, params);
//
//        return stockResponse.getQuantity();
//
//    }


    @Override
    public void update(OrderForm orderForm, long id) {

        Order order = this.orderRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFound("Order not found")
                );

        this.productsInOrderRepository.deleteAll(order.getProductsInOrder());
        addProductToOrder(order, orderForm);

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

        addProductToOrder(entity, orderForm);
    }

    private void addProductToOrder(Order entity, OrderForm form) {
        for (OrderItemForm orderItem : form.getOrderItems()) {
            ProductsInOrder productsInOrder = new ProductsInOrder();

            Product product = this.productRepository.findById(orderItem.getProductId())
                    .orElseThrow(() -> new EntityNotFound("Product id %d not found".formatted(orderItem.getProductId())));

            productsInOrder.setProduct(product);
            productsInOrder.setOrder(entity);
            productsInOrder.setQuantityToPrepare(orderItem.getQuantity());
            this.productsInOrderRepository.save(productsInOrder);
        }
    }
}
