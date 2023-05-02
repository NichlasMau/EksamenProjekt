package com.example.alphasolutionseksamen.repository;

import com.example.alphasolutionseksamen.model.Task;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class TaskRepository {
    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String user_id;
    @Value("${spring.datasource.password}")
    String user_pwd;

    public int createTask(Task newTask) {
        int taskID = 0;
        try (Connection con = DriverManager.getConnection(url, user_id, user_pwd)){
            String SQl = "INSERT INTO tasks (task_id, subproject_id, name, description, status, start_date, end_date, budget, estimated_time) values (?,?,?,?,?,?,?,?,?);";
            PreparedStatement psmt = con.prepareStatement(SQl, Statement.RETURN_GENERATED_KEYS);
            psmt.setInt(1, newTask.getTask_id());
            psmt.setInt(2, newTask.getSubproject_id());
            psmt.setString(3, newTask.getName());
            psmt.setString(4, newTask.getDescription());
            psmt.setString(5, newTask.getStatus());
            psmt.setDate(6, (Date) newTask.getStart_date());
            psmt.setDate(7, (Date) newTask.getEnd_date());
            psmt.setDouble(8, newTask.getBudget());
            psmt.setDouble(9, newTask.getEstimated_time());
            psmt.executeUpdate();

            ResultSet rs = psmt.getGeneratedKeys();
            if (rs.next()) {
                taskID = rs.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return taskID;
    }
}
