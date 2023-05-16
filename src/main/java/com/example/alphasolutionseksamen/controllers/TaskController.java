package com.example.alphasolutionseksamen.controllers;

import com.example.alphasolutionseksamen.model.Task;
import com.example.alphasolutionseksamen.model.User;
import com.example.alphasolutionseksamen.repository.TaskRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class TaskController {
    TaskRepository taskRepository;

    public TaskController() {
        taskRepository = new TaskRepository();
    }

    @GetMapping(path = "/{id}/tasks")
    public String getTasks(Model model, HttpSession session, @PathVariable int id) {
        User user = (User) session.getAttribute("user");
        List<Task> tasks = taskRepository.getUserTasks(user.getUser_id(), id);
        List<User> assigned_users = taskRepository.getTaskUsers(id);
        model.addAttribute("tasks", tasks);
        model.addAttribute("subproject_id", id);
        System.out.println(assigned_users);
        model.addAttribute("assigned_users", assigned_users);
        return "tasks";
    }

    @PostMapping("/create/task")
    public String createTask(@RequestParam("subproject_id") int subproject_id,
                             @RequestParam("name") String name,
                             @RequestParam("description") String description,
                             @RequestParam("status") String status,
                             @RequestParam("budget") String budget,
                             @RequestParam("end_date") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date endDate,
                             @RequestParam("estimated_time") Double estimated_time,
                             @ModelAttribute("task") Task task,
                             Model model,
                             HttpSession session) {
        task.setSubproject_id(subproject_id);
        taskRepository.createTask(task);
        return "redirect:/" + subproject_id + "/tasks";
    }

    @PostMapping("/update/task")
    public String updateTask(@RequestParam("task_id") int task_id,
                             @RequestParam("subproject_id") int subproject_id,
                             @RequestParam("name") String name,
                             @RequestParam("description") String description,
                             @RequestParam("status") String status,
                             @RequestParam("budget") Double budget,
                             @RequestParam("estimated_time") Double estimated_time,
                             @RequestParam("start_date") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date startDate,
                             @RequestParam("end_date") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date endDate,
                             @ModelAttribute("task") Task task) {
        taskRepository.updateTask(task);
        return "redirect:/" + subproject_id + "/tasks";
    }

    @GetMapping("/delete/task/{sp_id}/{id}")
    public String deleteTask(@PathVariable int id, @PathVariable int sp_id) {
        taskRepository.deleteTask(id);
        return "redirect:/" + sp_id + "/tasks";
    }

}
