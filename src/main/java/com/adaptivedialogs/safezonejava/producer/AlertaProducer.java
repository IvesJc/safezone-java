package com.adaptivedialogs.safezonejava.producer;

import com.adaptivedialogs.safezonejava.entities.dto.AlertaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@RequiredArgsConstructor
public class AlertaProducer {

    private final RabbitTemplate rabbitTemplate;

    public void enviarParaFila(AlertaDto alertaDTO) {
        rabbitTemplate.convertAndSend("alerta.exchange", "alerta.routingkey", alertaDTO);
    }
}
