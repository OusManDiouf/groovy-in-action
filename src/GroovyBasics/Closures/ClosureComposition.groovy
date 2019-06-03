package GroovyBasics.Closures

/**
 * Etant donnée deux fonctions f et g tel que l'expression f(g(x)) represente
 * une composition du type (f.g)(x) où . represente l'expression de composition.
 * En groovy, on peut accomplir la même chose avec les operateurs leftShift << et rightShift >>
 * qui pointent respectivement de la closure la plus interne à celle la plus externe.
 */

def deuxFois = { x -> x * 2}
def quatreFois = deuxFois >> deuxFois
def huitFois = deuxFois << quatreFois
assert deuxFois(2) == 4
assert huitFois(2) == 16