package com.campus.campus_management_system.controller;

import com.campus.campus_management_system.model.entity.Club;
import com.campus.campus_management_system.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clubs")
public class ClubController {

    @Autowired
    private ClubService clubService;

    @PostMapping
    public Club createClub(@RequestBody Club club) {
        return clubService.createClub(club);
    }

    @GetMapping
    public List<Club> getAllClubs() {
        return clubService.getAllClubs();
    }

    @PostMapping("/{clubId}/add-student/{studentId}")
    public String addStudentToClub(@PathVariable Long clubId, @PathVariable Long studentId) {
        clubService.addStudentToClub(studentId, clubId);
        return "Student added to club successfully!";
    }
}
