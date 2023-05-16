package com.test.neoris.service.customer;

import static org.mockito.Mockito.*;
import com.test.neoris.entities.Customer;
import com.test.neoris.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    public void testFindCustomerById() {
        Integer customerId = 1;
        Customer customer = new Customer();
        customer.setClientId(customerId);
        customer.setName("Andres Espitia");
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        ResponseEntity<Customer> response = customerService.findCustomerById(customerId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customer, response.getBody());
    }

    @Test
    public void testFindCustomerNotFound() {
        Integer customerId = 1;
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        ResponseEntity<Customer> response = customerService.findCustomerById(customerId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testSaveCustomer() {

        Customer customer = new Customer();
        customer.setName("Andres");

        ResponseEntity<String> response = customerService.saveCustomer(customer);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Cliente agregado exitosamente", response.getBody());
        verify(customerRepository).save(customer);
    }

    @Test
    public void testDeleteCustomer() {
        int id = 1;
        Mockito.when(customerRepository.findById(id)).thenReturn(Optional.of(new Customer()));

        ResponseEntity<String> response = customerService.deleteCustomer(id);

        Mockito.verify(customerRepository, Mockito.times(1)).findById(id);
        Mockito.verify(customerRepository, Mockito.times(1)).deleteById(id);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals("Cliente eliminado correctamente.", response.getBody());
    }

    @Test
    public void testDeleteNotExistCustomer() {
        int id = 1;
        Mockito.when(customerRepository.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<String> response = customerService.deleteCustomer(id);

        Mockito.verify(customerRepository, Mockito.times(1)).findById(id);
        Mockito.verify(customerRepository, Mockito.never()).deleteById(id);
        Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assert.assertNull(response.getBody());
    }

    @Test
    public void testUpdateCustomer() {
        // Arrange
        Integer customerId = 1;
        Customer customer = new Customer();
        customer.setName("Andres");
        customer.setAddress("Calle siempre viva");
        customer.setAge(24);
        customer.setGender("M");
        customer.setPassword("abc321");
        customer.setStatus(true);
        customer.setPhoneNumber("3244543440");
        customer.setIdentification("1023974162");

        Customer existingCustomer = new Customer();
        existingCustomer.setClientId(customerId);
        existingCustomer.setName("Felipe");
        existingCustomer.setAddress("Avenida");
        existingCustomer.setAge(24);
        existingCustomer.setGender("M");
        existingCustomer.setPassword("CONTRASEÑA321");
        existingCustomer.setStatus(false);
        existingCustomer.setPhoneNumber("987654123");
        existingCustomer.setIdentification("741852963");

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(existingCustomer));

        ResponseEntity<String> response = customerService.updateCustomer(customer, customerId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Customer actualizado exitosamente", response.getBody());

        ArgumentCaptor<Customer> captor = ArgumentCaptor.forClass(Customer.class);
        verify(customerRepository).save(captor.capture());
        Customer updatedCustomer = captor.getValue();
        assertEquals(customerId, updatedCustomer.getClientId());
        assertEquals(customer.getName(), updatedCustomer.getName());
        assertEquals(customer.getAddress(), updatedCustomer.getAddress());
        assertEquals(customer.getAge(), updatedCustomer.getAge());
        assertEquals(customer.getGender(), updatedCustomer.getGender());
        assertEquals(customer.getPassword(), updatedCustomer.getPassword());
        assertEquals(customer.isStatus(), updatedCustomer.isStatus());
        assertEquals(customer.getPhoneNumber(), updatedCustomer.getPhoneNumber());
        assertEquals(customer.getIdentification(), updatedCustomer.getIdentification());
    }
}