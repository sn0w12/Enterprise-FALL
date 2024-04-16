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
        logger.info("\nDeleted car with ID: " + id + "\n");

    // Update Car
    public String updateCar(Car carToBeUpdated) {
        Optional<Car> optionalCar = carRepository.findById(carToBeUpdated.getId());

        StringBuilder updateMessage = new StringBuilder();

        if (optionalCar.isPresent()) {
            Car existingCar = optionalCar.get();

            updateMessage.append("\nAdmin updated Car with id: " + existingCar.getId() + "\n");

            if (carToBeUpdated.getPricePerDay() != null || carToBeUpdated.getPricePerDay() == existingCar.getPricePerDay()) {
                existingCar.setPricePerDay(carToBeUpdated.getPricePerDay());
                updateMessage.append("Price per day changed to: " + existingCar.getPricePerDay() + "\n");
            }

            if (carToBeUpdated.getBrand() != null && (!carToBeUpdated.getBrand().isEmpty() || !carToBeUpdated.getBrand().contains(existingCar.getBrand()))) {
                existingCar.setBrand(carToBeUpdated.getBrand());
                updateMessage.append("Manifacturer changed to: " + existingCar.getBrand() + "\n");
            }

            if (carToBeUpdated.getModel() != null && (!carToBeUpdated.getModel().isEmpty() || !carToBeUpdated.getModel().contains(existingCar.getModel()))) {
                existingCar.setModel(carToBeUpdated.getModel());
                updateMessage.append("Model changed to: " + existingCar.getModel() + "\n");
            }

            if (carToBeUpdated.getRegistrationNumber() != null && (!carToBeUpdated.getRegistrationNumber().isEmpty() || !carToBeUpdated.getRegistrationNumber().contains(existingCar.getRegistrationNumber()))) {
                existingCar.setRegistrationNumber(carToBeUpdated.getRegistrationNumber());
                updateMessage.append("Reg Nr changed to: " + existingCar.getRegistrationNumber() + "\n");
            }

            if (carToBeUpdated.getIsBooked() != null) {
                if (!existingCar.getIsBooked() && carToBeUpdated.getIsBooked()) {
                    existingCar.setIsBooked(true);
                    updateMessage.append("Changed availability to: true\n");
                } else if (existingCar.getIsBooked() && !carToBeUpdated.getIsBooked()) {
                    existingCar.setIsBooked(false);
                    updateMessage.append("Changed availability to: false\n");
                }
            }

            logger.info(updateMessage);
            return updateMessage.toString();
        } else {

            logger.error("\nERROR: Car not found with ID " + carToBeUpdated.getId() + "\n");
            return "ERROR: Car not found";
        }
    }
}
