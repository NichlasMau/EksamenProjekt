package com.example.alphasolutionseksamen.controllers;


import com.example.alphasolutionseksamen.model.Customer;
import com.example.alphasolutionseksamen.model.Task;
import com.example.alphasolutionseksamen.model.User;
import com.example.alphasolutionseksamen.repository.ProjectMembersRepository;
import com.example.alphasolutionseksamen.repository.TaskRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;


@Controller
public class TaskController {
    TaskRepository taskRepository;
    ProjectMembersRepository projectMembersRepository;


    public TaskController() {
        taskRepository = new TaskRepository();
        projectMembersRepository = new ProjectMembersRepository();
    }


    @GetMapping(path = "/{id}/tasks")
    public String getTasks(Model model, HttpSession session, @PathVariable int id) {
        User user = (User) session.getAttribute("user");
        Customer customer = (Customer) session.getAttribute("customer");


        List < Task > tasks;
        String username;


        if (user != null) {
            tasks = taskRepository.getUserTasks(user.getUser_id(), id);
            model.addAttribute("isUser", true);
            username = user.getName();
            if (projectMembersRepository.userHasProject(user.getUser_id())) {
                model.addAttribute("isAdmin", true);
            }
        } else if (customer != null) {
            tasks = taskRepository.getCustomerTasks(customer.getCustomer_id());
            username = customer.getName();
        } else {
            return "redirect:/";
        }
        List < User > assigned_users = taskRepository.getTaskUsers(id);
        model.addAttribute("tasks", tasks);
        model.addAttribute("subproject_id", id);
        model.addAttribute("project_id", taskRepository.getProjectIdByTaskId(id));
        model.addAttribute("assigned_users", assigned_users);
        model.addAttribute("username", username);
        model.addAttribute("subproject_budget", taskRepository.getSubprojectBudget(id));
        model.addAttribute("subproject_budgetUsed", taskRepository.getSubprojectBudgetUsed(id));
        model.addAttribute("subproject_budgetLeft", (taskRepository.getSubprojectBudget(id) - taskRepository.getSubprojectBudgetUsed(id)));


        model.addAttribute("estimatedTotalTime", taskRepository.getTotalEstimatedTime(id));
        model.addAttribute("estimatedTotalTimeFinished", taskRepository.getTotalEstimatedTimeFinished(id));
        model.addAttribute("estimatedTotalTimeLeft",(taskRepository.getTotalEstimatedTime(id) - taskRepository.getTotalEstimatedTimeFinished(id)));


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



