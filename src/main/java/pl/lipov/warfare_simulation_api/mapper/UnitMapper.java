package pl.lipov.warfare_simulation_api.mapper;

import pl.lipov.warfare_simulation_api.dto.UnitRequestDto;
import pl.lipov.warfare_simulation_api.dto.UnitResponseDto;
import pl.lipov.warfare_simulation_api.model.Movement;
import pl.lipov.warfare_simulation_api.model.Unit;

import java.util.List;

public class UnitMapper {

    public static UnitResponseDto toUnitResponseDto(Unit unit) {
        return new UnitResponseDto(
                unit.getId(),
                unit.getName(),
                unit.getType(),
                unit.getFaction(),
                unit.getStrength(),
                unit.getStatus(),
                unit.getMovements()
                        .stream()
                        .map(Movement::getId)
                        .toList()
        );
    }

    public static List<UnitResponseDto> toUnitResponseDtoList(List<Unit> units) {
        return units.stream()
                .map(UnitMapper::toUnitResponseDto)
                .toList();
    }

    public static Unit toUnit(UnitRequestDto unitRequest) {
        Unit unit = new Unit();
        unit.setName(unitRequest.getName());
        unit.setType(unitRequest.getType());
        unit.setFaction(unitRequest.getFaction());
        unit.setStrength(unitRequest.getStrength());
        unit.setStatus(unitRequest.getStatus());
        return unit;
    }
}
