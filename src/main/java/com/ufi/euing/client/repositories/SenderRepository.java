package com.ufi.euing.client.repositories;

import com.ufi.euing.client.entity.Sender;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface SenderRepository extends JpaRepository<Sender, BigDecimal> {
}
