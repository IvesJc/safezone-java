package com.adaptivedialogs.safezonejava.repositories;

import com.adaptivedialogs.safezonejava.entities.Ocorrencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long> {}
