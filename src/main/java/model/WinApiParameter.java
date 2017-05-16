package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WinApiParameter {
    /**
     * identifier in database
     */
    long id;

    long functionId;

    long elementId;

    /**
     * definition of name and attributes of parameter
     */
    String firstDefinition;

    /**
     * definition of parameter values type
     */
    String typeDefinition;

    /**
     * special description of parameter
     */
    String description;
}
