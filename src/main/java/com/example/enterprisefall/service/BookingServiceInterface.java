package com.example.enterprisefall.service;

import com.example.enterprisefall.entity.Booking;

import java.util.List;
import java.util.Optional;


public interface BookingServiceInterface {
    List<Booking> getAllBookings();

    Optional<Booking> getBookingById(int id);

    Booking addNewBookingDate(Booking booking);

    String deleteBookingById(int id);

    String cancelBooking(Booking booking);



}