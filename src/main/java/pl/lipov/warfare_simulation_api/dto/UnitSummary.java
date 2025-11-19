package pl.lipov.warfare_simulation_api.dto;

import pl.lipov.warfare_simulation_api.model.UnitType;

public record UnitSummary(
        Long id,
        String name,
        UnitType type
) {
}