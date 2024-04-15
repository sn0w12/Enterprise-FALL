package com.example.enterprisefall.entity;

import jakarta.persistence.*;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String Booking;
    private String BookingDate;
    private String ReturnDate;
    //ID (AI PK) of person who booked
    private String PersonWhoBooks;
    // Brand and other things associated with the car booked
    private String CarBooked;

    public Booking() {

    }

    public Booking(Long id, String booking, String bookingDate, String returnDate, String personWhoBooks, String carBooked) {
        this.id = id;
        Booking = booking;
        BookingDate = bookingDate;
        ReturnDate = returnDate;
        PersonWhoBooks = personWhoBooks;
        CarBooked = carBooked;
    }

    public String getBooking() {
        return Booking;
    }

    public void setBooking(String booking) {
        Booking = booking;
    }

    public String getBookingDate() {
        return BookingDate;
    }

    public void setBookingDate(String bookingDate) {
        BookingDate = bookingDate;
    }

    public String getReturnDate() {
        return ReturnDate;
    }

    public void setReturnDate(String returnDate) {
        ReturnDate = returnDate;
    }

    public String getPersonWhoBooks() {
        return PersonWhoBooks;
    }

    public void setPersonWhoBooks(String personWhoBooks) {
        PersonWhoBooks = personWhoBooks;
    }

    public String getCarBooked() {
        return CarBooked;
    }

    public void setCarBooked(String carBooked) {
        CarBooked = carBooked;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
