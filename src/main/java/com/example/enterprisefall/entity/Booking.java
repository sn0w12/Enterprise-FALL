package com.example.enterprisefall.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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


    public Booking() {
    }

    public Booking( LocalDate bookingDate, LocalDate returnDate) {

        this.bookingDate = bookingDate;
        this.returnDate = returnDate;
    }

    public Booking(Customer customer) {
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }


}
