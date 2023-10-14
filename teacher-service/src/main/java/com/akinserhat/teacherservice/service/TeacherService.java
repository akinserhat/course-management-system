package com.akinserhat.teacherservice.service;

import com.akinserhat.teacherservice.dto.CreateTeacherRequest;
import com.akinserhat.teacherservice.dto.TeacherDto;
import com.akinserhat.teacherservice.dto.UpdateTeacherRequest;
import com.akinserhat.teacherservice.exception.TeacherNotFoundException;
import com.akinserhat.teacherservice.model.Branch;
import com.akinserhat.teacherservice.model.Status;
import com.akinserhat.teacherservice.model.Teacher;
import com.akinserhat.teacherservice.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public List<TeacherDto> getAllTeachers() {
        return teacherRepository.findAll()
                .stream()
                .map(teacher -> TeacherDto.builder()
                        .id(teacher.getId())
                        .name(teacher.getName())
                        .address(teacher.getAddress())
                        .phoneNumber(teacher.getPhoneNumber())
                        .universityName(teacher.getUniversityName())
                        .status(teacher.getStatus())
                        .branch(teacher.getBranch())
                        .build())
                .collect(Collectors.toList());
    }

    public TeacherDto getTeacherById(Long id) {
        return teacherRepository.findById(id)
                .map(teacher -> TeacherDto.builder()
                        .id(teacher.getId())
                        .name(teacher.getName())
                        .address(teacher.getAddress())
                        .phoneNumber(teacher.getPhoneNumber())
                        .universityName(teacher.getUniversityName())
                        .status(teacher.getStatus())
                        .branch(teacher.getBranch())
                        .build())
                .orElseThrow(() -> new TeacherNotFoundException("Eğitmen id bulunamadı: " + id));
    }

    public List<TeacherDto> getTeachersByBranch(Branch branch) {
        return teacherRepository.getTeacherByBranch(branch)
                .stream()
                .map(teacher -> TeacherDto.builder()
                        .id(teacher.getId())
                        .name(teacher.getName())
                        .address(teacher.getAddress())
                        .phoneNumber(teacher.getPhoneNumber())
                        .universityName(teacher.getUniversityName())
                        .status(teacher.getStatus())
                        .branch(teacher.getBranch())
                        .build())
                .collect(Collectors.toList());
    }

    public String createTeacher(CreateTeacherRequest createTeacherRequest) {
        Teacher teacher = new Teacher();
        teacher.setName(createTeacherRequest.getName());
        teacher.setAddress(createTeacherRequest.getAddress());
        teacher.setPhoneNumber(createTeacherRequest.getPhoneNumber());
        teacher.setUniversityName(createTeacherRequest.getUniversityName());
        teacher.setStatus(Status.ACTIVE);
        teacher.setBranch(createTeacherRequest.getBranch());
        teacherRepository.save(teacher);
        return "Eğitmen başarıyla oluşturuldu.";
    }

    public String updateTeacher(Long id, UpdateTeacherRequest updateTeacherRequest) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
        if (optionalTeacher.isPresent()) {
            Teacher teacher = optionalTeacher.get();
            teacher.setName(updateTeacherRequest.getName());
            teacher.setAddress(updateTeacherRequest.getAddress());
            teacher.setPhoneNumber(updateTeacherRequest.getPhoneNumber());
            teacher.setUniversityName(updateTeacherRequest.getUniversityName());
            teacher.setStatus(updateTeacherRequest.getStatus());
            teacher.setBranch(updateTeacherRequest.getBranch());
            teacherRepository.save(teacher);
            return "Eğitmen başarıyla güncellendi.";
        } else {
            throw new TeacherNotFoundException("Eğitmen bulunamadı! Lütfen tekrar deneyiniz.");
        }
    }

    public String deleteTeacher(Long id) {
        if (teacherRepository.existsById(id)) {
            teacherRepository.deleteById(id);
            return "Eğitmen başarıyla silindi.";
        } else {
            return "Silinecek eğitmen id bulunamadı: " + id;
        }
    }
}
