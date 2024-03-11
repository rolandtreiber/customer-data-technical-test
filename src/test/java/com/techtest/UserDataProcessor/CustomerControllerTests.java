package com.techtest.UserDataProcessor;

import com.techtest.UserDataProcessor.Controller.CustomerController;
import com.techtest.UserDataProcessor.DAO.CustomerDAO;
import com.techtest.UserDataProcessor.Exception.UserNotFoundException;
import com.techtest.UserDataProcessor.Repository.CustomerRepository;
import com.techtest.UserDataProcessor.Service.CustomerDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerTests {

    private CustomerController controller;
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    CustomerDataService customerDataService;

    @BeforeEach
    public void setUp() throws Exception {
        controller = new CustomerController(customerDataService);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void customerDetailsReturned() {
        CustomerDAO customerDAO = new CustomerDAO(
                "14","Jane Smith","456 Elm St","","Oakville","Anycounty","USA","67890"
        );
        customerDataService.save(customerDAO);
        CustomerDAO returnPayload = controller.getCustomerDataByCustomerRef("14");
        assertEquals(returnPayload.getCustomerRef(), "14");
        assertEquals(returnPayload.getCustomerName(), "Jane Smith");
        assertEquals(returnPayload.getAddressLine1(), "456 Elm St");
        assertEquals(returnPayload.getAddressLine2(), "");
        assertEquals(returnPayload.getTown(), "Oakville");
        assertEquals(returnPayload.getCounty(), "Anycounty");
        assertEquals(returnPayload.getCountry(), "USA");
        assertEquals(returnPayload.getPostcode(), "67890");

    }

    @Test
    public void customerNotFoundThrowsAppropriateException() {
        assertThrows(UserNotFoundException.class, () -> controller.getCustomerDataByCustomerRef("99"));
    }

    @Test
    public void customerSavedThroughApi() {
        CustomerDAO customerDAO = new CustomerDAO(
                "14","Jane Smith","456 Elm St","","Oakville","Anycounty","USA","67890"
        );

        URI newCustomerLocation = restTemplate.postForLocation("/customers", customerDAO);
        assertTrue(newCustomerLocation.getPath().contains("/customers/14"));

        ResponseEntity<CustomerDAO> responseEntity
                = restTemplate.getForEntity("/customers/14", CustomerDAO.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getCustomerName()).isEqualTo("Jane Smith");
    }

    @Test
    public void customersImportedFromFileSavedThroughApi() throws IOException {
        File file = ResourceUtils.getFile("src/test/resources/testuserdata.csv");

        List<CustomerDAO> customersData = customerDataService.parseCustomerListFromFile(file);

        for (CustomerDAO customerDAO : customersData) {
            restTemplate.postForLocation("/customers", customerDAO);
            ResponseEntity<CustomerDAO> responseEntity
                    = restTemplate.getForEntity("/customers/"+customerDAO.getCustomerRef(), CustomerDAO.class);
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(responseEntity.getBody().getCustomerName()).isEqualTo(customerDAO.getCustomerName());
            assertThat(responseEntity.getBody().getCustomerRef()).isEqualTo(customerDAO.getCustomerRef());
            assertThat(responseEntity.getBody().getAddressLine1()).isEqualTo(customerDAO.getAddressLine1());
            assertThat(responseEntity.getBody().getAddressLine2()).isEqualTo(customerDAO.getAddressLine2());
            assertThat(responseEntity.getBody().getTown()).isEqualTo(customerDAO.getTown());
            assertThat(responseEntity.getBody().getCounty()).isEqualTo(customerDAO.getCounty());
            assertThat(responseEntity.getBody().getCountry()).isEqualTo(customerDAO.getCountry());
            assertThat(responseEntity.getBody().getPostcode()).isEqualTo(customerDAO.getPostcode());
        }

    }

}
