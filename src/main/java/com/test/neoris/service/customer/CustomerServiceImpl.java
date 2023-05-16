package com.test.neoris.service.customer;

import com.test.neoris.entities.Customer;
import com.test.neoris.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public ResponseEntity<Customer> findCustomerById(Integer id) {
        try {
            Optional<Customer> customer = customerRepository.findById(id);
            Customer foundCustmer = customer.orElseThrow(() -> new EntityNotFoundException("No se encontró cliete con numero : " + id));
            return ResponseEntity.ok(foundCustmer);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<String> saveCustomer(Customer customerNew) {
        customerRepository.save(customerNew);
        return ResponseEntity.ok("Cliente agregado exitosamente");
    }

    @Override
    public ResponseEntity<String> deleteCustomer(Integer id) {
        if (customerRepository.findById(id).isPresent()) {
            customerRepository.deleteById(id);
            return ResponseEntity.ok("Cliente eliminado correctamente.");
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<String> updateCustomer(Customer customer, Integer id) {
        if (customerRepository.findById(id).isPresent()) {
            Customer newCustomer = new Customer();
            newCustomer.setClientId(id);
            newCustomer.setName(customer.getName());
            newCustomer.setAddress(customer.getAddress());
            newCustomer.setAge(customer.getAge());
            newCustomer.setGender(customer.getGender());
            newCustomer.setPassword(customer.getPassword());
            newCustomer.setStatus(customer.isStatus());
            newCustomer.setPhoneNumber(customer.getPhoneNumber());
            newCustomer.setIdentification(customer.getIdentification());
            customerRepository.save(newCustomer);

            return ResponseEntity.ok("Customer actualizado exitosamente");
        }
        return ResponseEntity.notFound().build();
    }
}
