package pl.lipov.warfare_simulation_api.service;

import org.springframework.stereotype.Service;
import pl.lipov.warfare_simulation_api.model.Movement;
import pl.lipov.warfare_simulation_api.model.UnitFaction;
import pl.lipov.warfare_simulation_api.model.UnitStatus;
import pl.lipov.warfare_simulation_api.repository.MovementRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class MovementService {

    private final MovementRepository repo;

    public MovementService(MovementRepository repo) {
        this.repo = repo;
    }

    public Movement save(Movement movement) {
        return repo.save(movement);
    }

    public List<Movement> findAll() {
        return repo.findAll();
    }

    public Optional<Movement> findById(Long id) {
        return repo.findById(id);
    }

    public List<Movement> findByStartTimestampBetween(Instant start, Instant end) {
        return repo.findByStartTimestampBetween(start, end);
    }

    public List<Movement> findByEndTimestampBetween(Instant start, Instant end) {
        return repo.findByEndTimestampBetween(start, end);
    }

    public List<Movement> findByUnitFaction(UnitFaction faction) {
        return repo.findByUnitFaction(faction);
    }

    public List<Movement> findRecentMovementsByUnitStatus(
            UnitStatus status,
            Instant from
    ) {
        return repo.findRecentMovementsByUnitStatus(status, from);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
