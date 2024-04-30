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

    public List<Car> findAllAvailableCars() {
        return carRepository.findAll().stream()
                .filter(car -> !car.getIsBooked())
                .collect(Collectors.toList());
    }

    public List<Car> findAllCars() {
        return carRepository.findAll();
    }

    public Car addCar(Car newCar) {
        Optional<Car> existingCar = carRepository.findByRegistrationNumber(newCar.getRegistrationNumber());
        if (existingCar.isPresent()) {
            Car car = existingCar.get();
            logger.error("\nAdmin attempted to add a duplicate car with registration number: " + car.getRegistrationNumber() + "\n");
            throw new IllegalArgumentException("Car with registration number " + car.getRegistrationNumber() + " already exists.");
        }

        Car savedCar = carRepository.save(newCar);
        logger.info("\nAdmin added new car: " + savedCar.getModel() + "\n");
        return savedCar;
    }

    public String deleteCarById(int id) {
        Optional<Car> optionalCar = carRepository.findById(id);

        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();
            logger.info("\nAdmin deleted car with ID: " + id + "\n");
            carRepository.delete(car);
            return "Car " + car.getId() + " deleted!";
        } else {
            logger.error("\nERROR: Car not found with ID " + id + "\n");
            return "ERROR: Car not found";
        }
    }

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
            carRepository.save(existingCar);
            return updateMessage.toString();
        } else {

            logger.error("\nERROR: Car not found with ID " + carToBeUpdated.getId() + "\n");
            return "ERROR: Car not found";
        }
    }
}
