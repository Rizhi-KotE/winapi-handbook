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
@Table(name = "WINAPI_FUNCTION")
public class WinApiFunction {

    @Id
    @GeneratedValue
    long id;

    String name;

    String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "function_id")
    List<WinApiParameter> params;
}
