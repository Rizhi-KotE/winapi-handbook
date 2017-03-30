package model

import groovy.transform.Canonical

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
@Canonical
public class WinApiParameter {
    @Id
    Long id;

    @OneToOne
    WinApiClass type;

    String name;
}
