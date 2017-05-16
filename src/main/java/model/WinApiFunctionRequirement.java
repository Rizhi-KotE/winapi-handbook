package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WinApiFunctionRequirement {
    /**
     * identifier in database
     */
    long id;

    long functionId;

    long elementId;

    String key;

    String value;
}
