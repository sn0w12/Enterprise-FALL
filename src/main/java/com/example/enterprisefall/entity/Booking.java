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
    @Column
    private Date BookingDate;
    @Column
    private Date ReturnDate;

    // ID of car booked
    @Column
    private String CarBooked;


    @OneToOne
    private Car car;
    @OneToOne
    private Customer customer;
    public Booking() {

    }

    public Booking(Long id, String booking, Date bookingDate, Date returnDate, String carBooked) {
        this.id = id;
        Booking = booking;
        BookingDate = bookingDate;
        ReturnDate = returnDate;
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

    public Date getReturnDate(Date date) {
        return ReturnDate;
    }

    public void setReturnDate(Date returnDate) {
        ReturnDate = returnDate;
    }

    public String getCarBooked() {
        return CarBooked;
    }

    public void setCarBooked(String carBooked) {
        CarBooked = carBooked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
