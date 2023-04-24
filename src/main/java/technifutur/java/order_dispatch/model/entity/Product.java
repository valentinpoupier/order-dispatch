package technifutur.java.order_dispatch.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import technifutur.java.order_dispatch.model.dto.ProductDTO;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @Column(name= "products_id")
    private long id;

    @Column(nullable= false)
    private String name;

    @Column(nullable = false)
    private int stockQuantity;
    
    @Column(nullable = false)
    private int price;
}
