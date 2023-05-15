package com.example.alphasolutionseksamen.controllers;

import com.example.alphasolutionseksamen.model.Project;
import com.example.alphasolutionseksamen.model.Subproject;
import com.example.alphasolutionseksamen.model.Task;
import com.example.alphasolutionseksamen.model.User;
import com.example.alphasolutionseksamen.repository.ProjectRepository;
import com.example.alphasolutionseksamen.repository.SubprojectRepository;
import com.example.alphasolutionseksamen.repository.TaskRepository;
import com.example.alphasolutionseksamen.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
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

    @GetMapping(path = "/json/projects")
    public ResponseEntity<List<Project>> getProjectsJSON(){
        List<Project> projects = projectRepository.getProjects();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    /*
     CONTROLLER FOR SUBPROJECTS
    */

    @GetMapping(path = "/json/subprojects")
    public ResponseEntity<List<Subproject>> getSubProjectsJSON(){
        List<Subproject> subprojects = subprojectRepository.getSubproject();
        return new ResponseEntity<>(subprojects, HttpStatus.OK);
    }

    @GetMapping(path = "/all/subprojects")
    public String getAllSubProjects(Model model, HttpSession session){
        List<Subproject> subprojects = subprojectRepository.getSubproject();
        model.addAttribute("subprojects", subprojects);
        return "subprojects";
    }


    @GetMapping(path = "/{id}/subprojects")
    public String getSubprojects(Model model, HttpSession session, @PathVariable int id) {
        User user = (User) session.getAttribute("user");
        List<Subproject> subprojects = subprojectRepository.getUserSubprojects(user.getUser_id(), id);
        model.addAttribute("subprojects", subprojects);
        model.addAttribute("project_id", id);
        return "subprojects";
    }

    @GetMapping("/create/subproject/{id}")
    public String createSubproject(@PathVariable int id, Model model) {
        Subproject subproject = new Subproject();
        model.addAttribute("subproject", subproject);
        return "subprojects";
    }

    @PostMapping("/create/subproject")
    public String createSubproject(@RequestParam("project_id") int project_id,
                                   @RequestParam("name") String name,
                                   @RequestParam("description") String description,
                                   @RequestParam("status") String status,
                                   @RequestParam("budget") Double budget,
                                   @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date endDate,
                                   @ModelAttribute("subproject") Subproject subproject,
                                   Model model,
                                   HttpSession session) {
        subproject.setProject_id(project_id);
        subprojectRepository.createSubproject(subproject);
        return "redirect:/"+ project_id + "/subprojects";
    }

    @PostMapping("/update/subproject")
    public String updateSubproject(@RequestParam("subproject_id") int subproject_id,
                                   @RequestParam("project_id") int project_id,
                                   @RequestParam("name") String name,
                                   @RequestParam("description") String description,
                                   @RequestParam("status") String status,
                                   @RequestParam("budget") Double budget,
                                   @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date startDate,
                                   @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date endDate,
                                   @ModelAttribute("subproject") Subproject subproject) {
        subprojectRepository.updateSubproject(subproject);
        return "redirect:/" + project_id + "/subprojects";
    }

    @GetMapping("/delete/subproject/{p_id}/{id}")
    public String deleteSubproject(@PathVariable int id, @PathVariable int p_id){
        subprojectRepository.deleteSubproject(id);
        return "redirect:/" + p_id + "/subprojects";
    }





    @GetMapping(path = "/projects")
    public String getProjects(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        List<Project> projects = projectRepository.getUserProjects(user.getUser_id());
        model.addAttribute("projects", projects);
        model.addAttribute("updateProject", new Project());
        return "projects";
    }




    @GetMapping("/create/project/{id}")
    public String createProject(@PathVariable int id, Model model) {
        Project project = new Project();
        model.addAttribute("project", project);
        return "projects";
    }


    @PostMapping("/create/project")
    public String createProject(@RequestParam("name") String name,
                                @RequestParam("description") String description,
                                @RequestParam("status") String status,
                                @RequestParam("budget") Double budget,
                                @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date endDate,
                                @ModelAttribute("project") Project project,
                                Model model,
                                HttpSession session) {
        User user = (User) session.getAttribute("user");
        projectRepository.createProject(project, user.getUser_id());
        return "redirect:/projects";
    }


    @PostMapping("/update/project")
    public String updateProject(@RequestParam("project_id") int project_id,
                                @RequestParam("name") String name,
                                @RequestParam("description") String description,
                                @RequestParam("status") String status,
                                @RequestParam("budget") Double budget,
                                @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date startDate,
                                @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date endDate,
                                @ModelAttribute("project") Project project) {
        projectRepository.updateProject(project);
        return "redirect:/projects";
    }


    @GetMapping("/delete/project/{id}")
    public String deleteProject(@PathVariable int id){
        projectRepository.deleteProject(id);
        return "redirect:/projects";
    }



    /*
     CONTROLLER FOR TASKS
    */

    @GetMapping(path = "/json/tasks")
    public ResponseEntity<List<Task>> getTasksJSON(){
        List<Task> tasks = taskRepository.getTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping(path = "/tasks")
    public String getTasks(Model model, HttpSession session){
        List<Task> tasks = taskRepository.getTasks();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    /*
     CONTROLLER FOR USERS
    */

    @GetMapping(path = "/users")
    public ResponseEntity<List<User>> getUsers(){
        List<User> users = userRepository.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/")
    public String homePage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email")String email, @RequestParam("pw") String pw,
                        HttpSession session,
                        Model model,
                        HttpServletRequest request)
    {
        User user = userRepository.getUserEmail(email);
        if (user != null) {
            if (user.getPassword().equals(pw)) {
                session.setAttribute("user", user);
                session.setMaxInactiveInterval(30);
                return "redirect:" + request.getContextPath() + "/projects";
            }
        }
        model.addAttribute("wrongCredentials", true);
        return "login";
    }
}
