package com.techtest.UserDataProcessor.Repository;


import com.techtest.UserDataProcessor.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Customer findById(final Integer id);

}
