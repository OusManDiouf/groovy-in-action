package GroovyBasics.Closures

/**
 * Les Closures Groovy implementent la méthode isCase pour les faire travailler comme classificateur
 * et en tandem avec les expressions grep et switch.
 * Les arguments respectives sont passé dans la closure, et l'appel de ce dernier evalue une valeur boolean Groovy.
 * Ca permet de classifier nos logiques de façon arbitraire.
 * cela est possible parce que les closures sont des objets.
 */

Closure<Boolean> impaire = { it % 2 == 1}
Closure<Boolean> paire = { it % 2 == 0}
assert [1,2,3].grep(impaire) == [1,3]
assert [1,2,3].grep(paire) == [2]