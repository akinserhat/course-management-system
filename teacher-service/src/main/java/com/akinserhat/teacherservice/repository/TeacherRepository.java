package com.akinserhat.teacherservice.repository;

import com.akinserhat.teacherservice.model.Branch;
import com.akinserhat.teacherservice.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    List<Teacher> getTeacherByBranch(Branch branch);
}
