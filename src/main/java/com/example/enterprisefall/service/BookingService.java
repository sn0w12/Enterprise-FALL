package com.example.enterprisefall.service;

import com.example.enterprisefall.entity.Booking;
import com.example.enterprisefall.entity.Car;
import com.example.enterprisefall.repository.BookingRepository;
import com.example.enterprisefall.repository.CarRepository;
import com.example.enterprisefall.repository.CustomerRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String deleteBookingById(long id) {
        bookingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Booking with ID " + id + " does not exist."));
        bookingRepository.deleteById(id);
        logger.info("\nDeleted booking with ID: " + id + "\n");
        return "booking has been deleted" + getBookingById(id);
    }

    @Override
    public String cancelBooking(long bookingId) {
        Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);
        if (optionalBooking.isPresent()) {
            Booking existingBooking = optionalBooking.get();
            existingBooking.setReturnDate(LocalDate.now()) ;

            Car car = existingBooking.getCar();
            car.setIsBooked(false);
            bookingRepository.save(existingBooking);
            carRepository.save(car);

            logger.info("\nBooking with ID: " + bookingId + " has been cancelled\n");
            return "Booking has been cancelled";
        } else {
            return "Booking with ID " + bookingId + " does not exist.";
        }

    }

    @Override
    public Booking updateBooking(long id, Booking booking) {
        return null;
    }


    // METODER
}
