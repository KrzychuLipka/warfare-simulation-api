package pl.lipov.warfare_simulation_api.dto;

import pl.lipov.warfare_simulation_api.model.UnitFaction;
import pl.lipov.warfare_simulation_api.model.UnitStatus;
import pl.lipov.warfare_simulation_api.model.UnitType;

import java.util.List;

public class UnitResponseDto {

    private Long id;
    private String name;
    private UnitType type;
    private UnitFaction faction;
    private Integer strength;
    private UnitStatus status;
    private List<Long> movementIds;

    private UnitResponseDto() {
    }

    public UnitResponseDto(
            Long id,
            String name,
            UnitType type,
            UnitFaction faction,
            Integer strength,
            UnitStatus status,
            List<Long> movementIds
    ) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.faction = faction;
        this.strength = strength;
        this.status = status;
        this.movementIds = movementIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UnitType getType() {
        return type;
    }

    public void setType(UnitType type) {
        this.type = type;
    }

    public UnitFaction getFaction() {
        return faction;
    }

    public void setFaction(UnitFaction faction) {
        this.faction = faction;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public UnitStatus getStatus() {
        return status;
    }

    public void setStatus(UnitStatus status) {
        this.status = status;
    }

    public List<Long> getMovementIds() {
        return movementIds;
    }

    public void setMovementIds(List<Long> movementIds) {
        this.movementIds = movementIds;
    }
}
