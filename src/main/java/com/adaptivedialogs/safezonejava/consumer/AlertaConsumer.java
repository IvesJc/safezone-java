package com.adaptivedialogs.safezonejava.consumer;

import com.adaptivedialogs.safezonejava.entities.Alerta;
import com.adaptivedialogs.safezonejava.entities.Ocorrencia;
import com.adaptivedialogs.safezonejava.entities.dto.AlertaDto;
import com.adaptivedialogs.safezonejava.repositories.AlertaRepository;
import com.adaptivedialogs.safezonejava.repositories.OcorrenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AlertaConsumer {

    private final AlertaRepository alertaRepository;

    @RabbitListener(queues = "alerta.queue")
    public void consumirAlerta(AlertaDto dto) {
        Alerta alerta = new Alerta();
        alerta.setTitulo(dto.getTitulo());
        alerta.setDescricao(dto.getDescricao());
        alerta.setDataHora(dto.getDataHora());
        alerta.setSeveridade(dto.getSeveridade());

        alertaRepository.save(alerta);
    }
}

