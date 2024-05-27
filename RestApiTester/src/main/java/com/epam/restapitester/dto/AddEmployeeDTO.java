package com.epam.restapitester.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddEmployeeDTO {
    private String name;
    private String eMail;
    private String gender;
}