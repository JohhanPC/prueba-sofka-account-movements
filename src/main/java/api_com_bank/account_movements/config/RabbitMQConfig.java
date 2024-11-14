package api_com_bank.account_movements.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue customerRequestQueue() {
        return new Queue("customer-request-queue", true);
    }

}
