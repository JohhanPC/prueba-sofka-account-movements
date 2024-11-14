package api_com_bank.account_movements.config;

import api_com_bank.account_movements.exceptions.MessageSendingException;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class CustomRabbitTemplate extends RabbitTemplate {

    // Constructor que recibe el ConnectionFactory
    public CustomRabbitTemplate(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    @Override
    public void convertAndSend(@NonNull String exchange, @NonNull String routingKey, @NonNull Object message) {
        try {
            super.convertAndSend(exchange, routingKey, message);
        } catch (AmqpException ex) {
            throw new MessageSendingException("Error al enviar mensaje a RabbitMQ", ex);
        }
    }

    @Override
    public void convertAndSend(@NonNull String routingKey, @NonNull Object message) {
        try {
            super.convertAndSend(routingKey, message);
        } catch (AmqpException ex) {
            throw new MessageSendingException("Error al enviar mensaje a RabbitMQ", ex);
        }
    }
}
