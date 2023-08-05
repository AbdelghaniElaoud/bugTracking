package com.example.bugreporting.controller;

import com.example.bugreporting.model.Project;
import com.example.bugreporting.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    // Handler to show project creation form
    @GetMapping("/create")
    public String showProjectCreationForm(Model model) {
        model.addAttribute("project", new Project());
        return "project_create";
    }

    // Handler to process project creation form submission
    @PostMapping
    public String createProject(@ModelAttribute Project project) {
        projectRepository.save(project);
        return "redirect:/projects/list"; // Redirect to the project list page
    }

    // Handler to display the list of all projects
    @GetMapping("/list")
    public String showProjectList(Model model) {
        List<Project> projects = projectRepository.findAll();
        model.addAttribute("projects", projects);
        return "project_list";
    }

    // Other handlers and methods for project-related operations
    // ...
}

