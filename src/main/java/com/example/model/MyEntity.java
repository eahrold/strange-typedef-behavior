package com.example.model;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.*;
import io.micronaut.data.model.DataType;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.postgis.jdbc.PGgeometry;
import net.postgis.jdbc.geometry.Point;

@Data
@MappedEntity
@NoArgsConstructor
public class MyEntity {

    @Id
    @GeneratedValue(GeneratedValue.Type.SEQUENCE)
    Long id;

    @Nullable
    @Column(name="gis_point")
    @TypeDef(type = DataType.OBJECT)
    @MappedProperty(converter = PointAttributeConverter.class, type = DataType.OBJECT,
            converterPersistedType = PGgeometry.class)
    Point point;

//    @Nullable
//    @Column(name="gis_point")
//    @TypeDef(type = DataType.OBJECT)
//    @MappedProperty(converter = ProxyPointAttributeConverter.class, type = DataType.OBJECT,
//            converterPersistedType = PGgeometry.class)
//    ProxyPoint point;



}
