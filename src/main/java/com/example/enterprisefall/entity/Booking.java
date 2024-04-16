package com.example.enterprisefall.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String Booking;
    private Date BookingDate;
    private Date ReturnDate;

    // @OneToOne to their ID


    private String PersonWhoBooks;
    // ID of car booked
    private String CarBooked;

    @JoinColumn
    private Customer customer;

    public Booking() {

    }

    public Booking(Long id, String booking, Date bookingDate, Date returnDate, String personWhoBooks, String carBooked) {
        this.id = id;
        Booking = booking;
        BookingDate = bookingDate;
        ReturnDate = returnDate;
        PersonWhoBooks = personWhoBooks;
        CarBooked = carBooked;
    }

    public String getAllBookings() {
        return Booking;
    }

    public void setBooking(String booking) {
        Booking = booking;
    }

    public Date getBookingDate() {
        return BookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        BookingDate = bookingDate;
    }

    public Date getReturnDate() {
        return ReturnDate;
    }

    public void setReturnDate(Date returnDate) {
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
