package technifutur.java.order_dispatch.model.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ProductsInOrderPK implements Serializable {
    private long OrderId;
    private long ProductId;
}
