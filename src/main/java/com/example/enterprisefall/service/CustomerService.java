

package com.example.enterprisefall.service;

import com.example.enterprisefall.entity.Booking;
import com.example.enterprisefall.entity.Car;
import com.example.enterprisefall.entity.Customer;
import com.example.enterprisefall.repository.BookingRepository;
import com.example.enterprisefall.repository.CarRepository;
import com.example.enterprisefall.repository.CustomerRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class CustomerService {

  private Logger logger = Logger.getLogger(CustomerService.class);

  private CustomerRepository customerRepository;
  private Booking booking;
  private BookingRepository bookingRepository;
  private Car car;
  private Customer customer;




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

  public Customer newOrder(Customer newOrder){

    Optional<Booking> bookingOptional = bookingRepository.findById(Long.valueOf((newOrder.getId())));
    if (bookingOptional.isPresent()){
    Booking booking = bookingOptional.get();

    booking.setBookingDate(new Date());

    // booking.setBookingDate(new Date());
    booking.getId();

    
    //log4j
    logger.info("New booking performed: " + newOrder);
    System.out.println("\nRental car ordered successfully: " + car.getModel() + "\n");

    // lägg till orders
      return newOrder;
  }



}
