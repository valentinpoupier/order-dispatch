package technifutur.java.order_dispatch.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "products_in_order")
@Getter
@Setter
public class ProductsInOrder {
    @EmbeddedId
    private ProductsInOrderPK id = new ProductsInOrderPK();

    private int quantityToPrepare;
    private int quantityPrepared = 0;

    @ManyToOne
    @MapsId("orderId")
    private Order order;

    @ManyToOne
    @MapsId("productId")
    private Product product;
}
