

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
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

  private Logger logger = Logger.getLogger(CustomerService.class);

  private CustomerRepository customerRepository;
  private Car car;
  private CarRepository carRepository;
  private CarService carService;
  private Booking booking;
  private BookingService bookingService;


  @Autowired
  public CustomerService(CustomerRepository customerRepository, CarService carService, CarRepository carRepository) {
    this.customerRepository = customerRepository;
    this.carService = carService;
    this.carRepository = carRepository;

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

    // ++ EXEMPEL FÖR ATT PASSA HELA CUSTOMER

    // Så här kan du göra till exempel
    //tack <3
    Optional<Customer> theCustomer = customerRepository.findById(customerId);

    if (theCustomer.isPresent()) { // om customer är present (om den hittar id)
      Customer theActualCustomer = theCustomer.get(); // skapa customern som har det id.t
      // nu har du tillgång till _allt_ i den customern till exempel:
      theActualCustomer.getPhoneNumber(); // testa skriv theActualCustomer. så får du upp alla och se hur det ser ut

    } else {
      // customer kan inte hittas
      System.out.println("Customer not found.");
    }

    // -- EXEMPEL FÖR ATT PASSA HELA CUSTOMER


    //Fungerar
    customerRepository.findById(customerId)
            .orElseThrow(() -> new IllegalArgumentException("Customer with ID " + customerId + " does not exist."));

    Car car = carService.findAllAvailableCars().get(carId);
    // Går för tillfället att boka samma bil flera gånger

    if (car.getIsBooked()) {
      return "Car is not available for booking.";
    }


    //Customer customer = new Customer(); behövs inte om man passerar customer med optional
    Booking booking = new Booking();
    booking.setId((long) customerId);
    car.setId(carId);
    car.getPricePerDay();

    LocalDate bookingEndDate = LocalDate.now().plusDays(7);
    booking.setBookingDate(LocalDate.now());
    booking.setBookingEndDate(bookingEndDate);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    String bookingDateFormatted = booking.getBookingDate().format(formatter);
    String returnDateFormatted = bookingEndDate.format(formatter);


    //Ska läggas till
    //Spara en bokning i en lista där det blir ett boknings nummer med customer id.

    // booking.savebooking();
    car.setIsBooked(true);
    carService.updateCar(carId, car);

    logger.info("Customer booked " + car.getBrand() + " " + car.getModel() + " " + car.getRegistrationNumber() +
            " booked for " + theCustomer.get().getName() + " on " + bookingDateFormatted + " for " + car.getPricePerDay() +
            " Return car by: " + returnDateFormatted);


    return car.getBrand() + " " + car.getModel() + " " + car.getRegistrationNumber() +
            " booked for " + theCustomer.get().getName() + " on " + bookingDateFormatted + " for " + car.getPricePerDay() + "kr per day." +
            " Return car by: " + returnDateFormatted;

  }

  public String listBookings(int customerId) {
    Optional<Customer> theCustomer = customerRepository.findById(customerId);
    // Optional<Car> theCar = carRepository.findById(CarId);
    //enter customerId to see their active and previous bookings
    if (theCustomer.isPresent()) {
//      Customer customer = theCustomer.get();
//      // Car car = theCar.get();
//      customer.getName();
//      car.getModel();
//      car.getIsBooked();
//      car.getBrand();
//      car.getPricePerDay();
      //Customer with "ID" has this booking which is active and show previous bookings as inactive

      return theCustomer.get().getBookings();
    }

    return "No bookings previous or current.";
  }
}


