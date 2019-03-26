/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.services;

import edu.eci.models.Car;
import edu.eci.persistences.repositories.ICarRepository;
import edu.eci.services.contracts.ICarServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author carloscl
 */
@Component
public class CarServices implements ICarServices {

    @Autowired
    @Qualifier("CarMemoryRepository")
    private ICarRepository carRepository;

    @Override
    public List<Car> getAll() {
        return carRepository.findAll();
    }

    @Override
    public Car get(String licencePlate) {
        if (carRepository.find(licencePlate) == null) {
            throw new RuntimeException("El carro no existe.");
        }
        return carRepository.find(licencePlate);
    }

    @Override
    public Car create(Car car) {
        if (carRepository.find(car.getLicencePlate()) != null) {
            throw new RuntimeException("El carro ya existe.");
        }
        carRepository.save(car);
        return car;
    }

    @Override
    public Car update(Car car) {
        if (carRepository.find(car.getLicencePlate()) == null) {
            throw new RuntimeException("El carro no existe.");
        }
        carRepository.update(car);
        return car;
    }

    @Override
    public Car delete(String licencePlate) {
        if (carRepository.find(licencePlate) == null) {
            throw new RuntimeException("El carro no existe.");
        }
        Car car = carRepository.find(licencePlate);
        carRepository.delete(car);
        return car;
    }

}
