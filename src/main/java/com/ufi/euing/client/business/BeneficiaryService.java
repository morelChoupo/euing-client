package com.ufi.euing.client.business;

import com.ufi.euing.client.entity.Beneficiary;
import com.ufi.euing.client.entity.GenericsResponse;

import java.math.BigDecimal;
import java.util.List;

public interface BeneficiaryService {

     GenericsResponse<Beneficiary> createBeneficiary(BigDecimal senderId, Beneficiary sender);
     GenericsResponse<Beneficiary> updateBeneficiary(Beneficiary sender);
     List<Beneficiary> getBeneficiary();
     List<Beneficiary> getBeneficiaryByStatut(int status);
     List<Beneficiary> searchBeneficiaryBySenderByFirstnameLastname(BigDecimal senderId, String firstname, String lastname);
     List<Beneficiary> searchBeneficiaryBySender(BigDecimal senderId);
     GenericsResponse<Beneficiary> getBeneficiaryByCode(String fctCode);
     GenericsResponse<Beneficiary> activateBeneficiary(Beneficiary sender);
     GenericsResponse<Beneficiary> desactivateBeneficiary(Beneficiary sender);
}
