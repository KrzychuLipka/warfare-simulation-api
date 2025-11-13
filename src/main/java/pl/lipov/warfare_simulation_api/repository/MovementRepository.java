package pl.lipov.warfare_simulation_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.lipov.warfare_simulation_api.model.Movement;
import pl.lipov.warfare_simulation_api.model.UnitFaction;
import pl.lipov.warfare_simulation_api.model.UnitStatus;

import java.time.Instant;
import java.util.List;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {

    List<Movement> findByStartTimestampBetween(Instant from, Instant to);

    List<Movement> findByEndTimestampBetween(Instant from, Instant to);

    @Query("SELECT m FROM Movement m JOIN m.unit u WHERE u.faction = :faction")
    List<Movement> findByUnitFaction(@Param("faction") UnitFaction faction);

    @Query(value = """
            SELECT DISTINCT m.*
            FROM movement m JOIN unit u ON m.unit_id = u.id
            WHERE u.status = :status AND m.start_timestamp > :from
            """, nativeQuery = true)
    List<Movement> findRecentMovementsByUnitStatus(
            @Param("status") UnitStatus status,
            @Param("from") Instant from
    );
}
