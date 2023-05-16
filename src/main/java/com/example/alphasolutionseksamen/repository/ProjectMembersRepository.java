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
