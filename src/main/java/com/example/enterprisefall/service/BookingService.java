package com.example.enterprisefall.service;

import com.example.enterprisefall.entity.Booking;
import com.example.enterprisefall.repository.BookingRepository;
import com.example.enterprisefall.repository.CarRepository;
import com.example.enterprisefall.repository.CustomerRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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


    @Override
    public List<Booking> getAllBookings() {

        return bookingRepository.findAll();

    }

    @Override
    public Optional<Booking> getBookingById(long id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        return booking;
    }

    @Override
    public Booking addNewBooking(Booking booking) {
        return null;
    }

    @Override
    public Booking addNewBookingDate(Booking booking) {
        return bookingRepository.save(booking);
    }



    @Override
    public void deleteBookingById(long id) {
        bookingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Booking with ID " + id + " does not exist."));
        bookingRepository.deleteById(id);
        logger.info("\nDeleted booking with ID: " + id + "\n");
    }

    @Override
    public Booking updateBooking(long id, Booking booking) {
        return null;
    }


    // METODER
}
