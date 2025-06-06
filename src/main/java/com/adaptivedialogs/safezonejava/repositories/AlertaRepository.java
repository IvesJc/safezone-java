package com.adaptivedialogs.safezonejava.repositories;

import com.adaptivedialogs.safezonejava.entities.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta, Long> {}
