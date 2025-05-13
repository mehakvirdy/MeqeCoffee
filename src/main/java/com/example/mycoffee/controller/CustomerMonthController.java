package com.example.mycoffee.controller;

import com.example.mycoffee.model.CustomerOfTheMonth;
import com.example.mycoffee.repository.CustomerMonthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer-of-month")
public class CustomerMonthController {

    @Autowired
    private CustomerMonthRepository customerMonthRepository;

    @GetMapping
    public CustomerOfTheMonth getCustomerOfTheMonth() {
        // call the repository to get the customer of the month
        return customerMonthRepository.findCustomerOfTheMonth();
    }
}