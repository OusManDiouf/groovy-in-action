package GroovyBasics.Closures

/**
 * CLosure supporte la méthode clone, pareil que java
 * Ne pas oublier d'effectuer le cast vers le correct type de retour !
 *
 * The asWriteable method returns a clone of the current closure that has an additional writeTo(Writer) method
 * to write the result of a closure call directly into the given Writer.
 *
 * Finally, there are a setter and getter for the so-called delegate.
 * We’ll cross the topic of what a delegate is and how it’s used inside a closure
 * when investigating a closure’s scoping rules in the next section.
 */

Closure<Integer> c1 ,c2,c3
c1 = { (Math.random() * 10).toInteger()}
c2 = c1.clone() as Closure<Integer>
println c1.call()
println c2.call()
//
//def writer = { println(it)}
//
//c3  = c1.asWritable()
//
//c3.writeTo(writer)