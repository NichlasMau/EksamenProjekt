package com.example.alphasolutionseksamen.controllers;

import com.example.alphasolutionseksamen.model.Project;
import com.example.alphasolutionseksamen.model.Subproject;
import com.example.alphasolutionseksamen.model.Task;
import com.example.alphasolutionseksamen.model.User;
import com.example.alphasolutionseksamen.repository.ProjectRepository;
import com.example.alphasolutionseksamen.repository.SubprojectRepository;
import com.example.alphasolutionseksamen.repository.TaskRepository;
import com.example.alphasolutionseksamen.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class ProjectController {

    ProjectRepository projectRepository;
    SubprojectRepository subprojectRepository;
    TaskRepository taskRepository;

    UserRepository userRepository;

    public ProjectController() {
        projectRepository = new ProjectRepository();
        subprojectRepository = new SubprojectRepository();
        taskRepository = new TaskRepository();
        userRepository = new UserRepository();
    }

     /*
     CONTROLLER FOR PROJECTS
     */

    @GetMapping(path = "/projects")
    public ResponseEntity<List<Project>> getProjects(){
        List<Project> projects = projectRepository.getProjects();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    /*
     CONTROLLER FOR SUBPROJECTS
    */

    @GetMapping(path = "/subprojects")
    public ResponseEntity<List<Subproject>> getSubProjects(){
        List<Subproject> subprojects = subprojectRepository.getSubproject();
        return new ResponseEntity<>(subprojects, HttpStatus.OK);
    }

    /*
     CONTROLLER FOR TASKS
    */

    @GetMapping(path = "/tasks")
    public ResponseEntity<List<Task>> getTasks(){
        List<Task> tasks = taskRepository.getTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    /*
     CONTROLLER FOR USERS
    */

    @GetMapping(path = "/users")
    public ResponseEntity<List<User>> getUsers(){
        List<User> users = userRepository.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
