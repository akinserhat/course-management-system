package com.akinserhat.studentservice.service;

import com.akinserhat.studentservice.dto.CreateStudentRequest;
import com.akinserhat.studentservice.dto.StudentDto;
import com.akinserhat.studentservice.dto.UpdateStudentRequest;
import com.akinserhat.studentservice.exception.StudentNotFoundException;
import com.akinserhat.studentservice.model.Status;
import com.akinserhat.studentservice.model.Student;
import com.akinserhat.studentservice.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentService {

    private final StudentRepository studentRepository;

    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(student -> StudentDto.builder()
                        .id(student.getId())
                        .name(student.getName())
                        .address(student.getAddress())
                        .parentName(student.getParentName())
                        .phoneNumber(student.getPhoneNumber())
                        .secondaryPhoneNumber(student.getSecondaryPhoneNumber())
                        .status(student.getStatus())
                        .build())
                .collect(Collectors.toList());
    }

    public StudentDto getStudentById(Long id) {
        return studentRepository.findById(id)
                .map(student -> StudentDto.builder()
                        .id(student.getId())
                        .name(student.getName())
                        .address(student.getAddress())
                        .parentName(student.getParentName())
                        .phoneNumber(student.getPhoneNumber())
                        .secondaryPhoneNumber(student.getSecondaryPhoneNumber())
                        .status(student.getStatus())
                        .build())
                .orElseThrow(() -> new StudentNotFoundException("Öğrenci id bulunamadı: " + id));
    }

    public List<StudentDto> getAllStudentsByStatus(Status status) {
        List<Student> students = studentRepository.findByStatus(status);
        return students.stream()
                .map(student -> StudentDto.builder()
                        .id(student.getId())
                        .name(student.getName())
                        .address(student.getAddress())
                        .parentName(student.getParentName())
                        .phoneNumber(student.getPhoneNumber())
                        .secondaryPhoneNumber(student.getSecondaryPhoneNumber())
                        .status(student.getStatus())
                        .build())
                .collect(Collectors.toList());
    }

    public String createStudent(CreateStudentRequest createStudentRequest) {
        Student student = new Student();
        student.setName(createStudentRequest.getName());
        student.setAddress(createStudentRequest.getAddress());
        student.setPhoneNumber(createStudentRequest.getPhoneNumber());
        student.setSecondaryPhoneNumber(createStudentRequest.getSecondaryPhoneNumber());
        student.setParentName(createStudentRequest.getParentName());
        student.setStatus(Status.ACTIVE);
        studentRepository.save(student);
        return "Öğrenci başarıyla oluşturuldu.";
    }

    public String updateStudent(Long id, UpdateStudentRequest updateStudentRequest) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            student.setName(updateStudentRequest.getName());
            student.setAddress(updateStudentRequest.getAddress());
            student.setPhoneNumber(updateStudentRequest.getPhoneNumber());
            student.setSecondaryPhoneNumber(updateStudentRequest.getSecondaryPhoneNumber());
            student.setParentName(updateStudentRequest.getParentName());
            student.setStatus(updateStudentRequest.getStatus());
            studentRepository.save(student);
            return "Öğrenci başarıyla güncellendi.";
        } else {
            throw new StudentNotFoundException("Öğrenci bulunamadı! Lütfen tekrar deneyiniz.");
        }
    }

    public String deleteStudent(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return "Öğrenci başarıyla silindi.";
        } else {
            return "Silinecek öğrenci id bulunamadı: " + id;
        }

    }
}
