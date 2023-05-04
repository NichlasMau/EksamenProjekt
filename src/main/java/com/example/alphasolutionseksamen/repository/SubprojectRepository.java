package com.example.alphasolutionseksamen.repository;

import com.example.alphasolutionseksamen.model.Subproject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class    SubprojectRepository {

    DBConnector connector;

    public List<Subproject> getSubproject() {
        List<Subproject> subprojects = new ArrayList<>();
        try (Connection con = connector.getConnection()) {
            String SQL = "SELECT * FROM subprojects;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                int id = rs.getInt("subproject_id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                Date startDate = rs.getDate("start_date");
                Date endDate = rs.getDate("end_date");
                String status = rs.getString("status");
                Double budget = rs.getDouble("budget");
                subprojects.add(new Subproject(id, name, description, status, budget, startDate, endDate));
            }
            return subprojects;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createSubproject(Subproject subprojects) {
        try (Connection con = connector.getConnection()) {
            String SQL = "INSERT INTO `subproject` (`id`, `name`, `description`, `status`,`budget`,`startDate`,`endDate` ) VALUES (1, ?, ?, ?,?,?,?);";
            PreparedStatement pstmt = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, subprojects.getName());
            pstmt.setString(2, subprojects.getDescription());
            pstmt.setString(3, subprojects.getStatus());
            pstmt.setDouble(4, subprojects.getBudget());
            pstmt.setDate(5, new java.sql.Date(new java.util.Date().getTime()));
            pstmt.setDate(6, (Date) subprojects.getEndDate());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteSubproject(int subprojectID) {
        try (Connection con = connector.getConnection()) {
            String SQL = "DELETE FROM subprojects WHERE subproject_id = ?;";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, subprojectID);
            pstmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}