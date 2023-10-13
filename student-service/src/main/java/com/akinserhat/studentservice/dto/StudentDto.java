package com.akinserhat.studentservice.dto;

import com.akinserhat.studentservice.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDto {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private String secondaryPhoneNumber;
    private String parentName;
    private Status status;
}
