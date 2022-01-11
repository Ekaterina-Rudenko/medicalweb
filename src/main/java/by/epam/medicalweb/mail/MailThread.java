package by.epam.medicalweb.mail;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailThread {
    static Logger logger = LogManager.getLogger();
    private MimeMessage message;
    private String sendToEmail;
    private String mailSubject;
    private String mailText;
    private Properties properties;

    public MailThread(String sendToEmail, String mailSubject, String mailText, Properties properties){
        this.sendToEmail = sendToEmail;
        this.mailSubject = mailSubject;
        this.mailText = mailText;
        this.properties = properties;
    }

    private void init() {
        Session mailSession = SessionCreator.createSession(properties);
        mailSession.setDebug(true);

        message = new MimeMessage(mailSession);
        try{
            message.setSubject(mailSubject);
            message.setContent(mailText, "text/html");
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(sendToEmail));
        } catch (AddressException e){
            logger.log(Level.ERROR, "Incorrect address: " + sendToEmail + " ", e);
        }catch (MessagingException e){
            logger.log(Level.ERROR, "Failed to create the message ", e);
        }
    }
    public  void run(){
        init();
        try{
            Transport.send(message);
            logger.log(Level.DEBUG, "Mail was sent successfully");
        } catch (MessagingException e){
            logger.log(Level.ERROR, " Mistake while trying to send the message ", e);
        }
    }
}
