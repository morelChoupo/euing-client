package com.ufi.euing.client.business.impl;

import com.ufi.euing.client.business.EmailService;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;





@Service
public class EmailServiceImpl implements EmailService {

    @Override
    public void SendMail(String firstName, String lastName, String password, String email) {
        System.out.println("#############  Pass  ****......**** ################");
        Properties props = new Properties();
        props.put("mail.smtp.host", "eucom-moneyhome.biz");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.debug", "true");
        props.put("mail.smtp.port", "" + 25 + "");
        props.put("mail.transport.protocol", "SMTP");

        props.put("mail.smtp.ssl.trust", "*");

        props.put("mail.smtp.user", "helpdesk@expressunion.net");

        props.put("mail.from", "support@mylcpclient.net");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("helpdesk@expressunion.net", "eRP1234@");
            }
        });
//        session.setDebug(true);
        try {

            Message message = new MimeMessage(session);
            BodyPart messageBodyPart = new MimeBodyPart();
            String[] tab = email.split("\\|");
            System.out.println("Taille =====> " + tab.length);
            int k = 0;
            for (int i = 0; i < tab.length; i++) {
                String string = tab[i];
                if (string.length() > 6) {
                    k = k + 1;
                }
            }
            InternetAddress[] address = new InternetAddress[k];
            for (int i = 0; i < tab.length; i++) {
                if (tab[i].length() < 6) {
                } else {
                    address[i] = new InternetAddress(tab[i]);
                    System.out.println(i + "===> address[i] ===> " + tab[i]);
                }
            }
            message.setRecipients(Message.RecipientType.TO, address);
            message.setSubject("LCP Client  - Nouveau mot de passe");
            message.setText("Bonjour " + firstName + " " + lastName + ", \n \n Votre nouveau mot de passe est : " + password + "\n \n MYLCP Team");
            messageBodyPart.setText("Bonjour " + firstName + " " + lastName + ", \n \n Votre nouveau mot de passe est : " + password + "\n \n MYLCP Team");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            Transport.send(message);

            System.out.println("\n\n*** Message sent successfully at " + email + " ***");

        } catch (MessagingException e) {
            System.out.println("\n\n*** Message not sent successfully at " + email + " ***");
            throw new RuntimeException(e);
        }

    }
}
