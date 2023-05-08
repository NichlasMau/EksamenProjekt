package com.example.alphasolutionseksamen.repository;

import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static Connection con;
    static String db_url = "jdbc:mysql://localhost:3306/projectmanagementdb";
    static String uid = "root";
    static String pwd = "root";

    public static Connection getConnection() throws SQLException {
        con = DriverManager.getConnection(db_url, uid, pwd);
        return con;
    }
}
