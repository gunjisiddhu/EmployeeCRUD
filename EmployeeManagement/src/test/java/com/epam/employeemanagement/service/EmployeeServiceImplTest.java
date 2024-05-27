//package com.epam.employeemanagement.service;
//
//import com.epam.employeemanagement.aop.LoggingAspect;
//import com.epam.employeemanagement.dto.request.EmployeeDTO;
//import com.epam.employeemanagement.dto.response.EmployeeDetailsDTO;
//import com.epam.employeemanagement.exception.EmployeeException;
//import com.epam.employeemanagement.model.Employee;
//import com.epam.employeemanagement.repository.EmployeeRepository;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
//import org.springframework.boot.test.mock.mockito.SpyBean;
//import org.springframework.context.annotation.Import;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//
//
//@ExtendWith(MockitoExtension.class)
//class EmployeeServiceImplTest {
//
//    @InjectMocks
//    EmployeeServiceImpl employeeServiceImpl;
//
//    @Mock
//    EmployeeRepository employeeRepository;
//
//    @SpyBean
//    LoggingAspect loggingAspect;
//
//    static Employee employee;
//
//    static EmployeeDTO employeeDTO;
//
//    static EmployeeDetailsDTO employeeDetailsDTO;
//
//
//
//    @BeforeAll
//    static void initValues() {
//        employee = new Employee();
//        employee.setId(1);
//        employee.setName("siddhu");
//        employee.setEMail("test@Testing.com");
//        employee.setGender("male");
//
//        employeeDTO = EmployeeDTO.builder()
//                .name("tester")
//                .eMail("test@testing.com")
//                .gender("male")
//                .build();
//
//        employeeDetailsDTO = EmployeeDetailsDTO.builder()
//                .id(1)
//                .name("tester")
//                .eMail("test@testing.com")
//                .gender("male")
//                .build();
//
//    }
//
//
//    @Test
//    void testAddNewEmployeeCase1(){
//        employeeDTO.setGender("female");
//        Mockito.when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
//        assertNotNull(employeeServiceImpl.add(employeeDTO));
//        Mockito.verify(employeeRepository).save(any(Employee.class));
//    }
//
//    @Test
//    void testAddNewEmployeeCase2(){
//        employeeDTO.setGender("male");
//        Mockito.when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
//        assertNotNull(employeeServiceImpl.add(employeeDTO));
//        Mockito.verify(employeeRepository).save(any(Employee.class));
//    }
//
//    @Test
//    void testAddNewEmployeeGenderMismatchCase(){
//        employeeDTO.setGender("XYZ");
//        assertThrows(EmployeeException.class, () -> employeeServiceImpl.add(employeeDTO));
//    }
//
//
//    @Test
//    void testUpdateEmployee() throws EmployeeException {
//        Mockito.when(employeeRepository.findById(employeeDetailsDTO.getId())).thenReturn(Optional.ofNullable(employee));
//
//
//        Mockito.when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
//        assertNotNull(employeeServiceImpl.update(employeeDetailsDTO));
//        Mockito.verify(employeeRepository).save(any(Employee.class));
//        Mockito.verify(employeeRepository).findById(employeeDetailsDTO.getId());
//
//    }
//
//    @Test
//    void testUpdateEmployeeWhenNotExists() {
//        Mockito.when(employeeRepository.findById(employeeDetailsDTO.getId())).thenReturn(Optional.empty());
//        assertThrows(EmployeeException.class,()-> employeeServiceImpl.update(employeeDetailsDTO));
//        Mockito.verify(employeeRepository).findById(employeeDetailsDTO.getId());
//    }
//
//    @Test
//    void testFetchEmployee(){
//        Mockito.when(employeeRepository.findById(employeeDetailsDTO.getId())).thenReturn(Optional.ofNullable(employee));
//        assertNotNull(employeeServiceImpl.get(employeeDetailsDTO.getId()));
//
//        Mockito.verify(employeeRepository).findById(employeeDetailsDTO.getId());
//    }
//
//    @Test
//    void testFetchEmployeeNotExists(){
//
//        Mockito.when(employeeRepository.findById(employeeDetailsDTO.getId())).thenReturn(Optional.empty());
//        assertThrows(EmployeeException.class,()-> employeeServiceImpl.get(employeeDetailsDTO.getId()));
//
//        Mockito.verify(employeeRepository).findById(employeeDetailsDTO.getId());
//    }
//
//    @Test
//    void testDeleteAssociateById(){
//        Mockito.doNothing().when(employeeRepository).deleteById(1);
//        assertDoesNotThrow(() -> employeeServiceImpl.remove(1));
//        Mockito.verify(employeeRepository).deleteById(1);
//    }
//}
