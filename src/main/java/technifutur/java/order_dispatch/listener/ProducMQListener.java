package technifutur.java.order_dispatch.listener;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProducMQListener {
    @RabbitListener(bindings = {
            @QueueBinding(
                    exchange = @Exchange(name = "user"),
                    value= @Queue(name = "q-product-stock", durable = "true")
            )
    })
    public void newProduct(String msg) {
        log.info("New user => {}", msg);
    }
}

