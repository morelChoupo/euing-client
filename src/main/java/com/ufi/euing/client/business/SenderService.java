package com.ufi.euing.client.business;

import com.ufi.euing.client.entity.GenericsResponse;
import com.ufi.euing.client.entity.Sender;

import java.util.List;

public interface SenderService {

     GenericsResponse<Sender> createSender(Sender sender);
     GenericsResponse<Sender> updateSender(Sender sender);
     List<Sender> getSender();
     List<Sender> getSenderByStatut(int status);
     List<Sender> searchSender(int code, String firstname, String lastname,String phoneNumber);
     GenericsResponse<Sender> getSenderByCode(String fctCode);
     GenericsResponse<Sender> activateSender(Sender sender);
     GenericsResponse<Sender> desactivateSender(Sender sender);

     List<Sender> searchSenderWithoutStatut(int code, String firstname, String lastname,String phoneNumber);
}
