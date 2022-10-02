package com.ufi.euing.client.repositories;

import com.ufi.euing.client.entity.TransactionEuing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface TransactionEuingRepository extends JpaRepository<TransactionEuing, BigDecimal> {
}
