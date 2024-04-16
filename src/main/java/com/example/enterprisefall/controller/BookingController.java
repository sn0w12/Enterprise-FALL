package com.example.enterprisefall.controller;

import com.example.enterprisefall.entity.Booking;
import com.example.enterprisefall.service.BookingService;
import com.example.enterprisefall.service.CarService;
import com.example.enterprisefall.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("api/v1/bookings")
public class BookingController {

    private CustomerService customerService;
    private CarService carService;
    @Autowired
    private BookingService bookingService;

    @Autowired
    public BookingController(CustomerService customerService, CarService carService, BookingService bookingService) {
        this.bookingService = bookingService;
        this.carService = carService;
        this.customerService = customerService;
    }

    @GetMapping("/Bookings")
    public ResponseEntity<List<Booking>> getAllBookings() {
        //return bookings based on number of booking
        List<Booking> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    @PostMapping("/DateBooked")
    public ResponseEntity<String> dateBooked() {
        //Add booking based on ID and show date of when it is booked
        return null;
    }

    @DeleteMapping("/cancelBooking")
    public ResponseEntity<Booking> cancelBooking() {
        //remove booking by ID
        //bookingService.deleteBookingById();

        return null;
    }


}
