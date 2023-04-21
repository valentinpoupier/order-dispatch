package technifutur.java.order_dispatch.model.dto;


import lombok.Builder;
import lombok.Data;
import technifutur.java.order_dispatch.model.OrderStatus;
import technifutur.java.order_dispatch.model.entity.Order;

import java.time.LocalDate;
import java.util.Map;

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
                .products(entity.getProducts())
                .build();
    }

}
