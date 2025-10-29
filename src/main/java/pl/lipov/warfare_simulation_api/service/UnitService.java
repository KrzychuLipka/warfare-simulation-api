package pl.lipov.warfare_simulation_api.service;

import org.springframework.stereotype.Service;
import pl.lipov.warfare_simulation_api.model.Unit;
import pl.lipov.warfare_simulation_api.repository.UnitRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UnitService {

    private final UnitRepository repo;

    public UnitService(UnitRepository repo) {
        this.repo = repo;
    }

    public Unit save(Unit unit) {
        return repo.save(unit);
    }

    public List<Unit> getAll() {
        return repo.findAll();
    }

    public Optional<Unit> findById(Long id) {
        return repo.findById(id);
    }

    public List<Unit> filter(
            String name,
            String type,
            String faction,
            String status
    ) {
        return repo.filter(name, type, faction, status);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
