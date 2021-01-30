package GUI;

import TwoFactorAuth.Authorizer;
import TwoFactorAuth.TokenGenerator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLOutput;
import java.util.ResourceBundle;

public class TokenViewController implements Initializable {
    @FXML
    private TextField tokenTextField;
    @FXML
    private Button loginButton;

    private int tokenReceived;
    private int tokenTyped;

    private Authorizer authorizer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginWithToken();
    }

    private boolean compareToken(int tokenReceived, int tokenTyped){

        return tokenReceived == tokenTyped;
    }

    private void loginWithToken(){

        authorizer = new Authorizer();
        authorizer.sendMail();
        tokenReceived = authorizer.getToken();
        System.out.println("Recived token: " + tokenReceived);

        loginButton.setOnAction(event -> {

            tokenTyped = Integer.parseInt(tokenTextField.getText());
            System.out.println("Token typed: " + tokenTyped);
            if (compareToken(tokenReceived, tokenTyped)) {
                Node node = (Node) event.getSource();
                Stage stage = (Stage)node.getScene().getWindow();
                stage.close();

                try{
                        Parent root = FXMLLoader.load(getClass().getResource("/GUI/UserView.fxml"));
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.setTitle("Database Manager");
                        stage.show();

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Token is incorrect");
                    alert.show();
                }
        });
    }



}
