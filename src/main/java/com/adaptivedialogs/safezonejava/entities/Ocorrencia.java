package com.adaptivedialogs.safezonejava.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_ocorrencia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ocorrencia {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O tipo é obrigatório")
    private String tipo;

    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;

    @NotNull(message = "Latitude é obrigatória")
    private Double latitude;

    @NotNull(message = "Longitude é obrigatória")
    private Double longitude;

    @NotBlank(message = "Status é obrigatório")
    private String status;

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
