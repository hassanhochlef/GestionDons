
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
    
    public static void sendMail(String recipient,String mail) throws Exception{
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
        UserService us = new UserService();
        String pass = us.getPassword1(recipient);
        Message message = prepareMessage(session, myAccountEmail, recipient,pass); 
        Transport.send(message);
        System.out.println("Message sent succesfully");
    }
    
    private static Message prepareMessage(Session session, String myAccountEmail, String recipient,String pass){
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO,  new InternetAddress(recipient));
            message.setSubject("Forgotpass");
            message.setText("Votre mot de passe oublié est :"+pass);
            return message;
        } catch (Exception ex) {
            Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
