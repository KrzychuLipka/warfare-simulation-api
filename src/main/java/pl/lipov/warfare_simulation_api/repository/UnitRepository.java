package pl.lipov.warfare_simulation_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.lipov.warfare_simulation_api.model.Unit;

import java.util.List;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {

    List<Unit> findByName(String name);
    List<Unit> findByType(String type);
    List<Unit> findByFaction(String faction);
    List<Unit> findByStatus(String status);
}
