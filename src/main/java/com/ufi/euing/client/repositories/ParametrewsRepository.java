package com.ufi.euing.client.repositories;

import com.ufi.euing.client.entity.Parametrews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface ParametrewsRepository extends JpaRepository<Parametrews, BigDecimal> {
}
