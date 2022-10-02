/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.integration.gimac.model;

/**
 *
 * @author User
 */
public class ReceiverCustomerData extends SenderCustomerData {

    private String city;
    private String country;
    private String phone;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "ReceiverCustomerData{" + "city=" + city + ", country=" + country + ", phone=" + phone + '}';
    }

}
