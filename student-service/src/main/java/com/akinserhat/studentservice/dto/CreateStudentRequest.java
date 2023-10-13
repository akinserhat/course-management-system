package com.akinserhat.studentservice.dto;

import com.akinserhat.studentservice.model.Status;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateStudentRequest {

    @NotEmpty
    private String name;
    private String address;
    @Size(min = 10, max = 10, message = "Lütfen geçerli bir telefon numarası giriniz.")
    private String phoneNumber;
    @Size(min = 10, max = 10, message = "Lütfen geçerli bir telefon numarası giriniz.")
    private String secondaryPhoneNumber;
    private String parentName;
    private Status status;
}
