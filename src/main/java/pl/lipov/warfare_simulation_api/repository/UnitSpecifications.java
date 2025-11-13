package pl.lipov.warfare_simulation_api.repository;

import org.springframework.data.jpa.domain.Specification;
import pl.lipov.warfare_simulation_api.model.Unit;
import pl.lipov.warfare_simulation_api.model.UnitFaction;
import pl.lipov.warfare_simulation_api.model.UnitType;

public class UnitSpecifications {

    public static Specification<Unit> hasFaction(UnitFaction faction) {
        return ((root, query, cb) -> cb.equal(root.get("faction"), faction));
    }

    public static Specification<Unit> hasType(UnitType type) {
        return ((root, query, cb) -> cb.equal(root.get("type"), type));
    }
}
