package com.ufi.euing.client.business;

public interface EmailService {

    void SendMail(String firstName,String lastName, String password, String email);
}
