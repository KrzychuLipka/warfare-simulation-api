package pl.lipov.warfare_simulation_api.repository;

import org.springframework.data.repository.CrudRepository;
import pl.lipov.warfare_simulation_api.model.Unit;

public interface UnitRepository extends CrudRepository<Unit, Long> {
}
