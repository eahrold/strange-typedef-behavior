package com.example.model;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.core.convert.ConversionContext;
import io.micronaut.data.model.runtime.convert.AttributeConverter;
import jakarta.inject.Singleton;
import net.postgis.jdbc.PGgeometry;
import net.postgis.jdbc.geometry.Geometry;
import net.postgis.jdbc.geometry.Point;

import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
public class PointAttributeConverter implements AttributeConverter<Point, PGgeometry> {

    Logger logger = Logger.getLogger(PointAttributeConverter.class.getName());

    @Override
    public @Nullable PGgeometry convertToPersistedValue(@Nullable Point entityValue,
            @NonNull ConversionContext context) {
        if (entityValue != null) {
            return new PGgeometry(entityValue);
        }
        return null;
    }

    @Override
    public @Nullable Point convertToEntityValue(@Nullable PGgeometry persistedValue,
            @NonNull ConversionContext context) {
        if (persistedValue != null) {
            if (Geometry.POINT == persistedValue.getGeoType()) {
                return (Point) persistedValue.getGeometry();
            }

            logger.log(Level.WARNING, "Expected Point, but found {0}", persistedValue);
        }

        return null;
    }

}
