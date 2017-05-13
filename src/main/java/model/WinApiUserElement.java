package model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"functions", "id"})
public class WinApiUserElement {
    /**
     * identifier in database
     */
    long id;

    /**
     * name of element
     */
    String name = "";

    /**
     * element description
     */
    String description = "";

    /**
     * element's functions
     */
    List<WinApiFunction> functions;
}
