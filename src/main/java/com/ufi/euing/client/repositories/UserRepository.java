package com.ufi.euing.client.repositories;

import com.ufi.euing.client.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface UserRepository extends JpaRepository<User, BigDecimal> {

    User findUserByUsrEmail(String email);

    User findUserByUsrEmailRec(String email);

    User findUserByUsrCode(String code);
}
