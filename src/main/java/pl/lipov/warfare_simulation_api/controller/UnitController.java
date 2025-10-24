package pl.lipov.warfare_simulation_api.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.lipov.warfare_simulation_api.dto.UnitRequestDto;
import pl.lipov.warfare_simulation_api.dto.UnitResponseDto;
import pl.lipov.warfare_simulation_api.mapper.UnitMapper;
import pl.lipov.warfare_simulation_api.model.Unit;
import pl.lipov.warfare_simulation_api.service.UnitService;

import java.util.List;

@RestController
@RequestMapping("/api/units")
public class UnitController {

    private final UnitService service;

    public UnitController(UnitService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UnitResponseDto> saveUnit(@Valid @RequestBody UnitRequestDto unitRequest) {
        Unit savedUnit = service.save(UnitMapper.toUnit(unitRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(UnitMapper.toUnitResponseDto(savedUnit));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnitResponseDto> getUnit(@PathVariable Long id) {
        Unit unit = service.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unit not found"));
        return ResponseEntity.ok(UnitMapper.toUnitResponseDto(unit));
    }

    @GetMapping
    public List<UnitResponseDto> getAllUnits() {
        return UnitMapper.toUnitResponseDtoList(service.getAll());
    }

    @GetMapping("/filterByName")
    public List<UnitResponseDto> filterUnitByName(String name) {
        return UnitMapper.toUnitResponseDtoList(service.filterByName(name));
    }

    @GetMapping("/filterByType")
    public List<UnitResponseDto> filterUnitByType(String type) {
        return UnitMapper.toUnitResponseDtoList(service.filterByType(type));
    }

    @GetMapping("/filterByFaction")
    public List<UnitResponseDto> filterUnitByFaction(String faction) {
        return UnitMapper.toUnitResponseDtoList(service.filterByFaction(faction));
    }

    @GetMapping("/filterByStatus")
    public List<UnitResponseDto> filterUnitByStatus(String status) {
        return UnitMapper.toUnitResponseDtoList(service.filterByStatus(status));
    }

    @GetMapping("/filter")
    public List<UnitResponseDto> filterUnit(
            String name,
            String type,
            String faction,
            String status
    ) {
        return UnitMapper.toUnitResponseDtoList(service.filter(name, type, faction, status));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnitResponseDto> updateUnit(
            @PathVariable Long id,
            @Valid @RequestBody Unit unit
    ) {
        unit.setId(id);
        Unit updatedUnit = service.save(unit);
        return ResponseEntity.ok(UnitMapper.toUnitResponseDto(updatedUnit));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUnitById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
