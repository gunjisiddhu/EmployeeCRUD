package com.epam.employeemanagement.controller;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.epam.employeemanagement.dto.request.EmployeeDTO;
import com.epam.employeemanagement.dto.response.EmployeeDetailsDTO;
import com.epam.employeemanagement.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {EmployeeController.class})
@ExtendWith(SpringExtension.class)
class EmployeeControllerDiffblueTest {
  @Autowired
  private EmployeeController employeeController;

  @MockBean
  private EmployeeService employeeService;

  /**
   * Method under test: {@link EmployeeController#addNewEmployee(EmployeeDTO)}
   */
  @Test
  void testAddNewEmployee() throws Exception {
    // Arrange
    MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/employee/")
            .contentType(MediaType.APPLICATION_JSON);

    ObjectMapper objectMapper = new ObjectMapper();
    EmployeeDTO buildResult = EmployeeDTO.builder().eMail("E Mail").gender("Gender").name("Name").build();
    MockHttpServletRequestBuilder requestBuilder = contentTypeResult
            .content(objectMapper.writeValueAsString(buildResult));

    // Act
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(employeeController)
            .build()
            .perform(requestBuilder);

    // Assert
    actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
  }

  /**
   * Method under test: {@link EmployeeController#fetchEmployee(int)}
   */
  @Test
  void testFetchEmployee() throws Exception {
    // Arrange
    EmployeeDetailsDTO buildResult = EmployeeDetailsDTO.builder()
            .eMail("E Mail")
            .gender("Gender")
            .id(1)
            .name("Name")
            .build();
    when(employeeService.get(anyInt())).thenReturn(buildResult);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employee/{employeeId}", 1);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(employeeController)
            .build()
            .perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andExpect(MockMvcResultMatchers.content()
                    .string("{\"id\":1,\"name\":\"Name\",\"gender\":\"Gender\",\"email\":\"E Mail\"}"));
  }

  /**
   * Method under test: {@link EmployeeController#removeEmployee(int)}
   */
  @Test
  void testRemoveEmployee() throws Exception {
    // Arrange
    doNothing().when(employeeService).remove(anyInt());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/employee/{employeeId}", 1);

    // Act
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(employeeController)
            .build()
            .perform(requestBuilder);

    // Assert
    actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
  }

  /**
   * Method under test:
   * {@link EmployeeController#updateEmployee(EmployeeDetailsDTO)}
   */
  @Test
  void testUpdateEmployee() throws Exception {
    // Arrange
    MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.put("/employee/")
            .contentType(MediaType.APPLICATION_JSON);

    ObjectMapper objectMapper = new ObjectMapper();
    EmployeeDetailsDTO buildResult = EmployeeDetailsDTO.builder()
            .eMail("E Mail")
            .gender("Gender")
            .id(1)
            .name("Name")
            .build();
    MockHttpServletRequestBuilder requestBuilder = contentTypeResult
            .content(objectMapper.writeValueAsString(buildResult));

    // Act
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(employeeController)
            .build()
            .perform(requestBuilder);

    // Assert
    actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
  }
}
