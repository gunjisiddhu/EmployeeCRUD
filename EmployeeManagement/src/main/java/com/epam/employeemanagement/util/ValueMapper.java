package com.epam.employeemanagement.util;

import com.epam.employeemanagement.dto.request.EmployeeDTO;
import com.epam.employeemanagement.dto.response.EmployeeDetailsDTO;
import com.epam.employeemanagement.exception.EmployeeException;
import com.epam.employeemanagement.model.Employee;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValueMapper {

    private ValueMapper() {
    }

    public static Employee mapEmployeeDTOToEmployee(EmployeeDTO employeeDTO) {

        if (!employeeDTO.getGender().equalsIgnoreCase("Male") && !employeeDTO.getGender().equalsIgnoreCase("Female")) {
            throw new EmployeeException("Please give valid Gender, Male or Female");
        }
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setEMail(employeeDTO.getEMail());
        employee.setGender(employeeDTO.getGender());
        return employee;
    }


    public static EmployeeDetailsDTO mapEmployeeToEmployeeDetailsDTO(Employee employee) {
        return EmployeeDetailsDTO.builder()
                .id(employee.getId())
                .name(employee.getName())
                .eMail(employee.getEMail())
                .gender(employee.getGender())
                .build();
    }
}
