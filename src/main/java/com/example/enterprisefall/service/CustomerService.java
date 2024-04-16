package com.example.enterprisefall.service;

import com.example.enterprisefall.entity.Customer;
import com.example.enterprisefall.repository.CustomerRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private Logger logger = Logger.getLogger(CustomerService.class);

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // METODER

    public Customer addCustomer(Customer newCustomer) {
        Customer savedCustomer = customerRepository.save(newCustomer);
        logger.info("Added new customer: " + savedCustomer);
        return customerRepository.save(newCustomer);
    }


}
