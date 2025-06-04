package com.adaptivedialogs.safezonejava.repositories;

import com.adaptivedialogs.safezonejava.entities.AreaRisco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRiscoRepository extends JpaRepository<AreaRisco, Long> {
}
