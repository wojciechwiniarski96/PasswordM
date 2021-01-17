package GUI;

import User.User;
import database.DataBaseManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

public class EditViewController implements Initializable {

    @FXML
    private TextField usernameTextfield;
    @FXML
    private TextField passwordTextfield;
    @FXML
    private TextField emailTextfield;
    @FXML
    private Button confirmationButton;
    @FXML
    private Button cancelButton;

    private DataBaseManager dataBaseManager;
    private Connection connection;
    private Statement statement;

    private String username;
    private String password; //TODO: encryption
    private String email;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dataBaseManager = new DataBaseManager();
        editInput();
        cancel();
    }

    public void editInput(){

        confirmationButton.setOnAction(event -> {

           username = usernameTextfield.getText();
           password = passwordTextfield.getText();
           email = emailTextfield.getText();


           connection = dataBaseManager.connectToDB();

           UserViewController userViewController = new UserViewController();
           userViewController

           String queryEdit = "";

        });
    }

    public void cancel(){

        cancelButton.setOnAction(event -> ((Node)(event.getSource())).getScene().getWindow().hide());
    }


}
