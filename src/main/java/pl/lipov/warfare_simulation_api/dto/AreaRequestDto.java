package pl.lipov.warfare_simulation_api.dto;

import java.time.Instant;

public record AreaRequestDto(
        Instant since,
        String areaWkt
) {
}
