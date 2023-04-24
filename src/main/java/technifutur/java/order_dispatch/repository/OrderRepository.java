package technifutur.java.order_dispatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import technifutur.java.order_dispatch.model.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {


}
