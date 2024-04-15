package com.example.enterprisefall.service;

import com.example.enterprisefall.repository.BookingRepository;
import com.example.enterprisefall.repository.CarRepository;
import com.example.enterprisefall.repository.CustomerRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

  private Logger logger = Logger.getLogger(BookingService.class);
  private BookingRepository bookingRepository;
  private CarRepository carRepository;
  private CustomerRepository customerRepository;

  @Autowired
  public BookingService(BookingRepository bookingRepository, CarRepository carRepository, CustomerRepository customerRepository) {
    this.bookingRepository = bookingRepository;
    this.carRepository = carRepository;
    this.customerRepository = customerRepository;
  }

  // METODER
}
