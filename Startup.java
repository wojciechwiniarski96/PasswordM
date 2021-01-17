import User.UserAction;
import database.DataBaseManager;

import java.sql.SQLException;
import java.util.Scanner;

public class Startup {

    public static void main(String[] args){
//
//        //Login or Create new user
//        //Login - Username, Password, Token
//        //Password - TODO: Check if Password is safe
//        //Create - TODO: Create new User.User
//
//        //Get Password for a Website/APP
//        //Add new Password TODO: Password encryption
//        //Edit Password for a Website
//        //Read All inputs
//
//        DataBaseManager dataBaseManager;
//        Scanner scanner = new Scanner(System.in);
//
////        System.out.println("Welcome to prototype of Password Manager");
////        System.out.println("Do you want to login or to create new account? \nIf you want to login enter 'login', " +
////                "if you want to create new account, enter 'create'");
//
////        if(scanner.next().equals("login")){
////            System.out.println("Enter your username");
////            String username = scanner.next();
////            System.out.println("Enter your password");
////            String password = scanner.next();
////
////            dataBaseManager = new DataBaseManager(username, password);
////
////            if(dataBaseManager.loginToDB(username, password)){
////                System.out.println("Great, you are now logged in");
////            }else{
////                System.out.println("User does not exist");
////            }
////
////        }else{
////            System.out.println("Enter your username");
////            String username = scanner.next();
////            System.out.println("Enter your password");
////            String password = scanner.next();
////
////            dataBaseManager = new DataBaseManager(username, password);
////            dataBaseManager.create();
////        }
////
////        dataBaseManager.insertStatement("test", "test");
//
//        dataBaseManager = new DataBaseManager("testq", "qwer1234");
//        dataBaseManager.createDB();
//        dataBaseManager.createTable("testTableName");
//
//
//        for(int i = 0; i < 10; i++){ //NUR BEISPIEL FÜR 10 EINGABEN IN DB
//            dataBaseManager.insertStatement("testTableName", "wwww.website" + i + ".com", "password1234" + i);
//        }
//
//        dataBaseManager.selectStatement("testTableName", "*");
//

        DataBaseManager dataBaseManager = new DataBaseManager();
   //     dataBaseManager.createDB();

        //UserAction userAction = new UserAction("name", "password");
//
//        try{
//            DataBaseManager.connectToDB();
//        }catch (SQLException e){
//            e.printStackTrace();
//        }

//        dataBaseManager.createTable("Users");
//
//        for(int i = 0; i < 10; i++){ //NUR BEISPIEL FÜR 10 EINGABEN IN DB
//                dataBaseManager.insertStatement("Users", i + "user" + i, i + "password" + i, "wwww.website1" + i + ".com");
//            }
//
//        UserAction userAction = new UserAction();

        dataBaseManager.connectToDB();

  }
}
