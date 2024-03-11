package com.techtest.UserDataProcessor;

import com.techtest.UserDataProcessor.Service.CustomerDataService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@SpringBootApplication
public class UserDataProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserDataProcessorApplication.class, args);
	}

	// TODO 01: Create test dataset using chatgpt - DONE
	// TODO 02: Add test signatures for the following:
	// 			- CustomerDataImportService - CSV file was read and parsed
	//			- Customer data imported into the DB
	//			- API returns the correct customer data
	//			- Invalid Customer Ref returns an error
	// TODO 03: Create the CustomerDataImportService interface and define method signatures
	// TODO 04: Create the DB schema
	// TODO 05: Implement the CSV import functionality
	// TODO 06: Make the CustomerDataImportService tests pass
	// TODO 07: Implement the [GET]/customer-data/ API endpoint
	// TODO 08: Test if all tests pass

	@Bean
	CommandLineRunner parseCsvCustomerData(CustomerDataService customerDataService) throws IOException {

		File file = ResourceUtils.getFile("classpath:static/userdata.csv");

		return args -> System.out.println(customerDataService.parseCustomerListFromFile(file));
    }

}
