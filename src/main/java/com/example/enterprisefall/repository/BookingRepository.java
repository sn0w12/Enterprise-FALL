package com.example.enterprisefall.repository;

import com.example.enterprisefall.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findById(Long id);

    void deleteById(Long id);

    @Override
    Booking save(Booking booking);
}
