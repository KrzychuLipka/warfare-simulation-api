package pl.lipov.warfare_simulation_api.mapper;

import org.locationtech.jts.geom.MultiLineString;
import pl.lipov.warfare_simulation_api.dto.MovementRequestDto;
import pl.lipov.warfare_simulation_api.dto.MovementResponseDto;
import pl.lipov.warfare_simulation_api.model.Movement;
import pl.lipov.warfare_simulation_api.model.Unit;
import pl.lipov.warfare_simulation_api.util.GeometryUtils;

import java.util.List;

public class MovementMapper {

    public static MovementResponseDto toMovementResponseDto(Movement movement) {
        return new MovementResponseDto(
                movement.getId(),
                movement.getStartTimestamp(),
                movement.getEndTimestamp(),
                GeometryUtils.toWkt(movement.getPath()),
                movement.getUnit().getId()
        );
    }

    public static List<MovementResponseDto> toMovementResponseDtoList(List<Movement> movements) {
        return movements.stream()
                .map(MovementMapper::toMovementResponseDto)
                .toList();
    }

    public static Movement toMovement(
            MovementRequestDto movementRequest,
            Unit unit
    ) {
        Movement movement = new Movement();
        movement.setStartTimestamp(movementRequest.getStartTimestamp());
        movement.setEndTimestamp(movementRequest.getEndTimestamp());
        MultiLineString path = GeometryUtils.parseWkt(movementRequest.getPathWKT());
        movement.setPath(path);
        movement.setUnit(unit);
        return movement;
    }
}
