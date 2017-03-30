package model

import groovy.transform.Canonical
import groovy.transform.TupleConstructor

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany
import model.WinApiFunction;

@Entity
@Canonical
@TupleConstructor
class WinApiClass {
    @Id
    Long id;

    String name;

    String description;

    String example;

    @OneToMany
    List<WinApiFunction> functions;
}
