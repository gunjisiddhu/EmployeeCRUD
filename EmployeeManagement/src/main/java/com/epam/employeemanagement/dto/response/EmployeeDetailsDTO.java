package com.epam.employeemanagement.dto.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;


@Builder
@AllArgsConstructor
@Setter
@Getter
@ToString
public class EmployeeDetailsDTO {
    @PositiveOrZero
    private int id;
    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String eMail;
    @NotBlank
    private String gender;
}
