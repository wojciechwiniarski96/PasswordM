package User;

import database.DataBaseManager;
import testPackage.Database;

import javax.xml.crypto.Data;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserAction extends User {

    private static final Logger logger = Logger.getLogger(UserAction.class.getName());

//    public UserAction(String username, String password) {
//        super(username, password);
//    }

    public UserAction(){

    }
    public boolean LogIn(String username, String password){

        return getUsername().equals(username) && getPassword().equals(password);
    }

    public boolean userExist(String username) throws SQLException {

        Connection connection = null;
        PreparedStatement statement = null;
        DataBaseManager dbManager = new DataBaseManager();


        List<User> users = new ArrayList<>();

        try{
            connection = dbManager.connectToDB();
            connection.setAutoCommit(false);
            String selectQuery = "SELECT username, password FROM testDatebase.testtable WHERE " +
                    "username  = ?";

            statement = connection.prepareStatement(selectQuery);
            int counter = 1;
            statement.setString(counter++, username);
            ResultSet resultSet = statement.executeQuery(); //throws exception here
            while(resultSet.next()){
                User user = new User();
                user.setID(resultSet.getInt(1));
                user.setUsername(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                users.add(user);
            }
            return !users.isEmpty();

        }catch (SQLException e){

            e.printStackTrace();
            logger.log(Level.SEVERE, e.getMessage());
        }finally {

            if(statement != null){
                statement.close();
            }
            if(connection != null){
                connection.close();
            }
        }

        return !users.isEmpty();

    }

    public int saveUser(User user) throws SQLException{
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        DataBaseManager dbManager = new DataBaseManager();


        try{
            connection = dbManager.connectToDB();
            connection.setAutoCommit(false);
            String insertQuery = "INSERT INTO testtable(title, username, password, url) VALUES ('title', ?, ?, 'url')";
            statement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            int counter = 1;
            statement.setString(counter++, user.getUsername());
            statement.setString(counter++, user.getPassword());
            statement.executeUpdate();
            connection.commit();
            resultSet = statement.getGeneratedKeys();
            if(resultSet.next()){
                return resultSet.getInt(1);
            }
        }catch (SQLException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, e.getMessage());
            if (connection != null) {
                connection.rollback();
            }
        }finally {
            if(resultSet != null){
                resultSet.close();
            }
            if(statement != null){
                statement.close();
            }
            if(connection != null){
                connection.close();
            }
        }
        return 0;
    }


}
