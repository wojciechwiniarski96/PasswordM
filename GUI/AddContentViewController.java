package GUI;

import database.DataBaseManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class AddContentViewController implements Initializable {

    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField websiteTextField;
    @FXML
    private Button enterButton;
    @FXML
    private Button buttonCancel;


    private DataBaseManager dataBaseManager;
    private Connection connection;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dataBaseManager = new DataBaseManager();
        cancel();
        insertNewEntry();
    }

    public void insertNewEntry(){

        enterButton.setOnAction(event -> {
            String username = usernameTextField.getText();
            String password = passwordTextField.getText();
            String website = websiteTextField.getText();

            Statement statement = null;

            try {
                connection = dataBaseManager.connectToDB();

                String queryUseTable = "USE testdatebase;";
                String queryInsert = "INSERT INTO users(main_username, main_password, url) " +
                        "VALUES('" + username + "', '" + password + "', " + "'" + website + "');";

                String queryUpdateUID = "UPDATE users" +
                        " SET users.userID = (" +
                        " SELECT id FROM logindata" +
                        " WHERE main_username = '" + LoginViewController.username +"')" +
                        " WHERE users.main_username = '" + username +"';";

                System.out.println(queryUseTable);
                System.out.println(queryInsert);
                System.out.println(queryUpdateUID);

                String combinedQueries = queryInsert + queryUpdateUID;

                System.out.println("Combined queries " + combinedQueries);

                statement = connection.createStatement();
                System.out.println("Adding new content to table " + username + " " + password + " " + website);

                PreparedStatement preparedStatement = connection.prepareStatement(combinedQueries);
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

        });

    }

    public void cancel(){

        buttonCancel.setOnAction(event -> ((Node)(event.getSource())).getScene().getWindow().hide());
    }

    //https://www.w3schools.com/sql/sql_insert_into_select.asp

}
