package com.example.alphasolutionseksamen.repository;


import com.example.alphasolutionseksamen.model.Subproject;
import com.example.alphasolutionseksamen.model.Task;
import com.example.alphasolutionseksamen.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;


import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Repository
public class TaskRepository {

    DBConnector connector;

    public void createTask(Task newTask) {
        try (Connection con = connector.getConnection()){
            String SQl = "INSERT INTO tasks (subproject_id, name, description, status, start_date, end_date, budget, estimated_time) values (?,?,?,?,?,?,?,?);";
            PreparedStatement psmt = con.prepareStatement(SQl, Statement.RETURN_GENERATED_KEYS);
            psmt.setInt(1, newTask.getSubproject_id());
            psmt.setString(2, newTask.getName());
            psmt.setString(3, newTask.getDescription());
            psmt.setString(4, newTask.getStatus());
            psmt.setDate(5, new java.sql.Date(new java.util.Date().getTime()));
            psmt.setTimestamp(6, Timestamp.valueOf(newTask.getEnd_date()));
            psmt.setDouble(7, newTask.getBudget());
            psmt.setDouble(8, newTask.getEstimated_time());
            psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Task> getUserTasks(int givenUserId, int givenSubprojectId) {
        List<Task> tasks = new ArrayList<>();
        try (Connection con = connector.getConnection()) {
            String SQL = "SELECT * FROM tasks t JOIN subprojects sp ON t.subproject_id = sp.subproject_id JOIN projects p ON sp.project_id = p.project_id JOIN project_members pm ON p.project_id = pm.project_id WHERE pm.user_id = ? AND t.subproject_id = ?";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, givenUserId);
            pstmt.setInt(2, givenSubprojectId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                int task_id = rs.getInt("task_id");
                int subproject_id = rs.getInt("subproject_id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String status = rs.getString("status");
                LocalDateTime start_date = rs.getTimestamp("start_date").toLocalDateTime();
                LocalDateTime end_date = rs.getTimestamp("end_date").toLocalDateTime();
                double budget = rs.getDouble("budget");
                double estimated_time = rs.getDouble("estimated_time");
                tasks.add(new Task(task_id, subproject_id, name, description, status, start_date, end_date, budget, estimated_time));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tasks;
    }

    public void updateTask(Task task) {
        try (Connection con = connector.getConnection()) {
            String SQL = "UPDATE tasks SET name = ?, description = ?, status = ?, start_date = ?, end_date = ?, budget = ?, estimated_time = ? WHERE task_id = ?";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, task.getName());
            pstmt.setString(2, task.getDescription());
            pstmt.setString(3, task.getStatus());
            pstmt.setTimestamp(4, Timestamp.valueOf(task.getStart_date()));
            pstmt.setTimestamp(5, Timestamp.valueOf(task.getEnd_date()));
            pstmt.setDouble(6, task.getBudget());
            pstmt.setDouble(7, task.getEstimated_time());
            pstmt.setInt(8, task.getTask_id());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteTask(int taskID) {
        try (Connection con = connector.getConnection()) {
            String SQL = "DELETE FROM tasks WHERE task_id = ?;";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, taskID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getTaskUsers(int taskId) {
        List<User> users = new ArrayList<>();
        try(Connection con = connector.getConnection()) {
            String SQL = """
                    SELECT users.user_id, users.name, users.email, users.username
                    FROM users
                    JOIN project_members ON project_members.user_id = users.user_id
                    JOIN projects ON projects.project_id = project_members.project_id
                    JOIN subprojects ON subprojects.project_id = projects.project_id
                    JOIN tasks ON tasks.subproject_id = subprojects.subproject_id
                    WHERE tasks.task_id = ?;
                    """;
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, taskId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("user_id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String username = rs.getString("username");
                users.add(new User(id, name, email, username));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }
}