package com.example.enterprisefall.controller;

import com.example.enterprisefall.entity.Booking;
import com.example.enterprisefall.entity.Car;
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

    @GetMapping("/cars")
    public ResponseEntity<List<Car>> listAvailableCars() {
        return ResponseEntity.ok(carService.findAllAvailableCars());
    }

    @PostMapping("/ordercar")
    public ResponseEntity<String> orderCar(@RequestParam("customerId") int customerId, @RequestParam("carId") int carId) {
        return ResponseEntity.ok(customerService.orderCar(customerId, carId));
    }

    @PutMapping("/cancelorder")
    public ResponseEntity<String> cancelBooking(@RequestBody Booking booking) {
        return ResponseEntity.ok(bookingService.cancelBooking(booking));
    }

    @GetMapping("/myorders")
    public ResponseEntity<List<String>> listOrdersForCustomer(@RequestParam int customerId) {
        return ResponseEntity.ok(customerService.listBookings(customerId));
    }
}
