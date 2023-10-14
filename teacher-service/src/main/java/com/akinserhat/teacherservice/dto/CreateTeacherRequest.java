package com.akinserhat.teacherservice.dto;

import com.akinserhat.teacherservice.model.Branch;
import com.akinserhat.teacherservice.model.Status;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTeacherRequest {
    @NotEmpty
    private String name;
    private String address;
    @Size(min = 10, max = 10)
    private String phoneNumber;
    private String universityName;
    private Status status;
    private Branch branch;
}
