package edu.eci.services.contracts;

import edu.eci.models.Car;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author carloscl
 */
@Service
public interface ICarServices {

    List<Car> getAll();

    Car get(String licencePlate);

    Car create(Car car);

    Car update(Car car);

    Car delete(String licencePlate);
}
