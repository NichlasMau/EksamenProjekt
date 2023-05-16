package com.example.alphasolutionseksamen.DTO;

public class Project_members {
    private int user_id;
    private int hourly_rate;
    private int project_id;
    private String role;

    public Project_members(int user_id, int hourly_rate, int project_id, String role) {
        this.user_id = user_id;
        this.hourly_rate = hourly_rate;
        this.project_id = project_id;
        this.role = role;
    }

    public Project_members() {
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getHourly_rate() {
        return hourly_rate;
    }

    public void setHourly_rate(int hourly_rate) {
        this.hourly_rate = hourly_rate;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
