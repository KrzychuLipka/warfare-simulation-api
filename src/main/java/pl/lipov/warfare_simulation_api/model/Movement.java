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
    private Instant startTimestamp;
    private Instant endTimestamp;
    @Column(columnDefinition = "geometry(MultiLineString,2180)")
    private MultiLineString path;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "unit_id", nullable = false)
    private Unit unit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(Instant startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public Instant getEndTimestamp() {
        return endTimestamp;
    }

    public void setEndTimestamp(Instant endTimestamp) {
        this.endTimestamp = endTimestamp;
    }

    public MultiLineString getPath() {
        return path;
    }

    public void setPath(MultiLineString path) {
        this.path = path;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
