package model

import groovy.transform.Canonical
import groovy.transform.EqualsAndHashCode

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Canonical
@EqualsAndHashCode(excludes = "params")
class WinApiFunction {

    @Id
    @GeneratedValue
    long id

    String name

    @Column(length = 1000)
    String description


    @Column(length = 1000)
    String example

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    List<WinApiParameter> params
}
