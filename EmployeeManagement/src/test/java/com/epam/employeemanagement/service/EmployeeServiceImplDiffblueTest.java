package com.epam.employeemanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.epam.employeemanagement.dto.request.EmployeeDTO;
import com.epam.employeemanagement.dto.response.EmployeeDetailsDTO;
import com.epam.employeemanagement.exception.EmployeeException;
import com.epam.employeemanagement.model.Employee;
import com.epam.employeemanagement.repository.EmployeeRepository;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EmployeeServiceImpl.class})
@ExtendWith(SpringExtension.class)
class EmployeeServiceImplDiffblueTest {
    @MockBean
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    /**
     * Method under test: {@link EmployeeServiceImpl#add(EmployeeDTO)}
     */
    @Test
    void testAdd() {
        // Arrange
        Employee employee = new Employee();
        employee.setEMail("E Mail");
        employee.setGender("Gender");
        employee.setId(1);
        employee.setName("Name");
        when(employeeRepository.save(Mockito.<Employee>any())).thenReturn(employee);

        // Act
        EmployeeDetailsDTO actualAddResult = employeeServiceImpl.add(new EmployeeDTO("Name", "E Mail", "Male"));

        // Assert
        verify(employeeRepository).save(Mockito.<Employee>any());
        assertEquals("E Mail", actualAddResult.getEMail());
        assertEquals("Male", actualAddResult.getGender());
        assertEquals("Name", actualAddResult.getName());
        assertEquals(0, actualAddResult.getId());
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#add(EmployeeDTO)}
     */
    @Test
    void testAdd2() {
        // Arrange
        when(employeeRepository.save(Mockito.<Employee>any())).thenThrow(new EmployeeException("An error occurred"));

        // Act and Assert
        assertThrows(EmployeeException.class, () -> employeeServiceImpl.add(new EmployeeDTO("Name", "E Mail", "Male")));
        verify(employeeRepository).save(Mockito.<Employee>any());
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#remove(int)}
     */
    @Test
    void testRemove() {
        // Arrange
        doNothing().when(employeeRepository).deleteById(Mockito.<Integer>any());

        // Act
        employeeServiceImpl.remove(1);

        // Assert that nothing has changed
        verify(employeeRepository).deleteById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#remove(int)}
     */
    @Test
    void testRemove2() {
        // Arrange
        doThrow(new EmployeeException("An error occurred")).when(employeeRepository).deleteById(Mockito.<Integer>any());

        // Act and Assert
        assertThrows(EmployeeException.class, () -> employeeServiceImpl.remove(1));
        verify(employeeRepository).deleteById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#get(int)}
     */
    @Test
    void testGet() {
        // Arrange
        Employee employee = new Employee();
        employee.setEMail("E Mail");
        employee.setGender("Gender");
        employee.setId(1);
        employee.setName("Name");
        Optional<Employee> ofResult = Optional.of(employee);
        when(employeeRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        EmployeeDetailsDTO actualGetResult = employeeServiceImpl.get(1);

        // Assert
        verify(employeeRepository).findById(Mockito.<Integer>any());
        assertEquals("E Mail", actualGetResult.getEMail());
        assertEquals("Gender", actualGetResult.getGender());
        assertEquals("Name", actualGetResult.getName());
        assertEquals(1, actualGetResult.getId());
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#get(int)}
     */
    @Test
    void testGet2() {
        // Arrange
        Optional<Employee> emptyResult = Optional.empty();
        when(employeeRepository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(EmployeeException.class, () -> employeeServiceImpl.get(1));
        verify(employeeRepository).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#get(int)}
     */
    @Test
    void testGet3() {
        // Arrange
        when(employeeRepository.findById(Mockito.<Integer>any())).thenThrow(new EmployeeException("An error occurred"));

        // Act and Assert
        assertThrows(EmployeeException.class, () -> employeeServiceImpl.get(1));
        verify(employeeRepository).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#update(EmployeeDetailsDTO)}
     */
    @Test
    void testUpdate() {
        // Arrange
        Employee employee = new Employee();
        employee.setEMail("E Mail");
        employee.setGender("Gender");
        employee.setId(1);
        employee.setName("Name");
        Optional<Employee> ofResult = Optional.of(employee);

        Employee employee2 = new Employee();
        employee2.setEMail("E Mail");
        employee2.setGender("Gender");
        employee2.setId(1);
        employee2.setName("Name");
        when(employeeRepository.save(Mockito.<Employee>any())).thenReturn(employee2);
        when(employeeRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        EmployeeDetailsDTO actualUpdateResult = employeeServiceImpl
                .update(new EmployeeDetailsDTO(1, "Name", "E Mail", "Gender"));

        // Assert
        verify(employeeRepository).findById(Mockito.<Integer>any());
        verify(employeeRepository).save(Mockito.<Employee>any());
        assertEquals("E Mail", actualUpdateResult.getEMail());
        assertEquals("Gender", actualUpdateResult.getGender());
        assertEquals("Name", actualUpdateResult.getName());
        assertEquals(1, actualUpdateResult.getId());
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#update(EmployeeDetailsDTO)}
     */
    @Test
    void testUpdate2() {
        // Arrange
        Employee employee = new Employee();
        employee.setEMail("E Mail");
        employee.setGender("Gender");
        employee.setId(1);
        employee.setName("Name");
        Optional<Employee> ofResult = Optional.of(employee);
        when(employeeRepository.save(Mockito.<Employee>any())).thenThrow(new EmployeeException("An error occurred"));
        when(employeeRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(EmployeeException.class,
                () -> employeeServiceImpl.update(new EmployeeDetailsDTO(1, "Name", "E Mail", "Gender")));
        verify(employeeRepository).findById(Mockito.<Integer>any());
        verify(employeeRepository).save(Mockito.<Employee>any());
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#update(EmployeeDetailsDTO)}
     */
    @Test
    void testUpdate3() {
        // Arrange
        Optional<Employee> emptyResult = Optional.empty();
        when(employeeRepository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(EmployeeException.class,
                () -> employeeServiceImpl.update(new EmployeeDetailsDTO(1, "Name", "E Mail", "Gender")));
        verify(employeeRepository).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#update(EmployeeDetailsDTO)}
     */
    @Test
    void testUpdate4() {
        // Arrange
        Employee employee = new Employee();
        employee.setEMail("E Mail");
        employee.setGender("Gender");
        employee.setId(1);
        employee.setName("Name");
        Optional<Employee> ofResult = Optional.of(employee);

        Employee employee2 = new Employee();
        employee2.setEMail("E Mail");
        employee2.setGender("Gender");
        employee2.setId(1);
        employee2.setName("Name");
        when(employeeRepository.save(Mockito.<Employee>any())).thenReturn(employee2);
        when(employeeRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        EmployeeDetailsDTO employeeDetailsDTO = mock(EmployeeDetailsDTO.class);
        when(employeeDetailsDTO.getId()).thenReturn(1);
        when(employeeDetailsDTO.getEMail()).thenReturn("E Mail");
        when(employeeDetailsDTO.getGender()).thenReturn("Gender");
        when(employeeDetailsDTO.getName()).thenReturn("Name");

        // Act
        EmployeeDetailsDTO actualUpdateResult = employeeServiceImpl.update(employeeDetailsDTO);

        // Assert
        verify(employeeDetailsDTO).getEMail();
        verify(employeeDetailsDTO).getGender();
        verify(employeeDetailsDTO).getId();
        verify(employeeDetailsDTO).getName();
        verify(employeeRepository).findById(Mockito.<Integer>any());
        verify(employeeRepository).save(Mockito.<Employee>any());
        assertEquals("E Mail", actualUpdateResult.getEMail());
        assertEquals("Gender", actualUpdateResult.getGender());
        assertEquals("Name", actualUpdateResult.getName());
        assertEquals(1, actualUpdateResult.getId());
    }
}
