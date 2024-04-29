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
        String bookingResult = customerService.orderCar(customerId, carId);
        return ResponseEntity.ok(bookingResult);
    }



            // Inte delete utan bara cancel
            // ändra slutdatum och att bil går från unavailable till available
    @PutMapping("/cancelorder")
    public ResponseEntity<String> cancelBooking(@RequestBody Booking booking) {

        bookingService.deleteBookingById(booking.getId());

        return ResponseEntity.ok("Booking cancelled");

    }

    @GetMapping("/myorders")
    public ResponseEntity<List<String>> listOrdersForCustomer(@RequestParam int customerId) {
        List<String> bookings = customerService.listBookings(customerId);
        return ResponseEntity.ok(bookings);
    }
}
