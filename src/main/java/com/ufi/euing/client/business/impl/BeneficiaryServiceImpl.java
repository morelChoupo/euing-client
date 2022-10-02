package com.ufi.euing.client.business.impl;

import com.ufi.euing.client.business.BeneficiaryService;
import com.ufi.euing.client.entity.Beneficiary;
import com.ufi.euing.client.entity.GenericsResponse;
import com.ufi.euing.client.repositories.BeneficiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Component
public class BeneficiaryServiceImpl implements BeneficiaryService {

    @Autowired
    private EntityManager em;

    @Autowired
    private BeneficiaryRepository beneficiaryRepository;


    @Override
    public GenericsResponse<Beneficiary> createBeneficiary(BigDecimal senderId, Beneficiary benef) {
        try {
            benef.setBenDateCreate(new Date());
            benef.setBenDateModif(new Date());

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("SP_INSERT_BENEFICIARY");
            // set parameters
            storedProcedure.registerStoredProcedureParameter("pSEN_ID", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pBEN_ID", BigDecimal.class, ParameterMode.INOUT);
            storedProcedure.registerStoredProcedureParameter("pBEN_CODE", Integer.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pBEN_FIRSTNAME", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pBEN_LASTNAME", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pBEN_COUNTRY", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pBEN_COUNTRY_ID", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pBEN_NATIONALITY", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pBEN_NATIONALITY_ID", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pBEN_CITY", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pBEN_CITY_ID", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pBEN_STATE", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pBEN_POSTAL_CODE", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pBEN_PHONE_NUMBER1", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pBEN_PHONE_NUMBER2", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pBEN_EMAIL", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pBEN_DOB", Date.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pBEN_STATUT", Short.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pBEN_ID_DOCUMENT_ISSUE_COUNTRY", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pBEN_ID_DOCUMENT_NUMBER", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pBEN_ID_DOCUMENT_TYPE", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pBEN_ID_DOCUMENT_IMG1", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pBEN_ID_DOCUMENT_IMG2", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pBEN_ID_DOCUMENT_IMG3", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pBEN_ID_DOCUMENT_EXP_DATE", Date.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pBEN_ID_DOCUMENT_DEL_DATE", Date.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pBEN_GENDER", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pBEN_OCCUPATION", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pBEN_COMMENT", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pBEN_DATE_CREATE", Date.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pBEN_DATE_MODIF", Date.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pBEN_ADDRESS", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pBEN_ADDRESS2", String.class, ParameterMode.IN);

//            storedProcedure.registerStoredProcedureParameter("RES", BigDecimal.class, ParameterMode.OUT);
            BigDecimal res = null;

            storedProcedure.setParameter("pSEN_ID", senderId);
            storedProcedure.setParameter("pBEN_ID", benef.getBenId());
            storedProcedure.setParameter("pBEN_CODE", benef.getBenCode());
            storedProcedure.setParameter("pBEN_FIRSTNAME", benef.getBenFirstname());
            storedProcedure.setParameter("pBEN_LASTNAME", benef.getBenLastname());
            storedProcedure.setParameter("pBEN_COUNTRY", benef.getBenCountry());
            storedProcedure.setParameter("pBEN_COUNTRY_ID", benef.getBenCountryId().getPsCode());
            storedProcedure.setParameter("pBEN_NATIONALITY", benef.getBenNationality());
            storedProcedure.setParameter("pBEN_NATIONALITY_ID", benef.getBenNationalityId().getPsCode());
            storedProcedure.setParameter("pBEN_CITY", benef.getBenCity());
            storedProcedure.setParameter("pBEN_CITY_ID", benef.getBenCityId().getViId());
            storedProcedure.setParameter("pBEN_STATE", benef.getBenState());
            storedProcedure.setParameter("pBEN_POSTAL_CODE", benef.getBenPostalCode());
            storedProcedure.setParameter("pBEN_PHONE_NUMBER1", benef.getBenPhoneNumber1());
            storedProcedure.setParameter("pBEN_PHONE_NUMBER2", benef.getBenPhoneNumber2());
            storedProcedure.setParameter("pBEN_EMAIL", benef.getBenEmail());
            storedProcedure.setParameter("pBEN_DOB", benef.getBenDob());
            storedProcedure.setParameter("pBEN_STATUT", benef.getBenStatut());

            storedProcedure.setParameter("pBEN_ID_DOCUMENT_ISSUE_COUNTRY", (benef.getBenIdDocumentIssueCountry() == null) ? null : benef.getBenIdDocumentIssueCountry().getPsCode());
            storedProcedure.setParameter("pBEN_ID_DOCUMENT_NUMBER", benef.getBenIdDocumentNumber());
            storedProcedure.setParameter("pBEN_ID_DOCUMENT_TYPE", (benef.getBenIdDocumentType() == null) ? null : benef.getBenIdDocumentType().getTpiCode());
            storedProcedure.setParameter("pBEN_ID_DOCUMENT_IMG1", benef.getBenIdDocumentImg1());
            storedProcedure.setParameter("pBEN_ID_DOCUMENT_IMG2", benef.getBenIdDocumentImg2());
            storedProcedure.setParameter("pBEN_ID_DOCUMENT_IMG3", benef.getBenIdDocumentImg3());
            storedProcedure.setParameter("pBEN_ID_DOCUMENT_EXP_DATE", benef.getBenIdDocumentExpDate());
            storedProcedure.setParameter("pBEN_ID_DOCUMENT_DEL_DATE", benef.getBenIdDocumentDelDate());
            storedProcedure.setParameter("pBEN_GENDER", benef.getBenGender());
            storedProcedure.setParameter("pBEN_OCCUPATION", benef.getBenOccupation());
            storedProcedure.setParameter("pBEN_COMMENT", benef.getBenComment());
            storedProcedure.setParameter("pBEN_DATE_CREATE", benef.getBenDateCreate());
            storedProcedure.setParameter("pBEN_DATE_MODIF", benef.getBenDateModif());
            storedProcedure.setParameter("pBEN_ADDRESS", benef.getBenAddress());
            storedProcedure.setParameter("pBEN_ADDRESS2", benef.getBenAddress2());
            // execute stored procedure
            storedProcedure.execute();

            res = (BigDecimal) storedProcedure.getOutputParameterValue("pBEN_ID");
            if (res.intValue() == -1) {
                return new GenericsResponse<>(500, "Code erreur -1 : Une erreur s'est produite lors de l'ajout/modification du bénéficiaire.", new Beneficiary());
            }

            if (res.intValue() == -2) {
                return new GenericsResponse<>(500, "Code erreur -2 : Une erreur s'est produite lors de la mise à jour du bénéficiaire.", new Beneficiary());
            }

            benef.setBenId(res);

            //Beneficiary result = this.find(res);
            //this.edit(benef);
//             this.getEntityManager().merge(benef);
            beneficiaryRepository.save(benef);

            return new GenericsResponse<>(benef);
        } catch (Exception e) {
            e.printStackTrace();
            return new GenericsResponse<>(500, "An error occured. Details : " + e.getMessage(), new Beneficiary());
        }
    }

    @Override
    public GenericsResponse<Beneficiary> updateBeneficiary(Beneficiary benef) {
        try {
            benef.setBenDateModif(new Date());
            //this.edit(benef);
            beneficiaryRepository.save(benef);
            return new GenericsResponse<>(benef);
        } catch (Exception e) {
            e.printStackTrace();
            return new GenericsResponse<>(500, "An error occured. Details : " + e.getMessage(), null);
        }
    }

    @Override
    public List<Beneficiary> getBeneficiary() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Beneficiary> getBeneficiaryByStatut(int status) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public List<Beneficiary> searchBeneficiaryBySenderByFirstnameLastname(BigDecimal senderId, String firstname, String lastname) {
        Query query = em.createNamedQuery("Beneficiary.searchBeneficiaryBySenderByFirstnameLastname")
                .setParameter("senId", senderId);

        if (firstname == null) {
            query.setParameter("benFirstname", null);
        } else {
            firstname = firstname.toUpperCase().trim();
            query.setParameter("benFirstname", "%" + firstname + "%");
        }

        if (lastname == null) {
            query.setParameter("benLastname", null);
        } else {
            lastname = lastname.toUpperCase().trim();
            query.setParameter("benLastname", "%" + lastname + "%");
        }

        return query.getResultList();
    }

    @Override
    public List<Beneficiary> searchBeneficiaryBySender(BigDecimal senderId) {
        return em.createNamedQuery("Beneficiary.searchBeneficiaryBySender")
                .setParameter("senId", senderId)
                .getResultList();
    }

    @Override
    public GenericsResponse<Beneficiary> getBeneficiaryByCode(String fctCode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GenericsResponse<Beneficiary> activateBeneficiary(Beneficiary benef) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GenericsResponse<Beneficiary> desactivateBeneficiary(Beneficiary benef) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
