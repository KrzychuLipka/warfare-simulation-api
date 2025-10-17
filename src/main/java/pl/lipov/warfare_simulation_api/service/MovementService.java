package pl.lipov.warfare_simulation_api.service;

import org.springframework.stereotype.Service;
import pl.lipov.warfare_simulation_api.model.Movement;
import pl.lipov.warfare_simulation_api.model.Unit;
import pl.lipov.warfare_simulation_api.repository.MovementRepository;

import java.time.Instant;
import java.util.List;

@Service
public class MovementService {

    private final MovementRepository repo;

    public MovementService(MovementRepository repo) {
        this.repo = repo;
    }

    public List<Movement> getAllMovements() {
        return repo.findAll();
    }

    List<Movement> getMovementsByUnit(Unit unit) {
        return repo.findByUnit(unit);
    }

    List<Movement> getMovementsByUnitId(Long unitId) {
        return repo.findByUnitId(unitId);
    }

    List<Movement> getMovementsByTimestampBetween(Instant start, Instant end) {
        return repo.findByTimestampBetween(start, end);
    }

    Movement getLastUnitMovement(Unit unit) {
        return repo.findFirstByUnitOrderByTimestampDesc(unit);
    }

    public Movement saveMovement(Movement movement) {
        return repo.save(movement);
    }

    void deleteMovement(Movement movement) {
        repo.delete(movement);
    }
}
