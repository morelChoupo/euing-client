package com.ufi.euing.client.repositories;

import com.ufi.euing.client.entity.ListeToUse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface ListToUseRepository extends JpaRepository<ListeToUse, BigDecimal> {

}
