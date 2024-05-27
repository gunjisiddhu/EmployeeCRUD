package com.epam.employeemanagement.service;

import com.epam.employeemanagement.dto.request.EmployeeDTO;
import com.epam.employeemanagement.dto.response.EmployeeDetailsDTO;

public interface EmployeeService {
    EmployeeDetailsDTO add(EmployeeDTO employeeDTO);

    void remove(int employeeId);

    EmployeeDetailsDTO get(int employeeId);

    EmployeeDetailsDTO update(EmployeeDetailsDTO employeeDetailsDTO);
}
