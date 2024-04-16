package com.example.enterprisefall.controller;

import com.example.enterprisefall.entity.Booking;
import com.example.enterprisefall.entity.Car;
import com.example.enterprisefall.entity.Customer;
import com.example.enterprisefall.service.BookingService;
import com.example.enterprisefall.service.CarService;
import com.example.enterprisefall.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Administratörer ska kunna göra ett antal aktiviteter med följande endpoints:
//        • Lista kunder GET /api/v1/customers
//• Lägga till fordon POST /api/v1/addcar
//• Ta bort fordon DELETE /api/v1/deletecar
//• Uppdatera fordon PUT /api/v1/updatecar
//• Lägga till kund POST /api/v1/addcustomer
//• Uppdatera kund PUT /api/v1/updatecustomer
//• Ta bort kund DELETE /api/v1/deletecustomer
//• Lista samtliga bilar GET /api/v1/allcars




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
        Car savedCar = carService.addCar(car);
        return ResponseEntity.ok(savedCar);
    }

    @PostMapping("/addcustomer")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer newCustomer) {
        Customer savedCustomer = customerService.addCustomer(newCustomer);
        return ResponseEntity.ok(savedCustomer);
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

//  @GetMapping("/allcars")
//  public ResponseEntity<List<Car>> listAllCars() {
//    // TODO
//  }
////
// • Lista bokningar GET /api/v1/orders
@GetMapping("/orders")
public ResponseEntity<List<Booking>> listAllBookings() {
    List<Booking> bookings = bookingService.getAllBookings();
    return ResponseEntity.ok(bookings);
 }

  @PutMapping("/updatecar")
  public ResponseEntity<String> updateCare(@RequestBody Car carToBeUpdated) {
    return ResponseEntity.ok(carService.updateCar(carToBeUpdated));
  }
//
//  @PutMapping("/updatecustomer")
//  public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customerToBeUpdated) {
//    // TODO
//  }
//
  @DeleteMapping("/deletecar/{id}")
  public ResponseEntity<String> deleteCar(@PathVariable("id") int id) {
      carService.deleteCarById(id);
      return new ResponseEntity<>("Car " + id + " deleted!", HttpStatus.OK);
  }
//
//  @DeleteMapping("/deletecustomer")
//  public ResponseEntity<String> deleteCustomer(@RequestBody Customer customerToBeDeleted) {
//    // TODO
//  }
//

    //• Ta bort bokning DELETE /api/v1/deleteorder
@DeleteMapping("/deleteorder/{id}")
public ResponseEntity<String> deleteBooking(@PathVariable ("id") int id) {
    bookingService.deleteBookingById(id);

    return null;
}
}
