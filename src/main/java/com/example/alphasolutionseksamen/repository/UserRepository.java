package com.example.alphasolutionseksamen.repository;


import com.example.alphasolutionseksamen.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
    public class UserRepository {
        @Value("${spring.datasource.url}")
        String url;
        @Value("${spring.datasource.username}")
        String user_id;
        @Value("${spring.datasource.password}")
        String user_pwd;


    public int createUser(User newUser) {
    int userID = 0;
    try (Connection con = DriverManager.getConnection(url, user_id, user_pwd)) {
        String SQL = "INSERT INTO User (name, email, username, password) values (?,?,?,?);";
        PreparedStatement psmt = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
        psmt.setString(1, newUser.getName());
        psmt.setString(2, newUser.getEmail());
        psmt.setString(3, newUser.getUsername());
        psmt.setString(4, newUser.getPassword());
        psmt.executeUpdate();

        ResultSet rs = psmt.getGeneratedKeys();
        if(rs.next()) {
            userID = rs.getInt(1);
        }

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
        return userID;
    }

    public User getUser(int uid) {


        return null;
    }
}

