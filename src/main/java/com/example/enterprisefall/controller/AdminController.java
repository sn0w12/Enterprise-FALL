

package com.example.enterprisefall.controller;

import com.example.enterprisefall.entity.Booking;
import com.example.enterprisefall.entity.Car;
import com.example.enterprisefall.entity.Customer;
import com.example.enterprisefall.service.BookingService;
import com.example.enterprisefall.service.CarService;
import com.example.enterprisefall.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/")
public class AdminController {

  private CustomerService customerService;
  private CarService carService;
  private BookingService bookingService;

  @Autowired
  public AdminController(CustomerService customerService, CarService carService, BookingService bookingService) {
    this.customerService = customerService;
    this.carService = carService;
    this.bookingService = bookingService;
  }

//  @PostMapping("/addcar")
//  public ResponseEntity<Car> addCar(@RequestBody Car newCar) {
//    // TODO
//  }
//
//  @PostMapping("/addcustomer")
//  public ResponseEntity<Customer> addCustomer(@RequestBody Customer newCustomer) {
//    // TODO
//  }
//
//  @GetMapping("/customers")
//  public ResponseEntity<List<Customer>> getAllCustomers() {
//    // TODO
//  }
//
//  @GetMapping("/allcars")
//  public ResponseEntity<List<Car>> listAllCars() {
//    // TODO
//  }
//
//  @GetMapping("/orders")
//  public ResponseEntity<List<Booking>> listAllBookings() {
//    // TODO
//  }
//
//  @PutMapping("/updatecar")
//  public ResponseEntity<Car> udpateCare(@RequestBody Car carToBeUpdated) {
//    // TODO
//  }
//
//  @PutMapping("/updatecustomer")
//  public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customerToBeUpdated) {
//    // TODO
//  }
//
//  @DeleteMapping("/deletecar")
//  public ResponseEntity<String> deleteCar(@RequestBody Car carToBeDeleted) {
//    // TODO
//  }
//
//  @DeleteMapping("/deletecustomer")
//  public ResponseEntity<String> deleteCustomer(@RequestBody Customer customerToBeDeleted) {
//    // TODO
//  }
//
//  @DeleteMapping("/deleteorder")
//  public ResponseEntity<String> deleteBooking(@RequestBody Booking bookingToBeDeleted) {
//    // TODO
//  }
}
