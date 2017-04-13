package model

import groovy.transform.Canonical

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Canonical
@Table(name = "parameter")
class WinApiParameter {
    @Id
    @GeneratedValue
    @Column(name = "id")
    long id

    String type

    String name
}
