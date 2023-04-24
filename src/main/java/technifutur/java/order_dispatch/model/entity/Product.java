package technifutur.java.order_dispatch.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product")
@Setter
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long product_id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "quantity_in_stock")
    private int stockQuantity;

    @OneToMany(mappedBy = "product")
    private Set<ProductsInOrder> productsInOrder = new HashSet<>();

}
