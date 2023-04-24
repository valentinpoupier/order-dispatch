package technifutur.java.order_dispatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import technifutur.java.order_dispatch.model.entity.ProductsInOrder;
import technifutur.java.order_dispatch.model.entity.ProductsInOrderPK;

public interface ProductsInOrderRepository extends JpaRepository<ProductsInOrder, ProductsInOrderPK> {
}