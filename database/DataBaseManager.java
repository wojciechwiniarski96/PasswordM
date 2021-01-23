package database;

import User.UserAction;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DataBaseManager extends UserAction {

//    private static final Logger logger = Logger.getLogger(DataBaseManager.class.getName());
//    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
//    private final static String URL = "jdbc:mysql://localhost:3306/testDatebase?allowMultiQueries=TRUE&serverTimezone=UTC";
//    private final static String DB_USER = "root";
//    private final static String DB_PASSWORD= "qwer1234";

    private static final Logger logger = Logger.getLogger(DataBaseManager.class.getName());
    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final static String URL = "jdbc:mysql://remotemysql.com:3306/gPOZ3L2sft?allowMultiQueries=TRUE&serverTimezone=UTC";
    private final static String DB_USER = "gPOZ3L2sft";
    private final static String DB_PASSWORD= "**************";



//    public DataBaseManager(String username, String password){
//        super(username, password);
//
//    }

    public DataBaseManager(){

    }

    public Connection connectToDB() {

        Connection connection = null;

        try {
            Class.forName(DRIVER);
            System.out.println("Connecting to database...");

            //Get a connection to database
            connection =  DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
            System.out.println("Connection established");
            return connection;

        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
            logger.log(Level.SEVERE, e.getMessage());
        }


        return connection;
    }

    public void createDB(){
        Connection connection = null;
        Statement statement = null;

        String dataBaseName = super.getUsername();
        try{
            Class.forName(DRIVER);
            System.out.println("Connecting to database...");
            //Get a connection to database
            connection = DriverManager.getConnection(URL, DB_USER, DB_PASSWORD); //Gets connection with database

            System.out.println("Creating database...");

            //create a statement
            statement = connection.createStatement();

            String sql = "CREATE DATABASE testDatebase";

            statement.executeUpdate(sql);
            System.out.println("Database created successfully...\n");

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try{
                if(statement != null){
                    statement.close();
                }
            }catch (SQLException e){
                System.out.println("Closing statement failed");
            }
            try{
                if(connection != null){
                    connection.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public void createTable(String tableName){
        Connection conn = null;
        Statement statement = null;

        try {
            Class.forName(DRIVER);
            System.out.println("\nDriver found: " + DRIVER);

            conn = DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
            System.out.println("Connection successful...");

            statement = conn.createStatement();


            //query for users with hashed passwords - part of user view - temporary
//            String queryCreateTable =
//                    "CREATE TABLE " + "testDatebase" + "." + tableName + " " +
//                    "(id INT(6) NOT NULL AUTO_INCREMENT, " +
//                    "main_username VARCHAR(30) NOT NULL, " +
//                    "main_password VARCHAR(30) NOT NULL, " +
//                    "title VARCHAR(30) NOT NULL, " +
//                    "username VARCHAR(30) NOT NULL, " +
//                    "password VARCHAR(64) NOT NULL, " +
//                    "url VARCHAR(30) NOT NULL, " +
//                    "PRIMARY KEY(id))";

            String queryCreateTable =
                    "CREATE TABLE testDatebase.loginData " +
                            "(id INT(6) NOT NULL AUTO_INCREMENT, " +
                            "main_username VARCHAR(30) NOT NULL, " +
                            "main_password VARCHAR(30) NOT NULL, " +
                            "PRIMARY KEY(id))";

            System.out.println("creating query as statement: " + queryCreateTable + "\n");

            statement.executeUpdate(queryCreateTable);

            System.out.println("Executing statement as query");

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
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void selectStatement(String tableName, String columnName) {
        Connection conn = null;
        Statement statement = null;

        try {
            Class.forName(DRIVER);
            System.out.println("\nDriver found: " + DRIVER);

            conn = DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
            System.out.println("Connection successful... \n");

            String querySelect = "SELECT " + columnName + " FROM " + "testdatebase." + tableName;
            statement = conn.createStatement();
            System.out.println("Creating query as statement: " + querySelect + "\n");

            ResultSet rs = statement.executeQuery(querySelect);


            //Iterate through the java resultSet
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String url = rs.getString("url");

                System.out.format("%s, %s, %s, %s, %s\n", id, title, username, password, url);
            }

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
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void insertStatement(String tablename, String username, String password, String website) {
        Connection conn = null;
        Statement statement = null;

        try {
            Class.forName(DRIVER);
            //System.out.println("\n\nDriver found: " + DRIVER);

            conn = DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
            System.out.println("Connection successful...");


            String queryInsert = "insert into testDatebase.users  (main_username, main_password, title, username, password, URL)\n" +
                                  "values('user2', 'password2', 'testTitle', '" + username + "', '" + password + "', '" + website + "');";
            statement = conn.createStatement();
            System.out.println("creating query as statement: " + queryInsert + "\n");


            PreparedStatement preparedStatement = conn.prepareStatement(queryInsert);
            preparedStatement.execute();
            System.out.println("Executing statement as query");


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
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateStatement() {
        Connection conn = null;
        Statement statement = null;

        try {
            Class.forName(DRIVER);
            //System.out.println("\n\nDriver found: " + DRIVER);

            conn = DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
            System.out.println("Connection successful...");


            String queryUpdate = "UPDATE testdatebase.users " +
                    "SET title = 'test' " +
                    "WHERE main_username = 'user1'" ;
            statement = conn.createStatement();
            System.out.println("creating query as statement: " + queryUpdate + "\n");


            PreparedStatement preparedStatement = conn.prepareStatement(queryUpdate);
            preparedStatement.execute();
            System.out.println("Executing statement as query");


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
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
