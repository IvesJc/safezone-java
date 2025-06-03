package com.adaptivedialogs.safezonejava.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Campanha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descricao;
    private LocalDateTime inicio;
    private LocalDateTime fim;

    @ManyToMany
    @JoinTable(
            name = "campanha_area_risco",
            joinColumns = @JoinColumn(name = "campanha_id"),
            inverseJoinColumns = @JoinColumn(name = "area_risco_id"))
    private List<AreaRisco> areasRisco;

    // Getters e Setters
}
