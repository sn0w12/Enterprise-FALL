package com.example.enterprisefall.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String booking;

    @Column
    private LocalDate bookingDate;

    @Column
    private LocalDate returnDate;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    private LocalDate bookingEndDate;

    public Booking() {
    }

    public Booking(String booking, LocalDate bookingDate, LocalDate returnDate) {
        this.booking = booking;
        this.bookingDate = bookingDate;
        this.returnDate = returnDate;
    }

    public Booking(Customer customer) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBooking() {
        return booking;
    }

    public void setBooking(String booking) {
        this.booking = booking;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setBookingEndDate(LocalDate bookingEndDate) {
        this.bookingDate = bookingEndDate;
    }

    public LocalDate getBookingEndDate() {
        return this.bookingEndDate;
    }
}
