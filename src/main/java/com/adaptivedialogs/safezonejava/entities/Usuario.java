package com.adaptivedialogs.safezonejava.entities;

import jakarta.persistence.*;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String oauthId;

    private String provedor;

    @OneToMany(mappedBy = "usuario")
    private List<Ocorrencia> ocorrencias;
}