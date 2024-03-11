package com.techtest.UserDataProcessor.Service;

import com.techtest.UserDataProcessor.DAO.CustomerDAO;
import com.techtest.UserDataProcessor.Entity.Customer;
import com.techtest.UserDataProcessor.Repository.CustomerRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerDataService {
    private CustomerRepository customerRepository;
    public CustomerDataService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerDAO> parseCustomerListFromFile(File fileLocation) throws IOException {
        String[] headers = { "Customer Ref", "Customer Name", "Address Line 1", "Address Line 2", "Town", "County", "Country", "Postcode"};

        Reader in = new FileReader(fileLocation);

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(headers)
                .setSkipHeaderRecord(true)
                .build();

        Iterable<CSVRecord> records = csvFormat.parse(in);

        List<CustomerDAO> customers = new ArrayList<>();

        for (CSVRecord record : records) {
            String customerRef = record.get("Customer Ref");
            String customerName = record.get("Customer Name");
            String addressLine1 = record.get("Address Line 1");
            String addressLine2 = record.get("Address Line 2");
            String town = record.get("Town");
            String county = record.get("County");
            String country = record.get("Country");
            String postCode = record.get("Postcode");
            CustomerDAO customerDAO = new CustomerDAO(customerRef, customerName, addressLine1, addressLine2, town, county, country, postCode);
            customers.add(customerDAO);
        }
        return customers;
    }

    public void importCustomerData(List<CustomerDAO> customerData)
    {
        for (CustomerDAO customerDAO : customerData) {
            save(customerDAO);
        }
    }

    public CustomerDAO save(CustomerDAO customerDAO)
    {
        return new CustomerDAO(customerRepository.save(customerDAO.customerEntity()));
    }

    public CustomerDAO getByCustomerRef(String customerRef)
    {
        Customer customer = customerRepository.findByCustomerRef(customerRef);
        return new CustomerDAO(customer);
    }
}
