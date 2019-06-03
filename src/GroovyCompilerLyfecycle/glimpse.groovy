package GroovyCompilerLyfecycle

import groovy.transform.*

class Universe {
//    Sans la vérification sur le type,
//    cette fonction se crash au runtime
//    avec l'exception GroovyCastException
//    static int answer(){
//        "forty two"
//    }

//    Avec la vérification de type,
//    on obtient une erreur "Static Type Checking" durant la scompilation.
    @TypeChecked
    static String answer(){
        "Still..."
    }
}

println Universe.answer()


