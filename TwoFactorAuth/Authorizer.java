package TwoFactorAuth;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;


public class Authorizer {

    public void sendMail(){

        Properties properties;
        MimeMessage message;
        TokenGenerator tokenGenerator = new TokenGenerator();

        properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");

        String from = "testmailauthorizer@gmail.com";
        String password = "Qwer1234!"; //Password for email "testmailauthorizer"

        String to = "wojciechwiniarski96@gmail.com"; //TODO: needs to be user input

        //get Session
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {

                        return new PasswordAuthentication(from, password);
                    }
                });
        //compose message
        try {

            message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject("Token for your login");
            message.setText("Hi, \nhere is your authorisation token: \n" + tokenGenerator.generateToken() +
             "\n\nBest regards, \nYour authorisation App");
            //send message
            Transport.send(message);
            System.out.println("message sent successfully");
        } catch (MessagingException e) {

            throw new RuntimeException(e);
        }

    }
}
