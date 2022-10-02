package com.ufi.euing.client.repositories;

import com.ufi.euing.client.entity.Pays;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PaysRepository extends JpaRepository<Pays, String> {

    Pays findPaysByPsCode(String countryCode);
}
