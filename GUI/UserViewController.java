package GUI;

import User.User;
import database.DataBaseManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserViewController implements Initializable {

//    @FXML
//    private Label label;
    @FXML
    private TableView<User> tableUser;
    @FXML
    private TableColumn<User, Integer> colID;
    @FXML
    private TableColumn<User, String> colUsername;
    @FXML
    private TableColumn<User, String> colPassword;
    @FXML
    private TableColumn<User, String> colURL;
    @FXML
    private TableColumn<User, String> colEdit;
    @FXML
    private Button buttonAdd;

    private Connection connection;
    private ObservableList<User> list;
    private DataBaseManager dataBaseManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dataBaseManager = new DataBaseManager();

        addNewContent();
        populateButWithJoin();

    }


    private void populateButWithJoin() {

        try {
            list = FXCollections.observableArrayList();
            connection = dataBaseManager.connectToDB();
            ResultSet rs;

            String username = LoginViewController.username;
            String password = LoginViewController.password;

            String query = "SELECT logindata.id, users.main_username, users.main_password, users.url "+
                    "FROM gPOZ3L2sft.users " +
                    "LEFT JOIN gPOZ3L2sft.logindata " +
                    "ON gPOZ3L2sft.users.userID = gPOZ3L2sft.logindata.id " +
                    "WHERE gPOZ3L2sft.logindata.main_username = '" + username +"' " +
                    "AND gPOZ3L2sft.logindata.main_password = '" + password + "'; ";
//local
//            String query = "SELECT logindata.id, users1.username, users1.password, users1.url "+
//                    "FROM testdatebase.users1 " +
//                    "LEFT JOIN testdatebase.logindata " +
//                    "ON testdatebase.users1.userID = testdatebase.logindata.id " +
//                    "WHERE testdatebase.logindata.main_username = '" + username +"' " +
//                    "AND testdatebase.logindata.main_password = '" + password + "'; ";


            System.out.println(query);
            rs = connection.createStatement().executeQuery(query);

            while (rs.next()) {
                User user = new User();
                user.setID((rs.getInt("id")));
                user.setPassword(rs.getString("main_password"));
                user.setUsername(rs.getString("main_username"));
                user.setUrl(rs.getString("url"));
                list.add(user);

            }

            colID.setCellValueFactory(new PropertyValueFactory<>("id"));
            colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
            colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
            colURL.setCellValueFactory(new PropertyValueFactory<>("url"));

            Callback<TableColumn<User, String>, TableCell<User, String>> cellFactory =(param) -> new TableCell<User, String>(){
                @Override
                public void updateItem(String item, boolean empty){
                    super.updateItem(item, empty);

                    if(empty){
                        setGraphic(null);
                        setText(null);
                    }else{
                        final Button editButton = new Button("Edit");
                        editButton.setOnAction(event -> {
                            try {
                                Parent root = FXMLLoader.load(getClass().getResource("EditView.fxml"));
                                Stage stage = new Stage();
                                Scene scene = new Scene(root);
                                stage.setScene(scene);
                                stage.setTitle("Database Manager");
                                stage.show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            User u = getTableView().getItems().get(getIndex());



                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setContentText("You want to edit \n " + u.getUsername());
                            alert.show();
                        });

                        setGraphic(editButton);
                        setText(null);
                    }
                }
            };
            colEdit.setCellFactory(cellFactory);
            tableUser.setItems(list);
        }
        catch (SQLException e){
            Logger.getLogger(UserViewController.class.getName()).log(Level.SEVERE, null, e);
        }
    }


    public void getUserData(){
    }

    private void populateTableView() {

        try {
            list = FXCollections.observableArrayList();
            connection = dataBaseManager.connectToDB();
            ResultSet rs;

            String username = LoginViewController.username;
            String password = LoginViewController.password;

            String query = "SELECT id, username, password, url FROM testdatebase.Users WHERE main_username = '" + username +"'"
                    + "AND main_password = '" + password + "'"; //join
            rs = connection.createStatement().executeQuery(query);

            while (rs.next()) {
                User user = new User();
                user.setID((rs.getInt("id")));
                user.setPassword(rs.getString("password"));
                user.setUsername(rs.getString("username"));

                list.add(user);

            }
            //@31:09
            colID.setCellValueFactory(new PropertyValueFactory<>("id"));
            colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
            colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));


            Callback<TableColumn<User, String>, TableCell<User, String>> cellFactory =(param) -> {
                final TableCell<User, String> cell = new TableCell<User, String>(){
                    @Override
                    public void updateItem(String item, boolean empty){
                        super.updateItem(item, empty);

                        if(empty){
                            setGraphic(null);
                            setText(null);
                        }else{
                            final Button editButton = new Button("Edit");
                            editButton.setOnAction(event -> {
                                User u = getTableView().getItems().get(getIndex());

                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setContentText("You want to edit \n " + u.getUsername());
                                alert.show();
                            });

                            setGraphic(editButton);
                            setText(null);
                        }
                    }
                };
                return cell;
            };

            colEdit.setCellFactory(cellFactory);



            tableUser.setItems(list);

        }
        catch (SQLException e){
            Logger.getLogger(UserViewController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    //TODO: edit button, button for closing app in menu, copying password, new user

    private void addNewContent(){

        buttonAdd.setOnAction(event -> {
            try{
                Parent root = FXMLLoader.load(getClass().getResource("AddContentView.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Database Manager");
                stage.show();
            }catch (Exception e){
                e.printStackTrace();
            }
        });

    }
}
