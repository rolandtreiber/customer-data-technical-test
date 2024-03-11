package com.techtest.UserDataProcessor;

import com.techtest.UserDataProcessor.DAO.CustomerDAO;
import com.techtest.UserDataProcessor.Service.CustomerDataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest

public class CustomerDataServiceTests {

    @Autowired
    CustomerDataService customerDataService;

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

}
