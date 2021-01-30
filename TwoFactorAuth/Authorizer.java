package TwoFactorAuth;
import GUI.LoginViewController;
import database.DataBaseManager;

import javax.mail.*;
import javax.mail.internet.*;
import java.sql.*;
import java.util.Properties;


public class Authorizer {

    private int token;

    private DataBaseManager databaseManager = new DataBaseManager();

    public void sendMail(){

        setToken(TokenGenerator.generateToken());

        System.out.println("Authorizer token: " + token);

        Properties properties;
        MimeMessage message;

        properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");

        String from = "testmailauthorizer@gmail.com";
        String password = "Qwer1234!"; //Password for email "testmailauthorizer"

        String to = retrieveEmailFromDB(); //TODO: needs to be user input

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
            message.setText("Hi, \nhere is your authorisation token: \n" + getToken() + //TokenGenerator.generateToken() +
             "\n\nBest regards, \nYour authorisation App");
            //send message
            Transport.send(message);
            System.out.println("message sent successfully, your token is: " + getToken());
        } catch (MessagingException e) {

            throw new RuntimeException(e);
        }
    }

    private void setToken(int token){
        this.token = token;
    }

    public int getToken(){
        return token;
    }

    public String retrieveEmailFromDB(){
        String email = "";
        String selectEmail = "SELECT e_mail FROM gPOZ3L2sft.logindata WHERE main_username = '" +
                LoginViewController.username + "'";

        try {
            Connection connection = databaseManager.connectToDB();
            Statement statement = connection.prepareStatement(selectEmail);
            ResultSet rs = statement.executeQuery(selectEmail);
            while (rs.next()){
                email = rs.getString("e_mail");
                System.out.println("email:" + email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return email;
    }


}
