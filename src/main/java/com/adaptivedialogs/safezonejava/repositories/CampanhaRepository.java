package com.adaptivedialogs.safezonejava.repositories;

import com.adaptivedialogs.safezonejava.entities.Alerta;
import com.adaptivedialogs.safezonejava.entities.Campanha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampanhaRepository extends JpaRepository<Campanha, Long> {}
