package com.example.alphasolutionseksamen.repository;

import com.example.alphasolutionseksamen.model.User;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    DBConnector connector;

    public String createUser(User newUser) {
        try (Connection con = connector.getConnection()) {
            String SQL = "INSERT INTO users (name, email, username, password) values (?,?,?,?);";
            PreparedStatement psmt = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            psmt.setString(1, newUser.getName());
            psmt.setString(2, newUser.getEmail());
            psmt.setString(3, newUser.getUsername());
            psmt.setString(4, newUser.getPassword());
            psmt.executeUpdate();
            return newUser.getEmail();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUser(int userid) {
        User user = null;
        try (Connection con = connector.getConnection()) {
            String SQL = "SELECT * FROM users WHERE user_id = ?;";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, userid);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int id = (rs.getInt("user_id"));
                String name = (rs.getString("name"));
                String email = (rs.getString("email"));
                String username = (rs.getString("username"));
                String password = (rs.getString("password"));
                user = new User(id, name, email, username, password);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public User getUserEmail(String checkMail) {
        User user = null;
        try (Connection con = connector.getConnection()) {
            String SQL = "SELECT * FROM users WHERE email = ?;";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, checkMail);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("user_id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String username = rs.getString("username");
                String password = rs.getString("password");
                user = new User(id, name, email, username, password);
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(int userID) {
        try (Connection con = connector.getConnection()) {
            String SQL = "DELETE FROM users WHERE user_id = ?;";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, userID);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List < User > getAllUsers() {
        List < User > users = new ArrayList < > ();
        try (Connection con = connector.getConnection()) {
            String SQL = "SELECT * FROM users";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("user_id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String username = rs.getString("username");
                String password = rs.getString("password");
                users.add(new User(id, name, email, username, password));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }
}