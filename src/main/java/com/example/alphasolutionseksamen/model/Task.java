package com.example.alphasolutionseksamen.model;


import java.time.LocalDateTime;
import java.util.Date;


public class Task {
    private int task_id;
    private int subproject_id;
    private String name;
    private String description;
    private String status;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private double budget;
    private double estimated_time;


    public Task(int task_id, int subproject_id, String name, String description, String status, LocalDateTime start_date, LocalDateTime end_date, double budget, double estimated_time) {
        this.task_id = task_id;
        this.subproject_id = subproject_id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.start_date = start_date;
        this.end_date = end_date;
        this.budget = budget;
        this.estimated_time = estimated_time;
    }


    public Task() {
    }


    public int getTask_id() {
        return task_id;
    }


    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }


    public int getSubproject_id() {
        return subproject_id;
    }


    public void setSubproject_id(int subproject_id) {
        this.subproject_id = subproject_id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }


    public LocalDateTime getStart_date() {
        return start_date;
    }


    public void setStart_date(LocalDateTime start_date) {
        this.start_date = start_date;
    }


    public LocalDateTime getEnd_date() {
        return end_date;
    }


    public void setEnd_date(LocalDateTime end_date) {
        this.end_date = end_date;
    }


    public double getBudget() {
        return budget;
    }


    public void setBudget(double budget) {
        this.budget = budget;
    }


    public double getEstimated_time() {
        return estimated_time;
    }


    public void setEstimated_time(double estimated_time) {
        this.estimated_time = estimated_time;
    }
}



