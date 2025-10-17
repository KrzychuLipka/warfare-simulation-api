package pl.lipov.warfare_simulation_api.service;

import org.springframework.stereotype.Service;
import pl.lipov.warfare_simulation_api.model.Unit;
import pl.lipov.warfare_simulation_api.repository.UnitRepository;

import java.util.List;

@Service
public class UnitService {

    private final UnitRepository repo;

    public UnitService(UnitRepository repo) {
        this.repo = repo;
    }

    public List<Unit> getAllUnits() {
        return repo.findAll();
    }

    List<Unit> getUnitsByName(String name) {
        return repo.findByName(name);
    }

    List<Unit> getUnitsByType(String type) {
        return repo.findByType(type);
    }

    List<Unit> getUnitsByFaction(String faction) {
        return repo.findByFaction(faction);
    }

    List<Unit> getUnitsByStatus(String status) {
        return repo.findByStatus(status);
    }

    public Unit saveUnit(Unit unit) {
        return repo.save(unit);
    }

    public void deleteUnit(Unit unit) {
        repo.delete(unit);
    }
}
