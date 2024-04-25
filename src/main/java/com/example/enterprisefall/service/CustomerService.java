

package com.example.enterprisefall.service;

import com.example.enterprisefall.entity.Booking;
import com.example.enterprisefall.entity.Car;
import com.example.enterprisefall.entity.Customer;
import com.example.enterprisefall.repository.CarRepository;
import com.example.enterprisefall.repository.CustomerRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

  private Logger logger = Logger.getLogger(CustomerService.class);

  private CustomerRepository customerRepository;
  private CarRepository carRepository;
  private CarService carService;
  private BookingService bookingService;


  @Autowired
  public CustomerService(CustomerRepository customerRepository, CarService carService, CarRepository carRepository, BookingService bookingService) {
    this.customerRepository = customerRepository;
    this.carService = carService;
    this.carRepository = carRepository;
    this.bookingService = bookingService;

  }

  public List<Customer> getAllCustomers() {
    return customerRepository.findAll();
  }

  public Customer addCustomer(Customer newCustomer) {
    Customer savedCustomer = customerRepository.save(newCustomer);
    logger.info("Admin added new customer: " + savedCustomer);
    return customerRepository.save(newCustomer);
  }

  public void deleteCustomer(int id) {
    customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Customer with ID " + id + " does not exist."));
    customerRepository.deleteById(id);
    logger.info("Admin deleted customer with ID: " + id);
  }

  // Update customer
  public String updateCustomer(Customer customerToBeUpdated) {
    Optional<Customer> optionalCustomer = customerRepository.findById(customerToBeUpdated.getId());

    StringBuilder updateMessage = new StringBuilder();

    if (optionalCustomer.isPresent()) {
      Customer existingCustomer = optionalCustomer.get();

      updateMessage.append("\nAdmin updated Customer with id: " + existingCustomer.getId() + "\n");

      if (customerToBeUpdated.getUsername() != null && (!customerToBeUpdated.getUsername().isEmpty() || !customerToBeUpdated.getUsername().contains(existingCustomer.getName()))) {
        existingCustomer.setUsername(customerToBeUpdated.getUsername());
        updateMessage.append("Username changed to: " + existingCustomer.getUsername() + "\n");
      }

      if (customerToBeUpdated.getName() != null && (!customerToBeUpdated.getName().isEmpty() || !customerToBeUpdated.getUsername().contains(existingCustomer.getName()))) {
        existingCustomer.setName(customerToBeUpdated.getName());
        updateMessage.append("Name changed to: " + existingCustomer.getName() + "\n");
      }

      if (customerToBeUpdated.getAddress() != null && (!customerToBeUpdated.getAddress().isEmpty() || !customerToBeUpdated.getAddress().contains(existingCustomer.getAddress()))) {
        existingCustomer.setAddress(customerToBeUpdated.getAddress());
        updateMessage.append("Address changed to: " + existingCustomer.getAddress() + "\n");
      }

      if (customerToBeUpdated.getEmail() != null && (!customerToBeUpdated.getEmail().isEmpty() || !customerToBeUpdated.getEmail().contains(existingCustomer.getEmail()))) {
        existingCustomer.setEmail(customerToBeUpdated.getEmail());
        updateMessage.append("Email changed to: " + existingCustomer.getEmail() + "\n");
      }

      if (customerToBeUpdated.getPhoneNumber() != null && (!customerToBeUpdated.getPhoneNumber().isEmpty() || !customerToBeUpdated.getPhoneNumber().contains(existingCustomer.getPhoneNumber()))) {
        existingCustomer.setPhoneNumber(customerToBeUpdated.getPhoneNumber());
        updateMessage.append("Phone number changed to: " + existingCustomer.getPhoneNumber() + "\n");
      }

      logger.info(updateMessage);
      customerRepository.save(existingCustomer);
      return updateMessage.toString();

    } else {
      logger.error("\nERROR: Customer not found with ID " + customerToBeUpdated.getId() + "\n");
      return "ERROR: Customer not found";
    }
  }

  public String orderCar(int customerId, int carId) {
    Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
    Optional<Car> optionalCar = carRepository.findById(carId);

    if (optionalCustomer.isEmpty() || optionalCar.isEmpty()) {
      return "Customer or car not found.";
    }

    Customer customer = optionalCustomer.get();
    Car car = optionalCar.get();

    if (car.getIsBooked()) {
      return "Car is not available for booking.";
    }

    Booking booking = new Booking(customer);
    booking.setCar(car);


    LocalDate bookingEndDate = LocalDate.now().plusDays(7);
    booking.setBookingDate(LocalDate.now());
    booking.setReturnDate(bookingEndDate);


    bookingService.addNewBookingDate(booking);

    car.setIsBooked(false);
    carService.updateCar(carId, car);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    String bookingDateFormatted = booking.getBookingDate().format(formatter);
    String returnDateFormatted = bookingEndDate.format(formatter);

    logger.info("Customer booked " + car.getBrand() + " " + car.getModel() + " " + car.getRegistrationNumber() +
            " booked for " + customer.getName() + " on " + bookingDateFormatted + " for " + car.getPricePerDay() +
            " Return car by: " + returnDateFormatted);

    return car.getBrand() + " " + car.getModel() + " " + car.getRegistrationNumber() +
            " booked for " + customer.getName() + " on " + bookingDateFormatted + " for " + car.getPricePerDay() + "kr per day." +
            " Return car by: " + returnDateFormatted;
  }

  public List<String> listBookings(int customerId) {
    Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
    List<String> bookingDetails = new ArrayList<>();

    if (optionalCustomer.isPresent()) {
      Customer customer = optionalCustomer.get();
      List<Booking> bookings = customer.getBookings();

      for (Booking booking : bookings) {
        String details = String.format("- %s %s, Booked on: %s, By: %s, Return by: %s",
                booking.getCar().getBrand(), booking.getCar().getModel(), booking.getBookingDate(), customer.getName(), booking.getReturnDate());

        bookingDetails.add(details);
      }
    }
    logger.info(bookingDetails);
    return bookingDetails;
  }
}



