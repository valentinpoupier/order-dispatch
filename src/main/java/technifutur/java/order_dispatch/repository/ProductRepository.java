package technifutur.java.order_dispatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import technifutur.java.order_dispatch.model.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
    
}
