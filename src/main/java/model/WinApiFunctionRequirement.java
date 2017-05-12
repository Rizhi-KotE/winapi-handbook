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

    String key;

    String value;
}
