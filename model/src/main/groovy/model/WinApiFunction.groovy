package model

import groovy.transform.Canonical

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
@Canonical
class WinApiFunction {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Long id

    String name

    @Column(length = 1000)
    String description

//    WinApiFunction(){
//
//    }
//
//    WinApiFunction(Long id, String nameText, String desr, String example, List<WinApiParameter> classFunctions) {
//        this.id = id
//        this.name = nameText
//        desrcBlob = desr.bytes
//        this.example = example
//        this.params = classFunctions
//    }
//
//    String getDescription(){
//        new String(desrcBlob)
//    }
//
//    void setDescription(String s){
//        desrcBlob = s.bytes
//    }

    String example

    @OneToMany(cascade = CascadeType.ALL)
    List<WinApiParameter> params
}
