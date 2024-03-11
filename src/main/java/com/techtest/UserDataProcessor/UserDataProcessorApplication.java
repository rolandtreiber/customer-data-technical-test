package com.techtest.UserDataProcessor;

import com.techtest.UserDataProcessor.DAO.CustomerDAO;
import com.techtest.UserDataProcessor.Service.CustomerDataService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@SpringBootApplication
public class UserDataProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserDataProcessorApplication.class, args);
	}

	// TODO 01: Create test dataset using chatgpt - DONE
	// TODO 02: Add test signatures for the following:
	// 			- CustomerDataImportService - CSV file was read and parsed - DONE
	//			- Customer data imported into the DB - DONE
	//			- API returns the correct customer data - DONE
	//			- Invalid Customer Ref returns an error - DONE
	// TODO 03: Create the CustomerDataImportService interface and define method signatures - DONE
	// TODO 04: Create the DB schema - DONE
	// TODO 05: Implement the CSV import functionality - DONE
	// TODO 06: Make the CustomerDataImportService tests pass - DONE
	// TODO 07: Implement the [GET]/customer-data/ API endpoint - DONE
	// TODO 08: Test if all tests pass - DONE
	// TODO 09: Add test for saving customer record through the API - DONE
	// TODO 10: Add save endpoint - DONE
	// TODO 11: Implement the test to read all the records from file and save them through the API - DONE

	@Bean
	@Profile("production")
	CommandLineRunner importCsvCustomerData(CustomerDataService customerDataService) throws IOException {
		File file = ResourceUtils.getFile("classpath:static/userdata.csv");

		List<CustomerDAO> parsedCustomerData = customerDataService.parseCustomerListFromFile(file);
		customerDataService.importCustomerData(parsedCustomerData);
		return args -> System.out.println((long) parsedCustomerData.size() +" user records were imported.");
    }

}
