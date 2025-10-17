package pl.lipov.warfare_simulation_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.lipov.warfare_simulation_api.model.Movement;
import pl.lipov.warfare_simulation_api.model.Unit;

import java.time.Instant;
import java.util.List;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {

    List<Movement> findByUnit(Unit unit);
    List<Movement> findByUnitId(Long unitId);
    List<Movement> findByTimestampBetween(Instant start, Instant end);
    Movement findFirstByUnitOrderByTimestampDesc(Unit unit);
}
