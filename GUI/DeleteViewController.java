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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DeleteViewController implements Initializable {

    @FXML
    private Button confirmButton;
    @FXML
    private Button cancelButton;

    private DataBaseManager dataBaseManager;

    private Connection connection;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dataBaseManager = new DataBaseManager();
        confirmDeletion();

        cancel();
    }

    private void confirmDeletion(){

        confirmButton.setOnAction(event -> {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            User u = (User)stage.getUserData();

            String username = u.getUsername();

            connection = dataBaseManager.connectToDB();

            System.out.println("This user will be deleted: " + username);
            String deleteInput = "DELETE FROM gPOZ3L2sft.users " +
                                    "WHERE main_username = '" + username + "';";

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(deleteInput);
                preparedStatement.execute();
                System.out.println("Executing 'DELETE' query ended successfully");
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
        cancelButton.setOnAction(event -> {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
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


}
