package com.example.enterprisefall.controller;

import com.example.enterprisefall.entity.Booking;
import com.example.enterprisefall.entity.Car;
import com.example.enterprisefall.service.BookingService;
import com.example.enterprisefall.service.CarService;
import com.example.enterprisefall.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/v1/")
public class CustomerController {

  private CustomerService customerService;
  private CarService carService;
  private BookingService bookingService;

  @Autowired
  public CustomerController(CustomerService customerService, CarService carService, BookingService bookingService) {
    this.customerService = customerService;
    this.carService = carService;
    this.bookingService = bookingService;
  }

//  @GetMapping("/cars")
//  public ResponseEntity<List<Car>> getAllCars() {
//    // TODO
//  }

//  @PostMapping("/ordercar")
//  public ResponseEntity<String> bookCar() {
//    // TODO
//  }

//  @PutMapping("/cancelorder")
//  public ResponseEntity<Booking> cancelBooking() {
//    // TODO
//  }

//  @GetMapping("/myorders")
//  public ResponseEntity<List<Booking>> listOrdersForCustomer() {
//    // TODO
//  }
}
