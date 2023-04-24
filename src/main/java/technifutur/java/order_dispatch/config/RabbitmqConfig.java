package technifutur.java.order_dispatch.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

public class RabbitmqConfig {
      
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    @Qualifier(value = "userRabbitTemplate")
    public RabbitTemplate userRabbitTemplate(
            Jackson2JsonMessageConverter converter,
            CachingConnectionFactory factory
    ) {
        RabbitTemplate template = new RabbitTemplate(factory);
        template.setMessageConverter(converter);

        return template;
    }

}
