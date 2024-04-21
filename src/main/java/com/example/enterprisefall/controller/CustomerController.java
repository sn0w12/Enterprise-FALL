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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public ResponseEntity<Customer> orderCar(@RequestBody Customer newOrder) {
        Customer newOrder1 = customerService.newOrder(newOrder);
        return ResponseEntity.ok(newOrder1);
    }


//  @PutMapping("/cancelorder")
//  public ResponseEntity<Booking> cancelBooking() {
//    // TODO
//  }


    //• Se tidigare och aktiva bokningar GET /api/v1/myorder
    @GetMapping("/myorders")
    public ResponseEntity<List<Booking>> listOrdersForCustomer() {
        //show getIsbooked and !getIsbooked
        List<Booking> orders = customerService.getOrders()
        return ResponseEntity.ok(orders);

    }
}
