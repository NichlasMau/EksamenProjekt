package com.example.alphasolutionseksamen.DTO;

public class Task_members {
    private int user_id;
    private int task_id;

    public Task_members(int user_id, int task_id) {
        this.user_id = user_id;
        this.task_id = task_id;
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
}
