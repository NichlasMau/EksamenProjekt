package com.example.alphasolutionseksamen.controllers;


import com.example.alphasolutionseksamen.DTO.Task_members;
import com.example.alphasolutionseksamen.repository.ProjectRepository;
import com.example.alphasolutionseksamen.repository.TaskMembersRepository;
import com.example.alphasolutionseksamen.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class TaskMemberController {
    UserRepository userRepository;
    TaskMembersRepository taskMembersRepository;
    ProjectRepository projectRepository;


    public TaskMemberController() {
        userRepository = new UserRepository();
        taskMembersRepository = new TaskMembersRepository();
        projectRepository = new ProjectRepository();
    }


    @PostMapping("/assignUser/task")
    public ResponseEntity< String > assignUserProject(@RequestParam("user_id") int user_id,
                                                      @RequestParam("task_id") int task_id,
                                                      @RequestParam("subproject_id") int subproject_id,
                                                      @ModelAttribute("task_members") Task_members taskMembers) {
        if(taskMembersRepository.isTaskAssigned(task_id)) {
            return ResponseEntity.badRequest().body("A user is already assigned");
        }
        Task_members task_members = new Task_members(user_id, task_id);
        taskMembersRepository.assignUserTask(task_members);
        return ResponseEntity.ok("SUCCESS");
    }
}



