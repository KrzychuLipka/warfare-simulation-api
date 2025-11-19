package pl.lipov.warfare_simulation_api.repository.unitMovementRepository;

import org.locationtech.jts.geom.Polygon;
import pl.lipov.warfare_simulation_api.model.Unit;

import java.time.Instant;
import java.util.List;

public interface UnitMovementRepository {

    List<Unit> findEnemyAirUnitsWithRecentMovementsInArea(
            Instant since,
            Polygon area
    );
}
