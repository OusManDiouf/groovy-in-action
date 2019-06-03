package GroovyBasics.ControlStructure

if (true) assert true // happy path
else assert false

if (1) {
    assert true // happy path
} else {
    assert false
}
// un String utilisé dans une expression if !!
if ('nonempty') assert true // happy path
else if (['x']) assert false
else assert false

if (0) assert false
else if ([]) assert false
else assert true // happy path

