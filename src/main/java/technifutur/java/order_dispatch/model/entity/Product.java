package technifutur.java.order_dispatch.model.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long product_id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "product")
    private Set<ProductsInOrder> productsInOrder = new HashSet<>();
}
