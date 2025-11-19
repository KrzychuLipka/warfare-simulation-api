package pl.lipov.warfare_simulation_api.repository;

import org.springframework.data.repository.CrudRepository;
import pl.lipov.warfare_simulation_api.model.Movement;

public interface MovementRepository extends CrudRepository<Movement, Long> {
}
