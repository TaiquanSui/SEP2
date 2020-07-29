package Shared.Model;

import java.io.Serializable;

public class User implements Serializable {

    private String id;
    private String email;
    private String password;
    private UserType userType;


    public User(){}

    public User(String email, String password, UserType userType) {
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getType() {
        return userType;
    }

    public void setType(UserType userType) {
        this.userType = userType;
    }
}
