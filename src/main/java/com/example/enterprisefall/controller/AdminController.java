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

    @PostMapping("/addcar")
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
        return ResponseEntity.ok(carService.addCar(car));
    }

    @PostMapping("/addcustomer")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer newCustomer) {
        Customer savedCustomer = customerService.addCustomer(newCustomer);
        return ResponseEntity.ok(savedCustomer);
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/allcars")
    public ResponseEntity<List<Car>> listAllCars() {
        return ResponseEntity.ok(carService.findAllCars());
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Booking>> listAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @PutMapping("/updatecar")
    public ResponseEntity<String> updateCare(@RequestBody Car carToBeUpdated) {
        return ResponseEntity.ok(carService.updateCar(carToBeUpdated));
    }

    @PutMapping("/updatecustomer")
    public ResponseEntity<String> updateCustomer(@RequestBody Customer customerToBeUpdated) {
        return ResponseEntity.ok(customerService.updateCustomer(customerToBeUpdated));
    }

    @DeleteMapping("/deletecar/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable("id") int id) {
        return ResponseEntity.ok(carService.deleteCarById(id));
    }

    @DeleteMapping("/deletecustomer")
    public ResponseEntity<String> deleteCustomer(@RequestBody Customer customerToBeDeleted) {
        return ResponseEntity.ok(customerService.deleteCustomer(customerToBeDeleted));
    }

    @DeleteMapping("/deleteorder")
    public ResponseEntity<String> deleteBooking(@RequestParam ("bookingId") int id) {
        return ResponseEntity.ok(bookingService.deleteBookingById(id));
    }
}