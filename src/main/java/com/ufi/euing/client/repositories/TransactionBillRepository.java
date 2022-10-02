package com.ufi.euing.client.repositories;

import com.ufi.euing.client.entity.TransactionBill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionBillRepository extends JpaRepository<TransactionBill, Long> {
}
