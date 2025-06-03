package com.adaptivedialogs.safezonejava.entities;

import jakarta.persistence.*;

@Entity
public class Ocorrencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;
    private String status;
    private String prioridade;

    private double latitude;
    private double longitude;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "area_risco_id")
    private AreaRisco areaRisco;

    @OneToMany(mappedBy = "ocorrencia")
    private List<Alerta> alertas;

    // Getters e Setters
}
