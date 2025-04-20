package com.example.model;

import io.micronaut.data.annotation.TypeDef;
import io.micronaut.data.model.DataType;
import io.micronaut.serde.annotation.Serdeable;
import net.postgis.jdbc.geometry.Point;

@Serdeable
@TypeDef(type = DataType.OBJECT)
public class ProxyPoint extends Point {
}
