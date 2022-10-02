package com.ufi.euing.client.repositories;

import com.ufi.euing.client.entity.EventsBill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventBillRepository extends JpaRepository<EventsBill, Long> {
}
