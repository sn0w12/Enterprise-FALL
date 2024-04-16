package com.example.enterprisefall.service;

import com.example.enterprisefall.entity.Booking;
import com.example.enterprisefall.repository.BookingRepository;
import com.example.enterprisefall.repository.CarRepository;
import com.example.enterprisefall.repository.CustomerRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService implements BookingServiceInterface {

  private Logger logger = Logger.getLogger(BookingService.class);
  private BookingRepository bookingRepository;
  private CarRepository carRepository;
  private CustomerRepository customerRepository;

  @Autowired
  public BookingService(BookingRepository bookingRepository, CarRepository carRepository, CustomerRepository customerRepository) {
    this.bookingRepository = bookingRepository;
    this.carRepository = carRepository;
    this.customerRepository = customerRepository;
  }


  //aktiva bookings
  @Override
  public List<Booking> getAllBookings() {
    return bookingRepository.findAll();
  }

  @Override
  public Booking getBookingById(long id) {
    return bookingRepository.findById();
  }

  @Override
  public Booking addNewBooking(Booking booking) {
    return null;
  }

  @Override
  public Booking addNewBookingDate(Booking booking) {
    bookingRepository.save();
      return booking;
  }

  @Override
  public void deleteBookingById(long id) {
    bookingRepository.deleteById();


  }

  @Override
  public Booking updateBooking(long id, Booking booking) {
    return null;
  }


  // METODER
}
