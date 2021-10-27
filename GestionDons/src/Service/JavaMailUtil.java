
package Service;

import Entities.User;
import com.mysql.jdbc.interceptors.SessionAssociationInterceptor;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author SeifD
 */
public class JavaMailUtil {
    
    public static void sendMail(String recipient) throws Exception{
        System.out.println("Preparing to send email");
        Properties properties = new Properties();
        
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        
        String myAccountEmail = "helplinecharityconfirm@gmail.com";
        String password = "HelpLineCharity123456789";
        
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password); 
            }  
        });
        
        Message message = prepareMessage(session, myAccountEmail, recipient); 
        Transport.send(message);
        System.out.println("Message sent succesfully");
    }
    
    private static Message prepareMessage(Session session, String myAccountEmail, String recipient){
        try {
            UserService us = new UserService();
           User u = new User();
       String password =  us.getPassword1(u.getMail());
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO,  new InternetAddress(recipient));
            message.setSubject("Votre Mot de passe oublié est"+password);
            message.setText("Test Mail");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
