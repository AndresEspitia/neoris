package com.test.neoris.controllers.customer;

import com.test.neoris.entities.Customer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerController {

    public List<Customer> getCustomers();

    public ResponseEntity<Customer> getCustomerById(int id);

    public ResponseEntity<String> addCustomer(Customer customer);

    public ResponseEntity<String> deleteCustomer(int id);

    public ResponseEntity<String> updateCustomer(Customer customerNew, int id);

}
