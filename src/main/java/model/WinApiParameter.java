package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "winapi_parameter")
public class WinApiParameter {
    @Id
    @GeneratedValue
    long id;

    String type;

    String name;
}
