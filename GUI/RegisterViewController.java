package GUI;

import database.DataBaseManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class RegisterViewController implements Initializable {

    private Connection connection;
    private DataBaseManager dataBaseManager;


    public static String username;
    public static String password;

    @FXML
    private TextField textFieldUsername;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField passConfirmationField;
    @FXML
    private TextField emailField;
    @FXML
    private Button buttonRegister;
    @FXML
    private Button buttonCancel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataBaseManager = new DataBaseManager();
        registerNewUser();
        cancel();
    }

    public void registerNewUser() { //todo: email for two factor authentication

        buttonRegister.setOnAction(event -> registerQuery());
    }

    public void registerQuery(){
        Statement statement = null;

        String username = textFieldUsername.getText();
        String password = passwordField.getText();
        String email = emailField.getText();

        if(passwordField.getText().equals(passConfirmationField.getText())){
            try {
                connection = dataBaseManager.connectToDB();

//                String queryInsert = "insert into testdatebase.loginData (main_username, main_password, e_mail)\n" +
//                        "values( '" + username + "', '" + password + "', " + "'" + email + "');";

                String queryInsert = "insert into gPOZ3L2sft.logindata (main_username, main_password, e_mail) " +
                        "values( '" + username + "', '" + password + "', " + "'" + email + "');";


                System.out.println(queryInsert);

                statement = connection.createStatement();
                System.out.println("Adding new user to table 'loginData: " + username + " " + password + " " + email);

                PreparedStatement preparedStatement = connection.prepareStatement(queryInsert);
                preparedStatement.execute();
                System.out.println("Executing query ended successfully");

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (statement != null) {
                        statement.close();
                    }
                } catch (SQLException e) {
                    System.out.println("Closing statement failed");
                }
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void cancel(){

        buttonCancel.setOnAction(event -> ((Node)(event.getSource())).getScene().getWindow().hide());
    }

}
