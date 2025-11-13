package pl.lipov.warfare_simulation_api.service;

import org.locationtech.jts.geom.Polygon;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import pl.lipov.warfare_simulation_api.dto.UnitSummary;
import pl.lipov.warfare_simulation_api.model.Unit;
import pl.lipov.warfare_simulation_api.model.UnitFaction;
import pl.lipov.warfare_simulation_api.model.UnitType;
import pl.lipov.warfare_simulation_api.repository.UnitRepository;
import pl.lipov.warfare_simulation_api.repository.UnitSpecifications;
import pl.lipov.warfare_simulation_api.repository.unitMovementRepository.UnitMovementRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class UnitService {

    private final UnitRepository unitRepository;
    private final UnitMovementRepository unitMovementRepository;

    public UnitService(
            UnitRepository unitRepository,
            UnitMovementRepository unitMovementRepository
    ) {
        this.unitRepository = unitRepository;
        this.unitMovementRepository = unitMovementRepository;
    }

    public Unit save(Unit unit) {
        return unitRepository.save(unit);
    }

    public List<Unit> findAll() {
        return unitRepository.findAll();
    }

    public List<Unit> findByFaction(UnitFaction faction) {
        return unitRepository.findAll(UnitSpecifications.hasFaction(faction));
    }

    //    public List<Unit> findByFaction(UnitFaction faction) {
//        return unitRepository.findByFaction(faction);
//    }

    public List<Unit> findEnemyAirUnits() {
        Specification<Unit> spec = Specification.allOf(
                UnitSpecifications.hasType(UnitType.AIR),
                UnitSpecifications.hasFaction(UnitFaction.REST_OF_WORLD)
        );
        return unitRepository.findAll(spec);
    }

    public List<Unit> findEnemyAirUnitsWithRecentMovementsInArea(
            Instant since,
            Polygon area
    ) {
        return unitMovementRepository.findEnemyAirUnitsWithRecentMovementsInArea(since, area);
    }

    public Optional<Unit> findById(Long id) {
        return unitRepository.findById(id);
    }

    public List<Unit> filter(
            String name,
            String type,
            String faction,
            String status
    ) {
        return unitRepository.filter(name, type, faction, status);
    }

    public List<Unit> findByName(String name) {
        return unitRepository.findByByName(name);
    }

    public List<UnitSummary> findUnitSummaries() {
        return unitRepository.getUnitSummaries();
    }

    public void deleteById(Long id) {
        unitRepository.deleteById(id);
    }
}
