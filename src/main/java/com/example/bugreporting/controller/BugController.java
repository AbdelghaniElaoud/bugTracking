package com.example.bugreporting.controller;

import com.example.bugreporting.model.Bug;
import com.example.bugreporting.model.Project;
import com.example.bugreporting.repository.ProjectRepository;
import com.example.bugreporting.service.BugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/bugs")
public class BugController {
    private final BugService bugService;

    @Autowired
    ProjectRepository projectRepository;

    private static final String UPLOAD_DIR = "C://Users//Electro-Market.ma//Desktop//saving"; // Directory to store attachments

    @Autowired
    public BugController(BugService bugService) {
        this.bugService = bugService;
    }

    @GetMapping
    public String listBugs(Model model) {
        model.addAttribute("bugs", bugService.getAllBugs());
        return "bug-list"; // Thymeleaf template name for bug list view
    }

    // Handler to show bug creation form
    @GetMapping("/create")
    public String showBugCreationForm(Model model) {
        List<Project> projects = projectRepository.findAll();
        model.addAttribute("bug", new Bug());
        model.addAttribute("projects", projects);
        return "bug-create";
    }

    @PostMapping("/create")
    public String createBug(@ModelAttribute Bug bug, @RequestParam("file") MultipartFile file) {
        fileStoringHandler(file, bug);
        return "redirect:/bugs";
    }


    @GetMapping("/download/{bugId}")
    public ResponseEntity<Resource> downloadAttachment(@PathVariable Long bugId) {
        // Retrieve the bug by ID
        Bug bug = bugService.getBugById(bugId);
        if (bug == null || bug.getAttachmentPath() == null) {
            // Handle not found or bug without attachment
            return ResponseEntity.notFound().build();
        }

        // Load the file resource
        Resource resource = new FileSystemResource(bug.getAttachmentPath());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/details/{bugId}")
    public String showBugDetails(@PathVariable Long bugId, Model model) {
        Bug bug = bugService.getBugById(bugId);
        if (bug == null) {
            // Handle bug not found
            return "redirect:/bugs"; // Redirect to the bug list page
        }

        model.addAttribute("bug", bug);
        return "bug-details";
    }

    @PostMapping("/updatebug/{bugId}")
    public String updateBug(@PathVariable Long bugId, @ModelAttribute Bug updatedBug, @RequestParam("file") MultipartFile file) {
        Bug bug = bugService.getBugById(bugId);
        if (bug == null) {
            // Handle bug not found
            return "redirect:/bugs";
        }

        // Update the bug record with the new values
        bug.setTitle(updatedBug.getTitle());
        bug.setDescription(updatedBug.getDescription());
        bug.setPriority(updatedBug.getPriority());
        bug.setProject(updatedBug.getProject());

        // Handle the file upload
        fileStoringHandler(file, bug);

        return "redirect:/bugs/details/" + bugId;
    }

    //Method to handle file storing
    private void fileStoringHandler(@RequestParam("file") MultipartFile file, Bug bug) {
        if (!file.isEmpty()) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String folderName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));

            try {
                // Create the folder if it doesn't exist
                String uploadFolderPath = UPLOAD_DIR + "/" + folderName;
                File folder = new File(uploadFolderPath);
                if (!folder.exists()) {
                    folder.mkdirs();
                }

                try (InputStream inputStream = file.getInputStream()) {
                    String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;
                    Path filePath = Paths.get(uploadFolderPath).resolve(uniqueFileName);
                    Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                    bug.setAttachmentPath(filePath.toString());
                }

            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception as per your requirement
            }
        }

        bugService.saveBug(bug);
    }


    @GetMapping("/update/{bugId}")
    public String viewBugDetails(@PathVariable Long bugId, Model model) {
        Bug bug = bugService.getBugById(bugId);
        if (bug == null) {
            // Handle bug not found
            return "redirect:/bugs";
        }

        List<Project> projects = projectRepository.findAll();
        model.addAttribute("bug", bug);
        model.addAttribute("projects", projects);
        model.addAttribute("hasAttachment", bug.getAttachmentPath() != null);
        return "bug-update"; // Thymeleaf template name for bug details view
    }



}
