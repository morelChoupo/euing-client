/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.integration.thunes.dto;

import java.io.Serializable;

/**
 *
 * @author UFI
 */
public class ThunesUtil implements Serializable {
    
    public static final String THUNES_URI_QUOTATION = "quotations";
    public static final String THUNES_URI_TRANSACTION = "transactions";
    public static final String THUNES_URI_CONFIRM = "confirm";
    public static final String THUNES_QUOT_MODE_SOURCE_AMOUNT = "SOURCE_AMOUNT";
    public static final String THUNES_QUOT_MODE_DESTINATION_AMOUNT = "DESTINATION_AMOUNT";
    public static final String THUNES_CHINA_PAYER_ID = "CHINA_PAYER_ID";
    public static final String THUNES_NIGERIA_PAYER_ID = "NIGERIA_PAYER_ID";    
    public static final String THUNES_SEND_SERVICE_CODE = "ENVOI_THUNES_C2C_INTL";
}
