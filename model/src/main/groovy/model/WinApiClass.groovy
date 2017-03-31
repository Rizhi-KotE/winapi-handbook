package model

import groovy.transform.Canonical
import groovy.transform.TupleConstructor

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import model.WinApiFunction;

@Entity
@Canonical
@TupleConstructor
class WinApiClass {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    long id

    String name

    @Column(length = 1000)
    String description;

//    WinApiClass(){
//
//    }
//
//    WinApiClass(Long id, String nameText, String desr, String example, List<WinApiFunction> classFunctions) {
//        this.id = id
//        this.name = nameText
//        desrcBlob = desr.bytes
//        this.example = example
//        this.functions = classFunctions
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
    List<WinApiFunction> functions
}
