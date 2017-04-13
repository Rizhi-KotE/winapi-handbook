package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"id", "params"})
public class WinApiFunction {

    @Id
    @GeneratedValue
    long id;

    String name;

    @Column(length = 1000)
    String description;


    @Column(length = 1000)
    String example;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    List<WinApiParameter> params;
}
