package com.adaptivedialogs.safezonejava.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensagem;

    private LocalDateTime dataCriacao;

    @ManyToOne
    @JoinColumn(name = "ocorrencia_id")
    private Ocorrencia ocorrencia;
}
