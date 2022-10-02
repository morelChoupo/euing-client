package com.ufi.euing.client.repositories;

import com.ufi.euing.client.entity.VwTransactionEuing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface VwTransactionEuingRepository extends JpaRepository<VwTransactionEuing, BigDecimal> {
}
