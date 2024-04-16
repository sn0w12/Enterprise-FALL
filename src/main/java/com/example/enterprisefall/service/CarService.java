package com.example.enterprisefall.service;

import com.example.enterprisefall.entity.Car;
import com.example.enterprisefall.repository.CarRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService {

    private Logger logger = Logger.getLogger(CarService.class);
    private CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    // Method to fetch all available (non-booked) cars
    public List<Car> findAllAvailableCars() {
        return carRepository.findAll().stream()
                .filter(car -> !car.getIsBooked())
                .collect(Collectors.toList());
    }

    // Method to add a new car
    public Car addCar(Car newCar) {
        Optional<Car> existingCar = carRepository.findByRegistrationNumber(newCar.getRegistrationNumber());
        if (existingCar.isPresent()) {
            logger.error("Attempt to add a duplicate car with registration number: " + newCar.getRegistrationNumber());
            throw new IllegalArgumentException("Car with registration number " + newCar.getRegistrationNumber() + " already exists.");
        }

        Car savedCar = carRepository.save(newCar);
        logger.info("\nAdded new car: " + savedCar + "\n");
        return savedCar;
    }

    public void deleteCarById(int id) {
        carRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Car with ID " + id + " does not exist."));
        carRepository.deleteById(id);
        logger.info("Deleted car with ID: " + id);
    }
}
