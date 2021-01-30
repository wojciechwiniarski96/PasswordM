package GUI;

import User.User;
import database.DataBaseManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class EditViewController implements Initializable {

    @FXML
    private TextField passwordTextField;
    @FXML
    private Button confirmationButton;
    @FXML
    private Button cancelButton;

    private DataBaseManager dataBaseManager;
    private Connection connection;

    private String username;
    private String password; //TODO: encryption
    private String email;

    private int id;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dataBaseManager = new DataBaseManager();
        editInput();
        cancel();

    }

    public void editInput(){
        confirmationButton.setOnAction(event -> {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            User u = (User)stage.getUserData();

            connection = dataBaseManager.connectToDB();

            String newPassword = passwordTextField.getText();

            username = u.getUsername();
            System.out.println("Username  " + username);
            System.out.println("New password " + newPassword);

            if(!passwordTextField.getText().equals("")){
                String updateUsername = "UPDATE gPOZ3L2sft.users " +
                        "SET users.main_password = '" + newPassword + "' " +
                        "WHERE main_username = '" + username + "';";

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(updateUsername);
                    preparedStatement.execute();
                    System.out.println("Executing 'UPDATE' query ended successfully");
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally{
                    try{
                        Statement statement = connection.createStatement();
                        if(statement != null){
                            statement.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    try{
                        if(connection != null){
                            connection.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            //TODO: ALERT FOR USER ABOUT EDITING THE INPUT
            stage.close();
            try{
                Parent root = FXMLLoader.load(getClass().getResource("/GUI/UserView.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    public void cancel(){

        cancelButton.setOnAction(event -> ((Node)(event.getSource())).getScene().getWindow().hide());
    }
//
//    public void setUsername(String username){
//        this.username = username;
//    }

//TODO: Password Manager not Database Manager
}
