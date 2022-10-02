package com.ufi.euing.client.repositories;

import com.ufi.euing.client.entity.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactureRepository extends JpaRepository<Facture, Long> {
}
