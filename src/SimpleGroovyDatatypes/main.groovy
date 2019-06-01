package SimpleGroovyDatatypes

import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation

int number = 4
//autoboxing happen automatically in Groovy
println number.class
println JavaClass.getNumber().class
JavaClass.getInfo()

String op = "ABCDE"

// Bien que cette méthode prénne un int primitif,
// groovy se charge du 'unboxing' avant de passer
// le parametre à la fonction indexOf.
// Cette dernier retourne un type int,
// qui passe par le mecanisme de 'boxing' en Integer
// dés sont entré dans le monde de groovy.
println op.indexOf(67)

// Groovy est plus orienté object que java
println 1.plus(1) == 1 + 1

/**
 * Peut importe comment les literaux(numbers, strings,etc...) apparaissent
 * dans le code groovy, c'est toujours des objets.
 * Seulement à la limite de Java, ces derniers sont 'boxed' et 'unboxed'
 * Les operateurs sont des raccourcis vers des appels de méthodes.
 */

//DefaultTypeTransformation.compareEqual()

