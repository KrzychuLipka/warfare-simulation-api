package pl.lipov.warfare_simulation_api.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import pl.lipov.warfare_simulation_api.model.UnitFaction;
import pl.lipov.warfare_simulation_api.model.UnitStatus;
import pl.lipov.warfare_simulation_api.model.UnitType;

public class UnitRequestDto {

    @NotBlank
    private String name;
    @NotNull
    private UnitType type;
    @NotNull
    private UnitFaction faction;
    @NotNull
    @Min(value = 1)
    @Max(value = 100)
    private Integer strength;
    @NotNull
    private UnitStatus status;

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
}
