package com.techtest.UserDataProcessor;

import com.techtest.UserDataProcessor.Service.CustomerDataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserDataProcessorApplicationTests {

	@Autowired
	CustomerDataService customerDataService;

	@Test
	void customerDataParsedFromCSV() throws IOException {
		File file = ResourceUtils.getFile("src/test/resources/testuserdata.csv");

		assertEquals(customerDataService.parseCustomerListFromFile(file).get(3).getCustomerName(), "Emily Brown");

	}

}
