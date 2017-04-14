package model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.annotation.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"functions", "id"})
@Table(name = "WINAPI_CLASS")
public class WinApiClass {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    long id;

    String name;

    String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "class_id")
    List<WinApiFunction> functions;
}
