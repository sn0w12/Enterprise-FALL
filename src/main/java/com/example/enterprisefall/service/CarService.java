

package com.example.enterprisefall.service;

import com.example.enterprisefall.entity.Car;
import com.example.enterprisefall.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.log4j.Logger;

import java.util.List;
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
}
