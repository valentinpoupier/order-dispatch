package technifutur.java.order_dispatch.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import technifutur.java.order_dispatch.model.OrderStatus;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "numero_order")
    private Integer numOrder;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "date_of_Order")
    private LocalDate orderAt;

    @Column(name = "shipping_address")
    private String shippingAddress;

    @Column(name = "billing_address")
    private String billingAddress;

    @OneToMany(mappedBy = "order")
    private Set<ProductsInOrder> productsInOrder = new HashSet<>();

}
