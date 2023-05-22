package com.example.alphasolutionseksamen.repository;

import com.example.alphasolutionseksamen.DTO.Project_members;
import com.example.alphasolutionseksamen.model.Project;
import com.example.alphasolutionseksamen.model.Task;
import com.example.alphasolutionseksamen.model.User;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProjectMembersRepository {
    DBConnector connector;

    public List<Project> getUserProjects(int givenUserId) {
        List<Project> projects = new ArrayList<>();
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

    public boolean userHasProject(int userId) {
        try (Connection con = connector.getConnection()){
            String SQl = "SELECT COUNT(*) FROM project_members WHERE user_id = ? AND role = 'Administrator';";
            PreparedStatement pstmt = con.prepareStatement(SQl, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void assignUserProject(Project_members projectMember) {
        try (Connection con = connector.getConnection()){
            String SQl = "INSERT INTO project_members (user_id, hourly_rate, project_id, role) VALUES (?, ?, ?, ?)";
            PreparedStatement psmt = con.prepareStatement(SQl, Statement.RETURN_GENERATED_KEYS);
            psmt.setInt(1, projectMember.getUser_id());
            psmt.setInt(2, projectMember.getHourly_rate());
            psmt.setInt(3, projectMember.getProject_id());
            psmt.setString(4, projectMember.getRole());
            psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
