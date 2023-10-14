package com.akinserhat.teacherservice.controller;

import com.akinserhat.teacherservice.dto.CreateTeacherRequest;
import com.akinserhat.teacherservice.dto.TeacherDto;
import com.akinserhat.teacherservice.dto.UpdateTeacherRequest;
import com.akinserhat.teacherservice.model.Branch;
import com.akinserhat.teacherservice.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping
    public ResponseEntity<List<TeacherDto>> getAllTeachers() {
        return ResponseEntity.ok(teacherService.getAllTeachers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> getTeacherById(@PathVariable Long id) {
        return ResponseEntity.ok(teacherService.getTeacherById(id));
    }

    @GetMapping("/branch/{branch}")
    public ResponseEntity<List<TeacherDto>> getTeachersByBranch(@PathVariable Branch branch) {
        return ResponseEntity.ok(teacherService.getTeachersByBranch(branch));
    }

    @PostMapping("/create")
    public ResponseEntity<String> createTeacher(@RequestBody CreateTeacherRequest createTeacherRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(teacherService.createTeacher(createTeacherRequest));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateTeacher(@PathVariable Long id, @RequestBody UpdateTeacherRequest updateTeacherRequest) {
        return ResponseEntity.ok(teacherService.updateTeacher(id, updateTeacherRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable Long id) {
        return ResponseEntity.ok(teacherService.deleteTeacher(id));
    }
}
