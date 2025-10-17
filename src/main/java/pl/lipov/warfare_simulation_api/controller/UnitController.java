package pl.lipov.warfare_simulation_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping
    public List<Unit> getAllUnits() {
        return service.getAllUnits();
    }

    @PostMapping
    public ResponseEntity<Unit> saveUnit(@RequestBody Unit unit) {
        Unit savedUnit = service.saveUnit(unit);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUnit);
    }
}
