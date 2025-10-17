package pl.lipov.warfare_simulation_api.model;

import jakarta.persistence.*;
import org.locationtech.jts.geom.MultiLineString;

import java.time.Instant;

@Entity
@Table(name = "movement")
public class Movement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant timestamp;
    @Column(columnDefinition = "geometry(MultiLineString,2180)")
    private MultiLineString path;
    private Double speed;
    private String direction;
    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public MultiLineString getPath() {
        return path;
    }

    public void setPath(MultiLineString path) {
        this.path = path;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
