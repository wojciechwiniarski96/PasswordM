package GUI;

import TwoFactorAuth.Authorizer;
import User.User;
import database.DataBaseManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.sound.midi.Soundbank;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class LoginViewController implements Initializable {

    private Connection connection;
    private DataBaseManager dataBaseManager;

    public static String username;
    public static String password;

    @FXML
    private TextField textFieldUsername;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button buttonLogin;
    @FXML
    private MenuItem exit;
    @FXML
    private MenuItem menuRegister;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataBaseManager = new DataBaseManager();
        login();
        register();
    }

    private void login(){

        buttonLogin.setOnAction(event -> {

            username = textFieldUsername.getText();
            password = passwordField.getText();

            User u = new User();

            u.setUsername(username);
            u.setPassword(password);

            try{
                Parent root = FXMLLoader.load(getClass().getResource("/GUI/TokenView.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Database Manager");
                stage.setUserData(u);
                stage.show();
                ((Node)(event.getSource())).getScene().getWindow().hide(); // closes login window after login
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    private void register(){
        menuRegister.setOnAction(event -> {
            try{
                Parent root = FXMLLoader.load(getClass().getResource("RegisterView.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Database Manager");
                stage.show();
//                ((Node)(event.getSource())).getScene().getWindow().hide(); // closes login window after login

            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }
}

//TODO: how to retrieve mail from db - for 2FA
