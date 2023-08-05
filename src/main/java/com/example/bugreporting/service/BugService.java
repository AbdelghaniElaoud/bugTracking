package com.example.bugreporting.service;

import com.example.bugreporting.model.Bug;
import com.example.bugreporting.repository.BugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BugService {
    private final BugRepository bugRepository;

    @Autowired
    public BugService(BugRepository bugRepository) {
        this.bugRepository = bugRepository;
    }

    public List<Bug> getAllBugs() {
        return bugRepository.findAll();
    }

    public void saveBug(Bug bug) {
        bugRepository.save(bug);
    }

    public Bug getBugById(Long bugId) {
        return bugRepository.findById(bugId).get();
    }

    // Implement methods to interact with the repository (e.g., save, find, update, delete bugs)
}
