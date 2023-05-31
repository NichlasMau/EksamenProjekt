package com.example.alphasolutionseksamen.repository;


import com.example.alphasolutionseksamen.DTO.Task_members;
import com.example.alphasolutionseksamen.model.User;
import org.springframework.stereotype.Repository;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository
public class TaskMembersRepository {
    DBConnector connector;


    public List<User> getTaskMembers(int taskId) {
        List< User > users = new ArrayList< >();
        try (Connection con = connector.getConnection()) {
            String SQL = "SELECT users.* FROM users JOIN task_members ON users.user_id = task_members.user_id WHERE task_members.task_id = ?;";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, taskId);
            ResultSet rs = pstmt.executeQuery();


            if (rs.next()) {
                int id = (rs.getInt("user_id"));
                String name = (rs.getString("name"));
                String email = (rs.getString("email"));
                String username = (rs.getString("username"));
                String password = (rs.getString("password"));
                users.add(new User(id, name, email, username, password));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public List<Task_members> getTaskMembersAll(int subprojectId) {
        List<Task_members> taskMembers = new ArrayList< >();
        try (Connection con = connector.getConnection()) {
            String SQL = "SELECT tm.task_member_id, u.user_id, u.name AS user_name, t.task_id FROM task_members tm JOIN users u ON tm.user_id = u.user_id JOIN tasks t ON tm.task_id = t.task_id JOIN subprojects sp ON t.subproject_id = sp.subproject_id WHERE sp.subproject_id = ?;";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, subprojectId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int user_id = (rs.getInt("user_id"));
                String user_name = (rs.getString("user_name"));
                int task_id = (rs.getInt("task_id"));
                taskMembers.add(new Task_members(user_id, task_id, user_name));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return taskMembers;
    }

    public boolean isTaskAssigned(int taskId) {
        try (Connection con = connector.getConnection()) {
            String SQl = "SELECT EXISTS (SELECT 1 FROM task_members WHERE task_id = ? ) AS task_exists;";
            PreparedStatement pstmt = con.prepareStatement(SQl, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, taskId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("task_exists");
                return count > 0;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void assignUserTask(Task_members taskMembers) {
        try (Connection con = connector.getConnection()) {
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



