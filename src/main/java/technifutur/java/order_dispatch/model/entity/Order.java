package technifutur.java.order_dispatch.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Map;

@Entity
@Getter
@Setter
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    
    @Column(name = "date_of_Order")
    private LocalDate OrderAt;
    
    @MapKeyColumn(name = "product_id")
    private Map<Long, Integer> products;
    
    
    

}
