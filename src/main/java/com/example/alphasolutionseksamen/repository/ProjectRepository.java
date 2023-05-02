package com.example.alphasolutionseksamen.repository;

import com.example.alphasolutionseksamen.model.Project;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository

public class ProjectRepository {

    String db_url = "jdbc:mysql://localhost:3306/projectmanagementDb";
    String uid = "root";
    String pwd = "Samim123";

    public void createProject(Project project) {
        try (Connection con = DriverManager.getConnection(db_url, uid, pwd))
        {
            String SQL = "INSERT INTO `projects` (`name`, `description`, `status`, `budget`, `start_date`, `end_date`) VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement pstmt = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, project.getName());
            pstmt.setString(2, project.getDescription());
            pstmt.setString(3, project.getStatus());
            pstmt.setDouble(4, project.getBudget());
            pstmt.setDate(5, new java.sql.Date(new java.util.Date().getTime()));
            pstmt.setDate(6, (Date) project.getEndDate());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Project> getProjects() {
        List<Project> projects = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(db_url, uid, pwd))
        {
            String SQL = "SELECT * FROM projects;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                int id = rs.getInt("project_id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String status = rs.getString("status");
                Double budget = rs.getDouble("budget");
                Date startDate = rs.getDate("start_date");
                Date endDate = rs.getDate("end_date");
                projects.add(new Project(id, name, description, status, budget, startDate, endDate));
            }
            return projects;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteProject (int projectId){
        try(Connection con = DriverManager.getConnection(db_url,uid,pwd)) {
            String SQL = "DELETE FROM projects WHERE project_id = ?;";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, projectId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
