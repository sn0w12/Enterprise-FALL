

package com.example.enterprisefall.service;

import com.example.enterprisefall.entity.Customer;
import com.example.enterprisefall.repository.CustomerRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

  private Logger logger = Logger.getLogger(CustomerService.class);

  private CustomerRepository customerRepository;

  @Autowired
  public CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public List<Customer> getAllCustomers() {
    return customerRepository.findAll();
  }

  public Customer addCustomer(Customer newCustomer) {
    Customer savedCustomer = customerRepository.save(newCustomer);
    logger.info("Added new customer: " + savedCustomer);
    return customerRepository.save(newCustomer);
  }

  public void deleteCustomer(int id) {
        customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Customer with ID " + id + " does not exist."));
        customerRepository.deleteById(id);
        logger.info("Deleted customer with ID: " + id);
    }

    // Update customer
    public String updateCustomer(Customer customerToBeUpdated) {
      Optional<Customer> optionalCustomer = customerRepository.findById(customerToBeUpdated.getId());

      StringBuilder updateMessage = new StringBuilder();

      if (optionalCustomer.isPresent()) {
        Customer existingCustomer = optionalCustomer.get();

        updateMessage.append("\nAdmin updated Customer with id: " + existingCustomer.getId() + "\n");

        if (customerToBeUpdated.getUsername() != null && (!customerToBeUpdated.getUsername().isEmpty() || !customerToBeUpdated.getUsername().contains(existingCustomer.getName()))) {
          existingCustomer.setUsername(customerToBeUpdated.getUsername());
          updateMessage.append("Username changed to: " + existingCustomer.getUsername() + "\n");
        }

        if (customerToBeUpdated.getName() != null && (!customerToBeUpdated.getName().isEmpty() || !customerToBeUpdated.getUsername().contains(existingCustomer.getName()))) {
          existingCustomer.setName(customerToBeUpdated.getName());
          updateMessage.append("Name changed to: " + existingCustomer.getName() + "\n");
        }

        if (customerToBeUpdated.getAddress() != null && (!customerToBeUpdated.getAddress().isEmpty() || !customerToBeUpdated.getAddress().contains(existingCustomer.getAddress()))) {
          existingCustomer.setAddress(customerToBeUpdated.getAddress());
          updateMessage.append("Address changed to: " + existingCustomer.getAddress() + "\n");
        }

        if (customerToBeUpdated.getEmail() != null && (!customerToBeUpdated.getEmail().isEmpty() || !customerToBeUpdated.getEmail().contains(existingCustomer.getEmail()))) {
          existingCustomer.setEmail(customerToBeUpdated.getEmail());
          updateMessage.append("Email changed to: " + existingCustomer.getEmail() + "\n");
        }

        if (customerToBeUpdated.getPhoneNumber() != null && (!customerToBeUpdated.getPhoneNumber().isEmpty() || !customerToBeUpdated.getPhoneNumber().contains(existingCustomer.getPhoneNumber()))) {
          existingCustomer.setPhoneNumber(customerToBeUpdated.getPhoneNumber());
          updateMessage.append("Phone number changed to: " + existingCustomer.getPhoneNumber() + "\n");
        }

        logger.info(updateMessage);
        customerRepository.save(existingCustomer);
        return updateMessage.toString();

      } else {
        logger.error("\nERROR: Customer not found with ID " + customerToBeUpdated.getId() + "\n");
        return "ERROR: Customer not found";
      }
    }

}
