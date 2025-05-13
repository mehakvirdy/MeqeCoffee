package com.example.mycoffee.controller;
import com.example.mycoffee.model.Customer;
import com.example.mycoffee.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Customer> getCustomerById(@PathVariable int id) {
        return customerRepository.findById(id);
    }

    @PostMapping
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @PutMapping("/{id}")
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Customer updateCustomer(@PathVariable int id, @RequestBody Customer customer) {
        // check if the customer exists
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));

        // update the fields of the existing customer
        // assuming customer is the new customer information
        existingCustomer.setName(customer.getName());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setPhone(customer.getPhone());

        // save the updated customer back to the repository
        return customerRepository.save(existingCustomer);
    }

    @DeleteMapping("/{id}")
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void deleteCustomer(@PathVariable int id) {
        customerRepository.deleteById(id);
    }
}