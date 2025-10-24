package pl.lipov.warfare_simulation_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public class MovementRequestDto {

    @NotNull
    private Instant startTimestamp;
    @NotNull
    private Instant endTimestamp;
    @NotBlank
    private String pathWKT;
    @NotNull
    private Long unitId;

    public Instant getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(Instant startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public Instant getEndTimestamp() {
        return endTimestamp;
    }

    public void setEndTimestamp(Instant endTimestamp) {
        this.endTimestamp = endTimestamp;
    }

    public String getPathWKT() {
        return pathWKT;
    }

    public void setPathWKT(String pathWKT) {
        this.pathWKT = pathWKT;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }
}
