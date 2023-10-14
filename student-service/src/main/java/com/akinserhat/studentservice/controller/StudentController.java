package com.akinserhat.studentservice.controller;

import com.akinserhat.studentservice.dto.CreateStudentRequest;
import com.akinserhat.studentservice.dto.StudentDto;
import com.akinserhat.studentservice.dto.UpdateStudentRequest;
import com.akinserhat.studentservice.model.Status;
import com.akinserhat.studentservice.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<StudentDto>> getAllStudentsByStatus(@PathVariable Status status) {
        return ResponseEntity.ok(studentService.getAllStudentsByStatus(status));
    }

    @PostMapping("/create")
    public ResponseEntity<String> createStudent(@Valid @RequestBody CreateStudentRequest createStudentRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(studentService.createStudent(createStudentRequest));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable Long id, @RequestBody UpdateStudentRequest updateStudentRequest) {
        return ResponseEntity.ok(studentService.updateStudent(id, updateStudentRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.deleteStudent(id));
    }
}
