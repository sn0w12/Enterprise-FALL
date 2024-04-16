package com.example.enterprisefall.service;

import com.example.enterprisefall.entity.Booking;


import java.util.List;


public interface BookingServiceInterface {
    List<Booking> getAllBookings();
    Booking getBookingById(long id);
    Booking addNewBooking(Booking booking);
    void deleteBookingById(long id);
    Booking updateBooking(long id, Booking booking);
}