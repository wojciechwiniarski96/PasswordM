package User;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {

    public User(){

    }

//    User(StringProperty username, StringProperty password){
//
//        getUsername();
//        getPassword();
//    }


    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty username = new SimpleStringProperty();
    private final StringProperty password = new SimpleStringProperty();
    private final StringProperty url = new SimpleStringProperty();

    public String getUsername(){

        return username.get();
    }

    public void setUsername(String username){

        this.username.set(username);
    }

    public StringProperty usernameProperty(){

        return username;
    }

    public String getPassword(){

        return password.get();
    }

    public void setPassword(String password){

        this.password.set(password);
    }

    public StringProperty passwordProperty(){

        return password;
    }

    public int getID(){

        return id.get();
    }

    public void setID(int id){

        this.id.set(id);
    }

    public IntegerProperty idProperty(){

        return id;
    }

    public StringProperty urlProperty(){

        return url;
    }

    public void setUrl(String url){

        this.url.set(url);
    }




    //Version before 10.12
    /*
    private int id;
    private String username;

    private String password;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public User(){

    }
    public int getID(){

        return id;
    }

    public void setID(int id){

        this.id = id;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username){

        this.username = username;
    }

    public String getPassword() {

        return password;
    }
    public void setPassword(String password){

        this.password = password;
    }
*/




}
