package com.ufi.euing.client.repositories;

import com.ufi.euing.client.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ServiceRepository extends JpaRepository<Service, Long> {
}
