package com.example.enterprisefall.entity;

import com.example.enterprisefall.service.BookingService;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String Booking;
    @Column
    private LocalDate BookingDate;
    @Column
    private Date ReturnDate;

    // ID of car booked

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


    public Booking() {

    }

    public Booking(Long id, String booking, LocalDate bookingDate, Date returnDate) {
        this.id = id;
        Booking = booking;
        BookingDate = bookingDate;
        ReturnDate = returnDate;


    }

    public LocalDate getBookingDate() {
        return BookingDate;
    }

    public void setBookingDate(LocalDate bookingEndDate) {
        BookingDate = bookingEndDate;
    }

    public Long getId() {
        return id;
    }

    public void setBookingEndDate(LocalDate bookingDate){
        this.BookingDate = bookingDate;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
