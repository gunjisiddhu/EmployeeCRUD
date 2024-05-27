package com.epam.employeemanagement.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Builder
@ToString
@AllArgsConstructor
@Setter
@Getter
public class EmployeeDTO {
    @NotBlank(message = "Give Name")
    private String name;
    @NotBlank(message = "Give E-Mail")
    @Email(message = "Give valid E-Mail")
    private String eMail;
    @NotBlank(message = "Give Gender")
    private String gender;
}
