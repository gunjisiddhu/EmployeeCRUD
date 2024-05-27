package com.epam.employeemanagement.controller;


import com.epam.employeemanagement.dto.request.EmployeeDTO;
import com.epam.employeemanagement.dto.response.EmployeeDetailsDTO;
import com.epam.employeemanagement.exception.EmployeeException;
import com.epam.employeemanagement.service.EmployeeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {

    private final EmployeeService employeeService;
    //new class object

    @PostMapping("/")
    ResponseEntity<EmployeeDetailsDTO> addNewEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDetailsDTO employeeDetails = employeeService.add(employeeDTO);
        return new ResponseEntity<>(employeeDetails, HttpStatus.CREATED);
    }

    @PutMapping("/")
    ResponseEntity<EmployeeDetailsDTO> updateEmployee(@Valid @RequestBody EmployeeDetailsDTO employeeDetailsDTO) throws EmployeeException {

        employeeDetailsDTO = employeeService.update(employeeDetailsDTO);
        return new ResponseEntity<>(employeeDetailsDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{employeeId}")
    ResponseEntity<Void> removeEmployee(@Valid @PositiveOrZero @PathVariable int employeeId) {
        employeeService.remove(employeeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{employeeId}")
    ResponseEntity<EmployeeDetailsDTO> fetchEmployee(@Valid @PositiveOrZero @PathVariable int employeeId) {
        EmployeeDetailsDTO employeeDetailsDTO = employeeService.get(employeeId);
        return new ResponseEntity<>(employeeDetailsDTO, HttpStatus.OK);
    }
}
