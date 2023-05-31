package com.example.alphasolutionseksamen.repository;


import com.example.alphasolutionseksamen.model.Project;
import org.springframework.stereotype.Repository;


import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Repository


public class ProjectRepository {
    DBConnector connector;


    public void createProject(Project project, int givenUserId) {
        try (Connection con = DBConnector.getConnection()) {
            String SQL = "INSERT INTO `projects` (`name`, `description`, `status`, `budget`, `start_date`, `end_date`) VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement pstmt = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement pstmt2 = con.prepareStatement("INSERT INTO project_members (user_id, hourly_rate, project_id, role) VALUES (?, ?, ?, 'Administrator')");
            pstmt.setString(1, project.getName());
            pstmt.setString(2, project.getDescription());
            pstmt.setString(3, project.getStatus());
            pstmt.setDouble(4, project.getBudget());
            pstmt.setDate(5, new java.sql.Date(new java.util.Date().getTime()));
            pstmt.setTimestamp(6, Timestamp.valueOf(project.getEndDate()));
            pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                rs.next();
                int projectId = rs.getInt(1);
                pstmt2.setInt(1, givenUserId);
                pstmt2.setFloat(2, 0);
                pstmt2.setInt(3, projectId);
                pstmt2.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List < Project > getUserProjects(int givenUserId) {
        List < Project > projects = new ArrayList < > ();
        try (Connection con = DBConnector.getConnection()) {
            String SQL = "SELECT * FROM projects p JOIN project_members pm ON p.project_id = pm.project_id WHERE pm.user_id = ?";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, givenUserId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("project_id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String status = rs.getString("status");
                Double budget = rs.getDouble("budget");
                LocalDateTime startDate = rs.getTimestamp("start_date").toLocalDateTime();
                LocalDateTime endDate = rs.getTimestamp("end_date").toLocalDateTime();
                projects.add(new Project(id, name, description, status, budget, startDate, endDate));
            }
            return projects;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List < Project > getCustomerProjects(int givenCustomerId) {
        List < Project > projects = new ArrayList < > ();
        try (Connection con = DBConnector.getConnection()) {
            String SQL = "SELECT * FROM projects p JOIN project_customers pm ON p.project_id = pm.project_id WHERE pm.customer_id = ?";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, givenCustomerId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("project_id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String status = rs.getString("status");
                Double budget = rs.getDouble("budget");
                LocalDateTime startDate = rs.getTimestamp("start_date").toLocalDateTime();
                LocalDateTime endDate = rs.getTimestamp("end_date").toLocalDateTime();
                projects.add(new Project(id, name, description, status, budget, startDate, endDate));
            }
            return projects;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void updateProject(Project project) {
        try (Connection con = DBConnector.getConnection()) {
            String SQL = "UPDATE projects SET name = ?, description = ?, status = ?, budget = ?, start_date = ?, end_date = ? WHERE project_id = ?";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, project.getName());
            pstmt.setString(2, project.getDescription());
            pstmt.setString(3, project.getStatus());
            pstmt.setDouble(4, project.getBudget());
            pstmt.setTimestamp(5, Timestamp.valueOf(project.getStartDate()));
            pstmt.setTimestamp(6, Timestamp.valueOf(project.getEndDate()));
            pstmt.setInt(7, project.getProject_id());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void deleteProject(int projectId) {
        try (Connection con = DBConnector.getConnection()) {
            String SQL = "DELETE FROM projects WHERE project_id = ?;";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, projectId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}



