package com.example.alphasolutionseksamen.DTO;

import com.example.alphasolutionseksamen.model.User;
import com.example.alphasolutionseksamen.repository.UserRepository;

public class Task_members {
    private int user_id;
    private int task_id;

    private String name;

    public Task_members(int user_id, int task_id) {
        this.user_id = user_id;
        this.task_id = task_id;
    }

    public Task_members() {
    }

    public Task_members(int user_id, int task_id, String name) {
        this.user_id = user_id;
        this.task_id = task_id;
        this.name = name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return new UserRepository().getUser(user_id);
    }
}
