package model

import groovy.transform.Canonical

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
@Canonical
class WinApiFunction {

    @Id
    Long id

    String name

    String description

    String example

    @OneToMany
    List<String> params
}
