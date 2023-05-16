package com.test.neoris.service.customer;

import com.test.neoris.entities.Customer;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    public List<Customer> findAllCustomers();

    public ResponseEntity<Customer> findCustomerById(Integer id);

    public ResponseEntity<String> saveCustomer(Customer customerNew);

    public ResponseEntity<String> deleteCustomer(Integer id);

    public ResponseEntity<String> updateCustomer(Customer customerNew, Integer id);
}
