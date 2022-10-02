/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.integration.juba.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 *
 * @author User
 */
public class DoGetAgentsDetails {

    @JsonProperty("AgentCode")
    private String AgentCode;

    @JsonProperty("AgentName")
    private String AgentName;

    @JsonProperty("CityCode")
    private String CityCode;

    @JsonProperty("CountryCode")
    private String CountryCode;

    @JsonProperty("Address")
    private String Address;

    @JsonProperty("CountryName")
    private String CountryName;

    @JsonProperty("CityName")
    private String CityName;

    @JsonProperty("Telephone")
    private String Telephone;

    @JsonProperty("Mobile")
    private String Mobile;

    @JsonProperty("ContactPerson")
    private String ContactPerson;

    @JsonProperty("Email")
    private String Email;

    @JsonProperty("Currencies")
    private List<CurrenciesDetails> Currencies;

    @JsonProperty("ModeOfPayment")
    private List<ModeOfPaymentDetails> ModeOfPayment;

    public String getAgentCode() {
        return AgentCode;
    }

    public void setAgentCode(String AgentCode) {
        this.AgentCode = AgentCode;
    }

    public String getAgentName() {
        return AgentName;
    }

    public void setAgentName(String AgentName) {
        this.AgentName = AgentName;
    }

    public String getCityCode() {
        return CityCode;
    }

    public void setCityCode(String CityCode) {
        this.CityCode = CityCode;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String CountryCode) {
        this.CountryCode = CountryCode;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String CountryName) {
        this.CountryName = CountryName;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String CityName) {
        this.CityName = CityName;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String Telephone) {
        this.Telephone = Telephone;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }

    public String getContactPerson() {
        return ContactPerson;
    }

    public void setContactPerson(String ContactPerson) {
        this.ContactPerson = ContactPerson;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public List<CurrenciesDetails> getCurrencies() {
        return Currencies;
    }

    public void setCurrencies(List<CurrenciesDetails> Currencies) {
        this.Currencies = Currencies;
    }

    public List<ModeOfPaymentDetails> getModeOfPayment() {
        return ModeOfPayment;
    }

    public void setModeOfPayment(List<ModeOfPaymentDetails> ModeOfPayment) {
        this.ModeOfPayment = ModeOfPayment;
    }

    @Override
    public String toString() {
        return "DoGetAgentsDetails{" + "AgentCode=" + AgentCode + ", AgentName=" + AgentName + ", CityCode=" + CityCode + ", CountryCode=" + CountryCode + ", Address=" + Address + ", CountryName=" + CountryName + ", CityName=" + CityName + ", Telephone=" + Telephone + ", Mobile=" + Mobile + ", ContactPerson=" + ContactPerson + ", Email=" + Email + ", Currencies=" + Currencies + ", ModeOfPayment=" + ModeOfPayment + '}';
    }

}
