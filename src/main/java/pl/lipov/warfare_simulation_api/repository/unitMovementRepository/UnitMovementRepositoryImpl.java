package pl.lipov.warfare_simulation_api.repository.unitMovementRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.locationtech.jts.geom.Polygon;
import org.springframework.stereotype.Repository;
import pl.lipov.warfare_simulation_api.model.Unit;
import pl.lipov.warfare_simulation_api.model.UnitFaction;
import pl.lipov.warfare_simulation_api.model.UnitType;

import java.time.Instant;
import java.util.List;

@Repository
public class UnitMovementRepositoryImpl implements UnitMovementRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Unit> findEnemyAirUnitsWithRecentMovementsInArea(
            Instant since,
            Polygon area
    ) {
        return entityManager.createQuery(
                        """
                                SELECT DISTINCT u
                                FROM Unit u JOIN u.movements m
                                WHERE u.faction = :faction
                                  AND u.type = :type
                                  AND m.startTimestamp >= :since
                                  AND function('ST_Intersects', m.path, :area) = true""", Unit.class
                )
                .setParameter("faction", UnitFaction.REST_OF_WORLD)
                .setParameter("type", UnitType.AIR)
                .setParameter("since", since)
                .setParameter("area", area)
                .getResultList();
    }
}
