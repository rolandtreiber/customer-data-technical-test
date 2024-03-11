package com.techtest.UserDataProcessor.Controller;

import com.techtest.UserDataProcessor.DAO.CustomerDAO;
import com.techtest.UserDataProcessor.Exception.UserNotFoundException;
import com.techtest.UserDataProcessor.Service.CustomerDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class CustomerController {

    private ResponseEntity<Void> entityWithLocation(Object resourceId) {

        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/customers/{childId}").buildAndExpand(resourceId)
                .toUri();

        return ResponseEntity.created(location).build();
    }
    CustomerDataService customerDataService;
    public CustomerController(CustomerDataService customerDataService) {
        this.customerDataService = customerDataService;
    }

    @GetMapping(value = "customers/{customerRef}")
    public CustomerDAO getCustomerDataByCustomerRef(@PathVariable String customerRef)
    {
        try {
            return customerDataService.getByCustomerRef(customerRef);
        } catch (NullPointerException exception) {
            throw new UserNotFoundException();
        }
    }

    @PostMapping(value = "customers")
    public ResponseEntity<Void> createCustomer(@RequestBody CustomerDAO customerDAO)
    {
        CustomerDAO savedCustomer = customerDataService.save(customerDAO);
        return entityWithLocation(savedCustomer.getCustomerRef());
    }

}
