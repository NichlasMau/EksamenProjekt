package com.example.alphasolutionseksamen.repository;

import com.example.alphasolutionseksamen.DTO.Project_members;
import com.example.alphasolutionseksamen.DTO.Task_members;
import com.example.alphasolutionseksamen.model.User;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskMembersRepository {
    DBConnector connector;

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

    public void assignUserTask(Task_members taskMembers) {
        try (Connection con = connector.getConnection()){
            String SQl = "INSERT INTO task_members (user_id, task_id) VALUES (?, ?)";
            PreparedStatement psmt = con.prepareStatement(SQl, Statement.RETURN_GENERATED_KEYS);
            psmt.setInt(1, taskMembers.getUser_id());
            psmt.setInt(2, taskMembers.getTask_id());
            psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
