package pl.lipov.warfare_simulation_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import pl.lipov.warfare_simulation_api.model.Unit;

import java.util.List;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {

    List<Unit> findByName(String name);

    List<Unit> findByType(String type);

    List<Unit> findByFaction(String faction);

    List<Unit> findByStatus(String status);

    @Query("""
            SELECT u FROM Unit u
            WHERE u.name = COALESCE(:name, u.name)
              AND u.type = COALESCE(:type, u.type)
              AND u.faction = COALESCE(:faction, u.faction)
              AND u.status = COALESCE(:status, u.status)
            """)
    List<Unit> filter(
            @Param("name") @Nullable String name,
            @Param("type") @Nullable String type,
            @Param("faction") @Nullable String faction,
            @Param("status") @Nullable String status
    );

}
