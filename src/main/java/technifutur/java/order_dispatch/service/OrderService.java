package technifutur.java.order_dispatch.service;

import technifutur.java.order_dispatch.model.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    List<OrderDTO> getAll();

    OrderDTO getOne(long id);


    int getStockQuantity(String productId);


    void delete(long id);

}
