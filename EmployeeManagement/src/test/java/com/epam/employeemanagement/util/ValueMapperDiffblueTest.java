package com.epam.employeemanagement.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.epam.employeemanagement.dto.request.EmployeeDTO;
import com.epam.employeemanagement.dto.response.EmployeeDetailsDTO;
import com.epam.employeemanagement.exception.EmployeeException;
import com.epam.employeemanagement.model.Employee;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ValueMapperDiffblueTest {
    /**
     * Method under test: {@link ValueMapper#mapEmployeeDTOToEmployee(EmployeeDTO)}
     */
    @Test
    void testMapEmployeeDTOToEmployee() {
        // Arrange, Act and Assert
        assertThrows(EmployeeException.class,
                () -> ValueMapper.mapEmployeeDTOToEmployee(new EmployeeDTO("Name", "E Mail", "Gender")));
    }

    /**
     * Method under test: {@link ValueMapper#mapEmployeeDTOToEmployee(EmployeeDTO)}
     */
    @Test
    void testMapEmployeeDTOToEmployee2() {
        // Arrange and Act
        Employee actualMapEmployeeDTOToEmployeeResult = ValueMapper
                .mapEmployeeDTOToEmployee(new EmployeeDTO("Name", "E Mail", "Male"));

        // Assert
        assertEquals("E Mail", actualMapEmployeeDTOToEmployeeResult.getEMail());
        assertEquals("Male", actualMapEmployeeDTOToEmployeeResult.getGender());
        assertEquals("Name", actualMapEmployeeDTOToEmployeeResult.getName());
    }

    /**
     * Method under test: {@link ValueMapper#mapEmployeeDTOToEmployee(EmployeeDTO)}
     */
    @Test
    void testMapEmployeeDTOToEmployee3() {
        // Arrange and Act
        Employee actualMapEmployeeDTOToEmployeeResult = ValueMapper
                .mapEmployeeDTOToEmployee(new EmployeeDTO("Name", "E Mail", "Female"));

        // Assert
        assertEquals("E Mail", actualMapEmployeeDTOToEmployeeResult.getEMail());
        assertEquals("Female", actualMapEmployeeDTOToEmployeeResult.getGender());
        assertEquals("Name", actualMapEmployeeDTOToEmployeeResult.getName());
    }

    /**
     * Method under test: {@link ValueMapper#mapEmployeeDTOToEmployee(EmployeeDTO)}
     */
    @Test
    void testMapEmployeeDTOToEmployee4() {
        // Arrange
        EmployeeDTO employeeDTO = mock(EmployeeDTO.class);
        when(employeeDTO.getEMail()).thenReturn("E Mail");
        when(employeeDTO.getName()).thenReturn("Name");
        when(employeeDTO.getGender()).thenReturn("Male");

        // Act
        Employee actualMapEmployeeDTOToEmployeeResult = ValueMapper.mapEmployeeDTOToEmployee(employeeDTO);

        // Assert
        verify(employeeDTO).getEMail();
        verify(employeeDTO, atLeast(1)).getGender();
        verify(employeeDTO).getName();
        assertEquals("E Mail", actualMapEmployeeDTOToEmployeeResult.getEMail());
        assertEquals("Male", actualMapEmployeeDTOToEmployeeResult.getGender());
        assertEquals("Name", actualMapEmployeeDTOToEmployeeResult.getName());
    }

    /**
     * Method under test: {@link ValueMapper#mapEmployeeDTOToEmployee(EmployeeDTO)}
     */
    @Test
    void testMapEmployeeDTOToEmployee5() {
        // Arrange
        EmployeeDTO employeeDTO = mock(EmployeeDTO.class);
        when(employeeDTO.getName()).thenThrow(new EmployeeException("An error occurred"));
        when(employeeDTO.getGender()).thenReturn("Male");

        // Act and Assert
        assertThrows(EmployeeException.class, () -> ValueMapper.mapEmployeeDTOToEmployee(employeeDTO));
        verify(employeeDTO).getGender();
        verify(employeeDTO).getName();
    }

    /**
     * Method under test:
     * {@link ValueMapper#mapEmployeeToEmployeeDetailsDTO(Employee)}
     */
    @Test
    void testMapEmployeeToEmployeeDetailsDTO() {
        // Arrange
        Employee employee = new Employee();
        employee.setEMail("E Mail");
        employee.setGender("Gender");
        employee.setId(1);
        employee.setName("Name");

        // Act
        EmployeeDetailsDTO actualMapEmployeeToEmployeeDetailsDTOResult = ValueMapper
                .mapEmployeeToEmployeeDetailsDTO(employee);

        // Assert
        assertEquals("E Mail", actualMapEmployeeToEmployeeDetailsDTOResult.getEMail());
        assertEquals("Gender", actualMapEmployeeToEmployeeDetailsDTOResult.getGender());
        assertEquals("Name", actualMapEmployeeToEmployeeDetailsDTOResult.getName());
        assertEquals(1, actualMapEmployeeToEmployeeDetailsDTOResult.getId());
    }

    /**
     * Method under test:
     * {@link ValueMapper#mapEmployeeToEmployeeDetailsDTO(Employee)}
     */
    @Test
    void testMapEmployeeToEmployeeDetailsDTO2() {
        // Arrange
        Employee employee = mock(Employee.class);
        when(employee.getId()).thenReturn(1);
        when(employee.getEMail()).thenReturn("E Mail");
        when(employee.getGender()).thenReturn("Gender");
        when(employee.getName()).thenReturn("Name");
        doNothing().when(employee).setEMail(Mockito.<String>any());
        doNothing().when(employee).setGender(Mockito.<String>any());
        doNothing().when(employee).setId(anyInt());
        doNothing().when(employee).setName(Mockito.<String>any());
        employee.setEMail("E Mail");
        employee.setGender("Gender");
        employee.setId(1);
        employee.setName("Name");

        // Act
        EmployeeDetailsDTO actualMapEmployeeToEmployeeDetailsDTOResult = ValueMapper
                .mapEmployeeToEmployeeDetailsDTO(employee);

        // Assert
        verify(employee).getEMail();
        verify(employee).getGender();
        verify(employee).getId();
        verify(employee).getName();
        verify(employee).setEMail(Mockito.<String>any());
        verify(employee).setGender(Mockito.<String>any());
        verify(employee).setId(anyInt());
        verify(employee).setName(Mockito.<String>any());
        assertEquals("E Mail", actualMapEmployeeToEmployeeDetailsDTOResult.getEMail());
        assertEquals("Gender", actualMapEmployeeToEmployeeDetailsDTOResult.getGender());
        assertEquals("Name", actualMapEmployeeToEmployeeDetailsDTOResult.getName());
        assertEquals(1, actualMapEmployeeToEmployeeDetailsDTOResult.getId());
    }
}
