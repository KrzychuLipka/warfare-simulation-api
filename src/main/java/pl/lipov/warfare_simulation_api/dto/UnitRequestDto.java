package pl.lipov.warfare_simulation_api.dto;

import jakarta.validation.constraints.*;

public class UnitRequestDto {

    @NotBlank
    private String name;
    @NotBlank
    @Pattern(regexp = "LAND|AIR|NAVY")
    private String type;
    @NotBlank
    @Pattern(regexp = "POLAND|REST_OF_WORLD")
    private String faction;
    @NotNull
    @Min(value = 1)
    @Max(value = 100)
    private Integer strength;
    @NotBlank
    @Pattern(regexp = "ACTIVE|INACTIVE|DESTROYED")
    private String status;

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
}
