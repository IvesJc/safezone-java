package com.adaptivedialogs.safezonejava.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class AreaRisco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private double latitude;
    private double longitude;
    private double raio; // km de raio de risco

    @OneToMany(mappedBy = "areaRisco")
    private List<Ocorrencia> ocorrencias;

    @ManyToMany(mappedBy = "areasRisco")
    private List<Campanha> campanhas;

    // Getters e Setters
}
