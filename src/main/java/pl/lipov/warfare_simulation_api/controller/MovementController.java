package pl.lipov.warfare_simulation_api.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.lipov.warfare_simulation_api.dto.MovementRequestDto;
import pl.lipov.warfare_simulation_api.dto.MovementResponseDto;
import pl.lipov.warfare_simulation_api.mapper.MovementMapper;
import pl.lipov.warfare_simulation_api.model.Movement;
import pl.lipov.warfare_simulation_api.model.Unit;
import pl.lipov.warfare_simulation_api.service.MovementService;
import pl.lipov.warfare_simulation_api.service.UnitService;
import pl.lipov.warfare_simulation_api.util.GeometryUtils;

import java.util.List;

@RestController
@RequestMapping("/api/movements")
public class MovementController {

    private final MovementService movementService;
    private final UnitService unitService;

    public MovementController(
            MovementService movementService,
            UnitService unitService
    ) {
        this.movementService = movementService;
        this.unitService = unitService;
    }

    @PostMapping
    public ResponseEntity<MovementResponseDto> saveMovement(@RequestBody MovementRequestDto movementRequest) {
        Unit unit = unitService.findById(movementRequest.getUnitId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unit not found"));

        Movement movement;
        try {
            movement = MovementMapper.toMovement(movementRequest, unit);
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }

        Movement savedMovement = movementService.save(movement);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(MovementMapper.toMovementResponseDto(savedMovement));
    }

    @PreAuthorize("hasAuthority('SCOPE_read:movements')")
    @GetMapping
    public List<MovementResponseDto> findAll() {
        return MovementMapper.toMovementResponseDtoList(movementService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovementResponseDto> getMovementById(@PathVariable Long id) {
        Movement movement = movementService
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movement not found"));
        return ResponseEntity.ok(MovementMapper.toMovementResponseDto(movement));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovementResponseDto> updateMovement(
            @PathVariable Long id,
            @Valid @RequestBody MovementRequestDto movementRequest
    ) {
        Movement existingMovement = movementService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movement not found"));

        Unit unit = unitService.findById(movementRequest.getUnitId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unit not found"));

        existingMovement.setStartTimestamp(movementRequest.getStartTimestamp());
        existingMovement.setEndTimestamp(movementRequest.getEndTimestamp());
        existingMovement.setPath(GeometryUtils.parseMultiLineWkt(movementRequest.getPathWKT()));
        existingMovement.setUnit(unit);

        Movement updatedMovement = movementService.save(existingMovement);
        return ResponseEntity.ok(MovementMapper.toMovementResponseDto(updatedMovement));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovementById(@PathVariable Long id) {
        movementService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
