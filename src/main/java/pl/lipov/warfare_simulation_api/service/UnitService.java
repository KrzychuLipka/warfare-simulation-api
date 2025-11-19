package pl.lipov.warfare_simulation_api.service;

import org.springframework.stereotype.Service;
import pl.lipov.warfare_simulation_api.model.Unit;
import pl.lipov.warfare_simulation_api.repository.UnitRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class UnitService {

    private final UnitRepository repo;

    public UnitService(UnitRepository repo) {
        this.repo = repo;
    }

    public Unit save(Unit unit) {
        return repo.save(unit);
    }

    public List<Unit> findAll() {
        return StreamSupport
                .stream(repo.findAll().spliterator(), false)
                .toList();
    }

    public Optional<Unit> findById(Long id) {
        return repo.findById(id);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
