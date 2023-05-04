package com.example.alphasolutionseksamen.repository;

import com.example.alphasolutionseksamen.model.Task;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepository {

    @Value("${spring.datasource.url}")
    String db_url;
    @Value("${spring.datasource.username}")
    String uid;
    @Value("${spring.datasource.password}")
    String pwd;


    public int createTask(Task newTask) {
        int taskID = 0;
        try (Connection con = DriverManager.getConnection(db_url, uid, pwd)){
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

    public List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(db_url, uid, pwd)) {
            String SQL = "SELECT * FROM tasks;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()){
                int task_id = rs.getInt("task_id");
                int subproject_id = rs.getInt("subproject_id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String status = rs.getString("status");
                Date start_date = rs.getDate("start_date");
                Date end_date = rs.getDate("end_date");
                double budget = rs.getDouble("budget");
                double estimated_time = rs.getDouble("estimated_time");

                tasks.add(new Task(task_id, subproject_id, name, description, status, start_date, end_date, budget, estimated_time));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tasks;
    }




}
