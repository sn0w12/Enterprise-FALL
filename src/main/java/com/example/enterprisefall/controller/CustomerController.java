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


//Kunderna ska kunna göra ett antal aktiviteter med följande endpoints:
//        • Lista tillgängliga bilar GET /api/v1/cars
//• Beställa hyrbil POST /api/v1/ordercar
//• Avboka PUT /api/v1/cancelorder
//• Se tidigare och aktiva bokningar GET /api/v1/myorder




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
  List<Car> cars = carService.findAllAvailableCars();
  return ResponseEntity.ok(cars);
}

//  @PostMapping("/ordercar")
//  public ResponseEntity<String> bookCar() {
//    // TODO
//  }


  //Borde det inte vara remove customer?
//  @PutMapping("/cancelorder")
//  public ResponseEntity<Booking> cancelBooking() {
//    // TODO
//  }

//  @GetMapping("/myorders")
//  public ResponseEntity<List<Booking>> listOrdersForCustomer() {
//    // TODO
//  }
}
