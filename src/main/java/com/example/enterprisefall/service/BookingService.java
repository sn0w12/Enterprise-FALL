package com.example.enterprisefall.service;

import com.example.enterprisefall.entity.Booking;
import com.example.enterprisefall.entity.Car;
import com.example.enterprisefall.repository.BookingRepository;
import com.example.enterprisefall.repository.CarRepository;
import com.example.enterprisefall.repository.CustomerRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public Optional<Booking> getBookingById(int id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        return booking;
    }

    @Override
    public Booking addNewBookingDate(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public String deleteBookingById(int id) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();
            Car car = booking.getCar();

            car.setIsBooked(false);

            carRepository.save(car);

            bookingRepository.delete(booking);
            logger.info("\nDeleted booking with ID: " + booking.getId() + "\n");
            return "Booking with ID " + booking.getId() + " has been deleted";
        } else {
            logger.error("\nBooking with ID " + id + " does not exist.\n");
            return "Booking with ID " + id + " does not exist.";
        }
    }

    @Override
    public String cancelBooking(Booking booking) {
        Optional<Booking> optionalBooking = bookingRepository.findById(booking.getId());
        if (optionalBooking.isPresent()) {
            Booking existingBooking = optionalBooking.get();
            existingBooking.setReturnDate(LocalDate.now()) ;

            Car car = existingBooking.getCar();
            car.setIsBooked(false);
            carRepository.save(car);
            bookingRepository.save(existingBooking);

            logger.info("Booking with ID " + existingBooking.getId() + " has been cancelled");
            return "Booking has been cancelled";
        } else {
            return "Booking with ID does not exist.";
        }
    }
}
