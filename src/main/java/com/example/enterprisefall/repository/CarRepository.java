package com.example.enterprisefall.repository;

import com.example.enterprisefall.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    Optional<Car> findByRegistrationNumber(String registrationNumber);
}
