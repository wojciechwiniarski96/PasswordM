//package GUI;
//
//
//
//import User.StringPool;
//import User.User;
//import User.UserAction;
//import database.DataBaseManager;
//import javafx.application.Application;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.text.Font;
//import javafx.scene.text.FontWeight;
//import javafx.scene.text.Text;
//import javafx.stage.Stage;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Alert.AlertType;
//import java.sql.SQLException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class StarterGUI extends Application {
//
//    private static final Logger logger = Logger.getLogger(StarterGUI.class.getName());
//    private DataBaseManager userDB = new DataBaseManager();
//    private UserAction userA = new UserAction();
//
//
//    public static void main(String[] args){
//        launch(args);
//    }
//
//    @Override
//    public void start(Stage primaryStage){
//        primaryStage.setTitle("PasswordManager");
//
//        GridPane grid = new GridPane();
//        grid.setAlignment(Pos.CENTER);
//        grid.setHgap(10);
//        grid.setVgap(10);
//        grid.setPadding(new Insets(25, 25, 25, 25));
//
//        Text scenetitle = new Text("Add User");
//        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
//        grid.add(scenetitle, 0, 0, 2, 1);
//
//
//        Label userNameLabel = new Label("Username");
//        grid.add(userNameLabel, 0, 1);
//
//        TextField usernameTextField = new TextField();
//        grid.add(usernameTextField, 1, 1);
//
//        Label passwordLabel = new Label("Password");
//        grid.add(passwordLabel, 0, 2);
//
//        PasswordField passwordField = new PasswordField();
//        grid.add(passwordField, 1,2);
//
//        // TODO: two factor authentication
////        Label tokenLabel = new Label("Token");
////        grid.add(tokenLabel, 0, 3);
////
////        TextField tokenTextField = new TextField();
////        grid.add(tokenLabel, 1, 3);
//
//        Button loginButton = new Button("Log in");
//        HBox hBox = new HBox(10);
//        hBox.setAlignment(Pos.BOTTOM_RIGHT);
//        hBox.getChildren().add(loginButton );
//        grid.add(hBox, 1, 3);
//
//        loginButton.setOnAction(actionEvent -> {
//            String username = usernameTextField.getText().trim();
//            String password = passwordField.getText();
//
//            //Fields cannot be empty
//            if(!StringPool.BLANK.equals(username) && !StringPool.BLANK.equals(password)){
//                try{
//                    if(!userA.userExist(username)){
//                        User user = this.CreateUser(username, password);
//                        int userID = userA.saveUser(user);
//                        if(userID > 0){
//                            this.alert("Save", "Successful!", AlertType.INFORMATION);
//                        }else {
//                            this.alert("Error", "Failed!", AlertType.ERROR);
//                        }
//                    }else {
//                        this.alert("Error", "User already exists!", AlertType.ERROR);
//                    }
//                }
//                catch (SQLException e){
//                    e.printStackTrace();
//                    logger.log(Level.SEVERE, e.getMessage());
//
//                }
//            }else{
//                this.alert("Error", "Please complete all fields!", AlertType.ERROR);
//            }
//
//        });
//
//    Scene scene = new Scene(grid, 300, 275);
//    primaryStage.setScene(scene);
//
//    primaryStage.show();
//
//    }
//    public void alert(String title, String message, Alert.AlertType alertType) {
//        Alert alert = new Alert(alertType);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//
//        alert.showAndWait();
//    }
//
//
//    public User CreateUser(String username, String password){
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(password);
//
//        return user;
//    }
//
//
//    //TODO: https://stackoverflow.com/questions/24488825/how-to-open-new-stage-and-close-previous-stage-between-classes
//    //TODO:     https://docs.oracle.com/javafx/2/ui_controls/table-view.htm
//    //TODO: https://www.youtube.com/watch?v=gvko7jLPZT0 -- wazne
//
//}
