package com.example.enterprisefall.service;

import com.example.enterprisefall.entity.Booking;

import java.util.List;
import java.util.Optional;


public interface BookingServiceInterface {
    List<Booking> getAllBookings();

    Optional<Booking> getBookingById(long id);

    Booking addNewBooking(Booking booking);

    Booking addNewBookingDate(Booking booking);

    String deleteBookingById(long id);

    Booking updateBooking(long id, Booking booking);
}