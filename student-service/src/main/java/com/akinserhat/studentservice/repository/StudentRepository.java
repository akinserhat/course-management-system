package com.akinserhat.studentservice.repository;

import com.akinserhat.studentservice.dto.StudentDto;
import com.akinserhat.studentservice.model.Status;
import com.akinserhat.studentservice.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByStatus(Status status);
}
