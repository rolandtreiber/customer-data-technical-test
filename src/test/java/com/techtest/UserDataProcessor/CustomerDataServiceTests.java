package com.techtest.UserDataProcessor;

import com.techtest.UserDataProcessor.DAO.CustomerDAO;
import com.techtest.UserDataProcessor.Entity.Customer;
import com.techtest.UserDataProcessor.Repository.CustomerRepository;
import com.techtest.UserDataProcessor.Service.CustomerDataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest

public class CustomerDataServiceTests {

    @Autowired
    CustomerDataService customerDataService;

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void customerDataParsedFromCSV() throws IOException {
        File file = ResourceUtils.getFile("src/test/resources/testuserdata.csv");

        CustomerDAO emilyBrown = customerDataService.parseCustomerListFromFile(file).get(3);
        assertEquals(emilyBrown.getCustomerName(), "Emily Brown");
        assertEquals(emilyBrown.getAddressLine1(), "101 Pine St");
        assertEquals(emilyBrown.getTown(), "Hillside");
        assertEquals(emilyBrown.getCounty(), "Anystate");
        assertEquals(emilyBrown.getCountry(), "USA");
        assertEquals(emilyBrown.getPostcode(), "78901");
    }

    @Test
    void customerDataPersistedInDB() {
        CustomerDAO customerDAO = new CustomerDAO(
                "14","Jane Smith","456 Elm St","","Oakville","Anycounty","USA","67890"
        );
        customerDataService.save(customerDAO);
        Customer customer = customerRepository.findByCustomerRef("14");
        assertEquals(customer.getCustomerRef(), "14");
        assertEquals(customer.getName(), "Jane Smith");
        assertEquals(customer.getAddressLine1(), "456 Elm St");
        assertEquals(customer.getAddressLine2(), "");
        assertEquals(customer.getTown(), "Oakville");
        assertEquals(customer.getCounty(), "Anycounty");
        assertEquals(customer.getCountry(), "USA");
        assertEquals(customer.getPostCode(), "67890");
    }

    @Test
    void customerDataImported() throws IOException {
        File file = ResourceUtils.getFile("src/test/resources/testuserdata.csv");

        List<CustomerDAO> customersData = customerDataService.parseCustomerListFromFile(file);

        customerDataService.importCustomerData(customersData);
        Customer customer = customerRepository.findById(4);

        assertEquals(customer.getCustomerRef(), "4");
        assertEquals(customer.getName(), "Emily Brown");
        assertEquals(customer.getAddressLine1(), "101 Pine St");
        assertEquals(customer.getAddressLine2(), "");
        assertEquals(customer.getTown(), "Hillside");
        assertEquals(customer.getCounty(), "Anystate");
        assertEquals(customer.getCountry(), "USA");
        assertEquals(customer.getPostCode(), "78901");
    }

}
