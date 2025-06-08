package com.adaptivedialogs.safezonejava.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlertaDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long id;
    private String titulo;
    private String descricao;
    private LocalDateTime dataHora;
    private String severidade;
}