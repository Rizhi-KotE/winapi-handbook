package model

import groovy.transform.Canonical

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
@Canonical
class WinApiParameter {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    long id

    String type

    String name
}
