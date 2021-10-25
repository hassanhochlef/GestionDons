/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import Entities.Don;
import java.util.HashSet;
import javax.mail.*;
import java.util.Properties;
import java.util.logging.Logger;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author Hassan
 */
public class MailSend {
    public static void sendMail(String recepient) throws Exception {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");
        String myAccountEmail = "helplinecharityconfirm@gmail.com";
        String password = "HelpLineCharity123456789";
        Session session = Session.getInstance(properties , new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(myAccountEmail,password);
            }
        });
        Message message = prepareMessage(session,myAccountEmail,recepient);
        Transport.send(message);
        
    }
    private static Message prepareMessage(Session session,String myAccountEmail,String recepient){
        try{
            Don d = new Don(1,"23-10-2021",500,"Hopiteaux");
            float x = d.getMontant();
            
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(recepient));
            message.setSubject("Merci pour votre don");
            message.setText("Merci pour votre don de montant"+x+"TND");
            return message;
        }catch (Exception ex){
           
        }
        return null;
    }
    
}
