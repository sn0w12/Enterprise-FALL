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


//Kunderna ska kunna göra ett antal aktiviteter med följande endpoints:
//        • Lista tillgängliga bilar GET /api/v1/cars
//• Beställa hyrbil POST /api/v1/ordercar
//• Avboka PUT /api/v1/cancelorder



@Controller
@RequestMapping("/api/v1/")
public class CustomerController {

    private CustomerService customerService;
    private CarService carService;
    private BookingService bookingService;
    private Car car;

    @Autowired
    public CustomerController(CustomerService customerService, CarService carService, BookingService bookingService) {
        this.customerService = customerService;
        this.carService = carService;
        this.bookingService = bookingService;
    }

    @GetMapping("/cars")
    public ResponseEntity<List<Car>> listAvailableCars() {
        List<Car> cars = carService.findAllAvailableCars();
        return ResponseEntity.ok(cars);
    }


    @PostMapping("/ordercar")
    public ResponseEntity<String> orderCar(@RequestParam("customerId") int customerId, @RequestParam("carId") int carId) {
        String bookingResult = customerService.orderCar(customerId, carId);
        return ResponseEntity.ok(bookingResult);
    }


    //Borde det inte vara remove customer?
  @PutMapping("/cancelorder")
  public ResponseEntity<String> cancelBooking(@RequestBody Booking booking) {

        bookingService.deleteBookingById(booking.getId());

        return ResponseEntity.ok("Booking cancelled");

    }


//• Se tidigare och aktiva bokningar GET /api/v1/myorder
//@GetMapping("/myorders")
//public ResponseEntity<List<Booking>> listOrdersForCustomer() {
//        //show getIsbooked and !getIsbooked











}

