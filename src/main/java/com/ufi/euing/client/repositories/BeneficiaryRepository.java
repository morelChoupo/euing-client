package com.ufi.euing.client.repositories;

import com.ufi.euing.client.entity.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, BigDecimal> {
}
