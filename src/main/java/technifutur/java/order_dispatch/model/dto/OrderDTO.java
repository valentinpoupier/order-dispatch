package technifutur.java.order_dispatch.model.dto;


import lombok.Builder;
import lombok.Data;
import technifutur.java.order_dispatch.model.OrderStatus;
import technifutur.java.order_dispatch.model.entity.Order;
import technifutur.java.order_dispatch.model.entity.ProductsInOrder;

import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Builder
public class OrderDTO {

    private long id;
    private Integer numOrder;
    private OrderStatus status;
    private LocalDate orderAt;
    private Map<Long, Integer> products;


    public static OrderDTO from(Order entity) {
        if (entity == null)
            return null;

        return OrderDTO.builder()
                .id(entity.getId())
                .numOrder(entity.getNumOrder())
                .orderAt(entity.getOrderAt())
                .status(entity.getStatus())
                .products(
                        entity.getProductsInOrder().stream()
                                .collect(
                                        Collectors.toMap(
                                                productsInOrder -> productsInOrder.getProduct().getProduct_id(),
                                                ProductsInOrder::getQuantityToPrepare
                                        )
                                )
                )
                .build();
    }
}
