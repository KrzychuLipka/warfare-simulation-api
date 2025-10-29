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

    @Query("""
                SELECT u FROM Unit u
                WHERE (:name IS NULL OR :name = '' OR LOCATE(LOWER(:name), LOWER(u.name)) > 0)
                  AND (:type IS NULL OR :type = '' OR LOCATE(LOWER(:type), LOWER(u.type)) > 0)
                  AND (:faction IS NULL OR :faction = '' OR LOCATE(LOWER(:faction), LOWER(u.faction)) > 0)
                  AND (:status IS NULL OR :status = '' OR LOCATE(LOWER(:status), LOWER(u.status)) > 0)
            """)
    List<Unit> filter(
            @Param("name") @Nullable String name,
            @Param("type") @Nullable String type,
            @Param("faction") @Nullable String faction,
            @Param("status") @Nullable String status
    );
}
