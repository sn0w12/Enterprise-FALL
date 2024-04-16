

package com.example.enterprisefall.service;

import com.example.enterprisefall.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.log4j.Logger;

@Service
public class CarService {

  private Logger logger = Logger.getLogger(CarService.class);
  private CarRepository carRepository;

  @Autowired
  public CarService(CarRepository carRepository) {
    this.carRepository = carRepository;
  }

  // METODER
}
