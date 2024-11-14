package api_com_bank.account_movements.services.implementations;

import api_com_bank.account_movements.config.CustomRabbitTemplate;
import api_com_bank.account_movements.services.contracts.IHandlerMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class HandlerMessage implements IHandlerMessage {

    private final CustomRabbitTemplate rabbitTemplate;

    @Override
    public void sendMessage(String identificationId) {
        log.info("Enviando mensaje a RabbitMQ: {}", identificationId);
        rabbitTemplate.convertAndSend("customer-request-queue", identificationId);
        log.info("Mensaje enviado exitosamente a RabbitMQ");
    }
}
