package pl.lipov.warfare_simulation_api.dto;

import pl.lipov.warfare_simulation_api.model.Movement;

import java.util.List;

public class UnitResponseDto {

    private Long id;
    private String name;
    private String type;
    private String faction;
    private Integer strength;
    private String status;
    private List<Long> movementIds;

    private UnitResponseDto() {
    }

    public UnitResponseDto(
            Long id,
            String name,
            String type,
            String faction,
            Integer strength,
            String status,
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFaction() {
        return faction;
    }

    public void setFaction(String faction) {
        this.faction = faction;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Long> getMovementIds() {
        return movementIds;
    }

    public void setMovementIds(List<Long> movementIds) {
        this.movementIds = movementIds;
    }
}
