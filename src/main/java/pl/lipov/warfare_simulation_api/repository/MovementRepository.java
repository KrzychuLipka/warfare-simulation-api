package pl.lipov.warfare_simulation_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.lipov.warfare_simulation_api.model.Movement;
import pl.lipov.warfare_simulation_api.model.Unit;

import java.time.Instant;
import java.util.List;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {

    List<Movement> findByStartTimestampBetween(Instant from, Instant to);
    List<Movement> findByEndTimestampBetween(Instant from, Instant to);
}
