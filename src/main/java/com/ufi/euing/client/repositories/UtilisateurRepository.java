package com.ufi.euing.client.repositories;

import com.ufi.euing.client.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, BigDecimal> {
}
