package com.epam.restapitester.api;

import com.epam.restapitester.dto.AddEmployeeDTO;
import com.epam.restapitester.dto.EmployeeDetailsDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RestClientImpl {
    private static final String BASE_URL = "http://localhost:8010/employee/";
    private final RestClient restClient;

    @GetMapping("/testGetEmployeeRestClient")
    public void getEmployeeData() {
        int employeeId = 1;

        String fullUrl = BASE_URL + employeeId;

        ResponseEntity<EmployeeDetailsDTO> response = restClient.get()
                .uri(fullUrl)
                .retrieve()
                .toEntity(EmployeeDetailsDTO.class);


        log.info(response.toString());
    }

    @PostMapping("/testAddEmployeeRestClient")
    public void addEmployeeData() {
        AddEmployeeDTO addEmployeeDTO = AddEmployeeDTO.builder()
                .name("sasi")
                .eMail("sasi@gmail.com")
                .gender("Female")
                .build();

        ResponseEntity<EmployeeDetailsDTO> responseEntity = restClient.post()
                .uri(BASE_URL)
                .contentType(APPLICATION_JSON)
                .body(addEmployeeDTO)
                .retrieve()
                .toEntity(EmployeeDetailsDTO.class);

        log.info(responseEntity.toString());
    }


    @DeleteMapping("/testDeleteEmployeeRestClient")
    public void removeEmployee() {
        String url = BASE_URL + 4;
        restClient.delete().uri(url);

    }


    @PutMapping("/testPutMappingRestClient")
    public void putMapping() {
        EmployeeDetailsDTO employeeDetailsDTO = EmployeeDetailsDTO.builder().id(1).name("ammu").eMail("ammu@gmail.com").gender("male").build();
        ResponseEntity<EmployeeDetailsDTO> responseEntity = restClient.put()
                .uri(BASE_URL)
                .contentType(APPLICATION_JSON)
                .body(employeeDetailsDTO)
                .retrieve()
                .toEntity(EmployeeDetailsDTO.class);

        log.info(responseEntity.toString());
    }


    void printPattern(){
        int n = 5;
        for(int i=1;i<=n/2;i++){
            for(int j=1;j<=n/2;j++)
                System.out.print(" ");
            System.out.println("*");
        }
        for(int i=1;i<=n;i++)
            System.out.print("*");
        System.out.println();
        for(int i=1;i<=n/2;i++){
            for(int j=1;j<=n/2;j++)
                System.out.print(" ");
            System.out.println("*");
        }
    }

}
