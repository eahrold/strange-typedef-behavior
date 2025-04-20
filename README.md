## Strange `@TypeDef` Behavior

The `@TypeDef(type = DataType.OBJECT)` is getting wiped when it's applied at the field level, making
it impossible to get the def applied to 3rd party entities. 


```java
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

    // This Doesn't Work, and causes compile failure...
    @Nullable
    @Column(name="gis_point")
    @TypeDef(type = DataType.OBJECT)
    @MappedProperty(converter = PointAttributeConverter.class, type = DataType.OBJECT,
            converterPersistedType = PGgeometry.class)
    Point point;

    // This Works    
    @Nullable
    @Column(name="gis_point")
    @TypeDef(type = DataType.OBJECT)
    @MappedProperty(converter = ProxyPointAttributeConverter.class, type = DataType.OBJECT,
            converterPersistedType = PGgeometry.class)
    ProxyPoint point;
}
```

## Screenshots of Step Through

Notice on the 3rd, the type is set as Object (which is correct), but if it's object then getting nulled out immedaitely after.

![Screenshot 2025-04-18 at 10 01 22 PM](https://github.com/user-attachments/assets/4488f5e4-807f-4827-afda-b4729b9bd415)
![Screenshot 2025-04-18 at 10 01 34 PM](https://github.com/user-attachments/assets/53db4269-5f80-4946-bb98-31cdf6ed9a58)
![Screenshot 2025-04-18 at 10 01 45 PM](https://github.com/user-attachments/assets/f4efae59-4523-452e-84de-a2c17dd616cc)
![Screenshot 2025-04-18 at 10 02 02 PM](https://github.com/user-attachments/assets/c2d81564-3399-49db-b481-c00514cab209)
![Screenshot 2025-04-18 at 10 02 17 PM](https://github.com/user-attachments/assets/4de48eb6-7266-4677-b870-03746b68d5cf)

