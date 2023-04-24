package technifutur.java.order_dispatch.model.dto;

import lombok.Builder;
import lombok.Data;
import technifutur.java.order_dispatch.model.entity.Product;

@Data
@Builder
public class ProductDTO {
    
    
        private long id;
    
        private String name;
    
        private int stockQuantity;
        
        private int price;

    public static ProductDTO from(Product entity) {
        if (entity == null)
            return null;

        return ProductDTO.builder()
                .id(entity.getProduct_id())
                .name(entity.getName())
                .price(entity.getPrice())
                .stockQuantity(entity.getStockQuantity())
                .build();
    }
}
