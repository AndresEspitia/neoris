package com.test.neoris.controllers.customer;

import com.test.neoris.entities.Customer;

import com.test.neoris.service.customer.CustomerServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@AllArgsConstructor
@RestController
public class CustomerImpl implements CustomerController{

    private final CustomerServiceImpl customerService;

    @GetMapping(path = "/customers", produces = "application/json")
    @Override
    public List<Customer> getCustomers() {
        return customerService.findAllCustomers();
    }

    @GetMapping(path = "/customers/{id}", produces = "application/json")
    @Override
    public ResponseEntity<Customer> getCustomerById(@PathVariable int id) {
        try {
            return customerService.findCustomerById(id);
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping(path = "/customers/add", produces = "application/json")
    @Override
    public ResponseEntity<String> addCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    @DeleteMapping(path = "/customers/delete/{id}", produces = "application/json")
    @Override
    public ResponseEntity<String> deleteCustomer(@PathVariable int id) {
        return customerService.deleteCustomer(id);
    }

    @PutMapping(path = "/customers/update/{id}", produces = "application/json")
    @Override
    public ResponseEntity<String> updateCustomer(@RequestBody Customer customerNew, @PathVariable int id) {
        return customerService.updateCustomer(customerNew, id);
    }
}
