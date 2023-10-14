package com.akinserhat.teacherservice.dto;

import com.akinserhat.teacherservice.model.Branch;
import com.akinserhat.teacherservice.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherDto {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private String universityName;
    private Status status;
    private Branch branch;
}
