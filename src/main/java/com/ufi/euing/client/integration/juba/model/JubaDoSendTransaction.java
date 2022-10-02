/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.integration.juba.model;

import com.ufi.euing.client.entity.Beneficiary;
import com.ufi.euing.client.entity.Sender;
import com.ufi.euing.client.entity.Utilisateur;
import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author UFI
 */

@Data
public class JubaDoSendTransaction implements Serializable {

    Long companyId;
    String companyName;
    Long serviceId;
    String serviceName;
    String destCountryCode;
    String destCountryLibelle;
    Double amount;
    String originCurrency;
    String destCurrency;
    String agentCode;
    String agentCity;
    Sender sender;
    Beneficiary beneficiary;
    Utilisateur usr;
    Integer purpose;
    Integer sourceOfIncome;
    Integer paymentMode;
    String motifTransaction;
    String messageTransaction;


}
