package com.example.model;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.*;
import io.micronaut.data.model.DataType;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.Column;
import lombok.NoArgsConstructor;
import net.postgis.jdbc.PGgeometry;
import net.postgis.jdbc.geometry.Point;

@Serdeable
@MappedEntity
@NoArgsConstructor
public class MyEntity {

    @Id
    @GeneratedValue(GeneratedValue.Type.SEQUENCE)
    Long id;


    @Nullable
    @Column(name="gis_location")
    @TypeDef(type = DataType.OBJECT)
    @MappedProperty(converter = PointAttributeConverter.class, type = DataType.OBJECT,
            converterPersistedType = PGgeometry.class)
    Point location;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Nullable
    @TypeDef(type = DataType.OBJECT)
    public Point getLocation() {
        return location;
    }

    public void setLocation(@Nullable Point location) {
        this.location = location;
    }
}
