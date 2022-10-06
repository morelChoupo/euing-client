package com.ufi.euing.client.business.impl;

import com.ufi.euing.client.business.SenderService;
import com.ufi.euing.client.entity.GenericsResponse;
import com.ufi.euing.client.entity.Sender;
import com.ufi.euing.client.exceptions.domain.EmailNotFoundException;
import com.ufi.euing.client.props.EuingProperties;
import com.ufi.euing.client.repositories.SenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Component
public class SenderServiceImpl implements SenderService {

    @Autowired
    private EntityManager em;

    final SenderRepository repository;

    final EuingProperties euingProperties;

    public SenderServiceImpl(SenderRepository repository,
                             EuingProperties euingProperties) {
        this.repository = repository;
        this.euingProperties = euingProperties;
    }


    @Override
    public GenericsResponse<Sender> createSender(Sender sender) {
        try {

            sender.setSenDateCreate(new Date());
            sender.setSenDateModif(new Date());

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("SP_INSERT_SENDER");
            // set parameters
            storedProcedure.registerStoredProcedureParameter("pSEN_ID", BigDecimal.class, ParameterMode.INOUT);
            storedProcedure.registerStoredProcedureParameter("pSEN_CODE", Integer.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pSEN_FIRSTNAME", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pSEN_LASTNAME", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pSEN_COUNTRY", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pSEN_COUNTRY_ID", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pSEN_COUNTRY_RES", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pSEN_COUNTRY_RES_ID", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pSEN_NATIONALITY", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pSEN_NATIONALITY_ID", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pSEN_CITY", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pSEN_CITY_ID", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pSEN_STATE", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pSEN_POSTAL_CODE", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pSEN_PHONE_NUMBER1", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pSEN_PHONE_NUMBER2", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pSEN_EMAIL", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pSEN_DOB", Date.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pSEN_STATUT", Short.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pSEN_ID_DOCUMENT_ISSUE_COUNTRY", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pSEN_ID_DOCUMENT_NUMBER", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pSEN_ID_DOCUMENT_TYPE", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pSEN_ID_DOCUMENT_IMG1", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pSEN_ID_DOCUMENT_IMG2", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pSEN_ID_DOCUMENT_IMG3", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pSEN_ID_DOCUMENT_EXP_DATE", Date.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pSEN_ID_DOCUMENT_DEL_DATE", Date.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pSEN_GENDER", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pSEN_OCCUPATION", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pSEN_COMMENT", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pSEN_DATE_CREATE", Date.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pSEN_DATE_MODIF", Date.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pSEN_ADDRESS", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pSEN_ADDRESS2", String.class, ParameterMode.IN);

//            storedProcedure.registerStoredProcedureParameter("RES", BigDecimal.class, ParameterMode.OUT);

            BigDecimal res = null;
            storedProcedure.setParameter("pSEN_ID", sender.getSenId());
            storedProcedure.setParameter("pSEN_CODE", sender.getSenCode());
            storedProcedure.setParameter("pSEN_FIRSTNAME", sender.getSenFirstname());
            storedProcedure.setParameter("pSEN_LASTNAME", sender.getSenLastname());
            storedProcedure.setParameter("pSEN_COUNTRY", sender.getSenCountry());
            storedProcedure.setParameter("pSEN_COUNTRY_ID", sender.getSenCountryId().getPsCode());
            storedProcedure.setParameter("pSEN_COUNTRY_RES", sender.getSenCountryRes());
            storedProcedure.setParameter("pSEN_COUNTRY_RES_ID", sender.getSenCountryResId().getPsCode());
            storedProcedure.setParameter("pSEN_NATIONALITY", sender.getSenNationality());
            storedProcedure.setParameter("pSEN_NATIONALITY_ID", sender.getSenNationalityId().getPsCode());
            storedProcedure.setParameter("pSEN_CITY", sender.getSenCity());
            storedProcedure.setParameter("pSEN_CITY_ID", sender.getSenCityId().getViId());
            storedProcedure.setParameter("pSEN_STATE", sender.getSenState());
            storedProcedure.setParameter("pSEN_POSTAL_CODE", sender.getSenPostalCode());
            storedProcedure.setParameter("pSEN_PHONE_NUMBER1", sender.getSenPhoneNumber1());
            storedProcedure.setParameter("pSEN_PHONE_NUMBER2", sender.getSenPhoneNumber2());
            storedProcedure.setParameter("pSEN_EMAIL", sender.getSenEmail());
            if(sender.getSenDob() == null)
                sender.setSenDob(new Date());
            storedProcedure.setParameter("pSEN_DOB", (sender.getSenDob() == null) ? new Date() : sender.getSenDob());
            storedProcedure.setParameter("pSEN_STATUT", sender.getSenStatut());
            storedProcedure.setParameter("pSEN_ID_DOCUMENT_ISSUE_COUNTRY", (sender.getSenIdDocumentIssueCountry() == null) ? null : sender.getSenIdDocumentIssueCountry().getPsCode());
            storedProcedure.setParameter("pSEN_ID_DOCUMENT_NUMBER", sender.getSenIdDocumentNumber());
            storedProcedure.setParameter("pSEN_ID_DOCUMENT_TYPE", (sender.getSenIdDocumentType() == null) ? null : sender.getSenIdDocumentType().getTpiCode());
            storedProcedure.setParameter("pSEN_ID_DOCUMENT_IMG1", sender.getSenIdDocumentImg1());
            storedProcedure.setParameter("pSEN_ID_DOCUMENT_IMG2", sender.getSenIdDocumentImg2());
            storedProcedure.setParameter("pSEN_ID_DOCUMENT_IMG3", sender.getSenIdDocumentImg3());
            storedProcedure.setParameter("pSEN_ID_DOCUMENT_EXP_DATE", sender.getSenIdDocumentExpDate());
            storedProcedure.setParameter("pSEN_ID_DOCUMENT_DEL_DATE", sender.getSenIdDocumentDelDate());
            if( sender.getSenGender()==null || sender.getSenGender().trim().equalsIgnoreCase("") )
                sender.setSenGender("N/A");

            storedProcedure.setParameter("pSEN_GENDER", (sender.getSenGender()==null || sender.getSenGender().trim().equalsIgnoreCase("") ) ? "N/A" : sender.getSenGender());
            storedProcedure.setParameter("pSEN_OCCUPATION", sender.getSenOccupation());
            storedProcedure.setParameter("pSEN_COMMENT", sender.getSenComment());
            storedProcedure.setParameter("pSEN_DATE_CREATE", sender.getSenDateCreate());
            storedProcedure.setParameter("pSEN_DATE_MODIF", sender.getSenDateModif());
            storedProcedure.setParameter("pSEN_ADDRESS", sender.getSenAddress());
            storedProcedure.setParameter("pSEN_ADDRESS2", sender.getSenAddress2());
            // execute stored procedure
            storedProcedure.execute();

            res = (BigDecimal) storedProcedure.getOutputParameterValue("pSEN_ID");
            if (res.intValue() == -1) {
                return new GenericsResponse<>(500, "Code erreur -1 : Une erreur s'est produite lors de l'ajout/modification de l'expéditeur.", new Sender());
            }

            if (res.intValue() == -2) {
                return new GenericsResponse<>(500, "Code erreur -2 : Une erreur s'est produite lors de la mise à jour de l'expediteur.", new Sender());
            }

            sender.setSenId(res);


            Sender result = repository.findById(res).orElseThrow(()-> new EmailNotFoundException("Sender not found"));
            sender.setSenCode(result.getSenCode());

            //this.getEntityManager().merge(sender);
            repository.save(sender);
            return new GenericsResponse<>(sender);
        } catch (Exception e) {
            e.printStackTrace();
            return new GenericsResponse<>(500, "An error occured. Details : " + e.getMessage(), new Sender());
        }
    }

    @Override
    public GenericsResponse<Sender> updateSender(Sender sender) {
        try {
            sender.setSenDateModif(new Date());
            repository.save(sender);
            return new GenericsResponse<>(sender);
        } catch (Exception e) {
            return new GenericsResponse<>(500, "An error occured. Details : " + e.getMessage(), new Sender());
        }
    }

    @Override
    public List<Sender> getSender() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sender> getSenderByStatut(int status) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sender> searchSender(int code, String firstname, String lastname, String phoneNumber) {
        return em.createNamedQuery("Sender.findBySenCodeFirstnameLastnamePhoneNumberStatut")
                .setParameter("senFirstname", firstname)
                .setParameter("senLastname", lastname)
                .setParameter("senPhoneNumber", phoneNumber)
                .setParameter("senCode", code)
                .getResultList();
    }

    @Override
    public GenericsResponse<Sender> getSenderByCode(String fctCode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GenericsResponse<Sender> activateSender(Sender sender) {
        try {
            Sender temp = repository.findById(sender.getSenId()).orElseThrow(()-> new EmailNotFoundException("Sender not found"));
            temp.setSenStatut(new Short("1"));
            temp.setSenDateModif(new Date());
            repository.save(sender);
            return new GenericsResponse<>(temp);
        } catch (Exception e) {
            return new GenericsResponse<>(500, "An error occured. Details : " + e.getMessage(), new Sender());
        }
    }

    @Override
    public GenericsResponse<Sender> desactivateSender(Sender sender) {
        try {
            Sender temp = repository.findById(sender.getSenId()).orElseThrow(()-> new EmailNotFoundException("Sender not found"));
            temp.setSenStatut(new Short("O"));
            temp.setSenDateModif(new Date());
            repository.save(sender);
            return new GenericsResponse<>(temp);
        } catch (Exception e) {
            return new GenericsResponse<>(500, "An error occured. Details : " + e.getMessage(), new Sender());
        }
    }

    @Override
    public List<Sender> searchSenderWithoutStatut(int code, String firstname, String lastname, String phoneNumber) {
        return em.createNamedQuery("Sender.findBySenCodeFirstnameLastnamePhoneNumber")
                .setParameter("senFirstname", ""+firstname+"")
                .setParameter("senLastname", ""+lastname+"")
                .setParameter("senPhoneNumber", phoneNumber)
                .setParameter("senCode", code)
                .getResultList();
    }
}
