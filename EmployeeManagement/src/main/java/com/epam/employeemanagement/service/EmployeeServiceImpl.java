package com.epam.employeemanagement.service;

import com.epam.employeemanagement.dto.request.EmployeeDTO;
import com.epam.employeemanagement.dto.response.EmployeeDetailsDTO;
import com.epam.employeemanagement.exception.EmployeeException;
import com.epam.employeemanagement.model.Employee;
import com.epam.employeemanagement.repository.EmployeeRepository;
import com.epam.employeemanagement.util.ValueMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepository employeeRepository;

    public EmployeeDetailsDTO add(EmployeeDTO employeeDTO) {
        Employee employee = ValueMapper.mapEmployeeDTOToEmployee(employeeDTO);
        employeeRepository.save(employee);
        return ValueMapper.mapEmployeeToEmployeeDetailsDTO(employee);
    }

    public void remove(int employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    public EmployeeDetailsDTO get(int employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeeException("Employee with given ID not found"));
        return ValueMapper.mapEmployeeToEmployeeDetailsDTO(employee);
    }

    public EmployeeDetailsDTO update(EmployeeDetailsDTO employeeDetailsDTO) {
        Employee modifiedEmployee = employeeRepository.findById(employeeDetailsDTO.getId()).map(employee -> {
            employee.setName(employeeDetailsDTO.getName());
            employee.setGender(employeeDetailsDTO.getGender());
            employee.setEMail(employeeDetailsDTO.getEMail());
            return employee;
        }).orElseThrow(() -> new EmployeeException("Employee with given ID not found"));
        employeeRepository.save(modifiedEmployee);
        return ValueMapper.mapEmployeeToEmployeeDetailsDTO(modifiedEmployee);
    }
}
