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

//    private Car car;
//    private Customer customer;

    // @OneToOne to their ID

    public Booking() {

    }

    public Booking(Long id, String booking, Date bookingDate, Date returnDate, Car car, Customer customer) {
        this.id = id;
        Booking = booking;
        BookingDate = bookingDate;
        ReturnDate = returnDate;
//        this.car = car;
//        this.customer = customer;
    }

    public String getBooking() {
        return Booking;
    }

//    public Car getCar() {
//        return car;
//    }
//
//    public void setCar(Car car) {
//        this.car = car;
//    }
//
//    public Customer getCustomer() {
//        return customer;
//    }

//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }

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



//    public String getCarBooked() {
//        return CarBooked;
//    }
//
//    public void setCarBooked(String carBooked) {
//        CarBooked = carBooked;
//    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
