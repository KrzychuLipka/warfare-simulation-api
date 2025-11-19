package pl.lipov.warfare_simulation_api.service;

import org.springframework.stereotype.Service;
import pl.lipov.warfare_simulation_api.model.Movement;
import pl.lipov.warfare_simulation_api.repository.MovementRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

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
        return StreamSupport
                .stream(repo.findAll().spliterator(), false)
                .toList();
    }

    public Optional<Movement> findById(Long id) {
        return repo.findById(id);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
