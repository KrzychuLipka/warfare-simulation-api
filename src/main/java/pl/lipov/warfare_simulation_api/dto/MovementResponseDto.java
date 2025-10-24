package pl.lipov.warfare_simulation_api.dto;

import java.time.Instant;

public class MovementResponseDto {

    private Long id;
    private Instant startTimestamp;
    private Instant endTimestamp;
    private String pathWKT;
    private Long unitId;

    public MovementResponseDto() {
    }

    public MovementResponseDto(
            Long id,
            Instant startTimestamp,
            Instant endTimestamp,
            String pathWKT,
            Long unitId
    ) {
        this.id = id;
        this.startTimestamp = startTimestamp;
        this.endTimestamp = endTimestamp;
        this.pathWKT = pathWKT;
        this.unitId = unitId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
