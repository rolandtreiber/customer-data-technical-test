package com.techtest.UserDataProcessor.DAO;

import com.techtest.UserDataProcessor.Entity.Customer;

public class CustomerDAO {
    private final String customerRef;
    private final String customerName;
    private final String addressLine1;
    private final String addressLine2;
    private final String town;
    private final String county;
    private final String country;
    private final String postcode;

    public CustomerDAO(Customer customer) {
        customerRef = customer.getCustomerRef();
        customerName = customer.getName();
        addressLine1 = customer.getAddressLine1();
        addressLine2 = customer.getAddressLine2();
        town = customer.getTown();
        county = customer.getCounty();
        country = customer.getCountry();
        postcode = customer.getPostCode();
    }

    public CustomerDAO(String customerRef, String customerName, String addressLine1, String addressLine2, String town, String county, String country, String postcode) {
        this.customerRef = customerRef;
        this.customerName = customerName;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.town = town;
        this.county = county;
        this.country = country;
        this.postcode = postcode;
    }

    public Customer customerEntity()
    {
        Customer customer = new Customer();
        customer.setCustomerRef(this.customerRef);
        customer.setName(this.customerName);
        customer.setAddressLine1(this.addressLine1);
        customer.setAddressLine2(this.addressLine2);
        customer.setTown(this.town);
        customer.setCounty(this.county);
        customer.setCountry(this.country);
        customer.setPostCode(this.postcode);
        return customer;
    }

    public String getCustomerRef() {
        return customerRef;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getTown() {
        return town;
    }

    public String getCounty() {
        return county;
    }

    public String getCountry() {
        return country;
    }

    public String getPostcode() {
        return postcode;
    }
}
