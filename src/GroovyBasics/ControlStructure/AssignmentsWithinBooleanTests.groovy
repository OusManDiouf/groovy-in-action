package GroovyBasics.ControlStructure

//normal comparaison
def x = 1
if (x == 2) assert false

/*************************
 compile time not allowed
 if (x=2) assert false
 *************************/

// assign et test dans l'expression imbriqué (noter la sous-expression entre parenthése,
// qui léve l'alerte du compileur Groovy)
if ((x = 3)) println x

// assignation délibéré et test au niveau du while
// Found in usage like processing tokens retrieved from a parser or reading data from a stream
def store = []
while (x = x - 1) {
    store << x
}
assert store == [2,1]

//pouf !! affiche 2
while (x = 2) {
    println(x)
    break // loop jusqu'a l'infinie et l'au delà sans le break !
}
