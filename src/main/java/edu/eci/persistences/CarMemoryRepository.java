/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.persistences;

import edu.eci.models.Car;
import edu.eci.persistences.repositories.ICarRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static java.util.stream.Collectors.toList;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author carloscl
 */
@Component
@Qualifier("CarMemoryRepository")
public class CarMemoryRepository implements ICarRepository {

    public static List<Car> carsContainer;

    public static List<Car> getContainer() {
        if (CarMemoryRepository.carsContainer == null) {
            CarMemoryRepository.carsContainer = new ArrayList<>();
        }
        return CarMemoryRepository.carsContainer;
    }

    @Override
    public List<Car> findAll() {
        return CarMemoryRepository.getContainer();
    }

    @Override
    public Car find(String licencePlate) {
        Optional<Car> answer = CarMemoryRepository.getContainer()
                .stream()
                .filter(c -> licencePlate.equals(c.getLicencePlate()))
                .findFirst();
        return answer.isPresent() ? answer.get() : null;
    }

    @Override
    public String save(Car entity) {
        CarMemoryRepository.getContainer().add(entity);
        return entity.getLicencePlate();
    }

    @Override
    public void update(Car entity) {
        CarMemoryRepository.carsContainer = CarMemoryRepository.getContainer()
                .stream()
                .map(c -> c.getLicencePlate().equals(entity.getLicencePlate()) ? entity : c)
                .collect(toList());
    }

    @Override
    public void delete(Car o) {
        CarMemoryRepository.carsContainer = CarMemoryRepository.getContainer()
                .stream()
                .filter(c -> !c.getLicencePlate().equals(o.getLicencePlate()))
                .collect(toList());
    }

    @Override
    public void remove(Long id) {
        CarMemoryRepository.carsContainer = CarMemoryRepository.getContainer()
                .stream()
                .filter(c -> !c.getLicencePlate().equals(id))
                .collect(toList());
    }
}
