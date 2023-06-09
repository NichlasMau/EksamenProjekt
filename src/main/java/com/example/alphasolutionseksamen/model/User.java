package com.example.alphasolutionseksamen.model;

public class User {

    private int user_id;
    private String name;
    private String email;
    private String username;
    private String password;

    public User(int user_id, String name, String email, String username, String password) {
        this.user_id = user_id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public User(int user_id, String name, String email, String username) {
        this.user_id = user_id;
        this.name = name;
        this.email = email;
        this.username = username;
    }

    public User(String name, String email, String username, String password) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public User() {
    }


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
