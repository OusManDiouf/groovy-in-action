package GroovyBasics.ControlStructure

assert true
assert !false // montre qu'une expression est reellement evaluée à la valeur false

// Les Matcher doivent matcher !
assert ('a' =~ /./)
assert !('a' =~ /b/)

// Les Collections doivent être non-vide
assert [1]
assert ![]

// Les iterators doivent avoir un prochain element
Iterator iter = [1].iterator()
assert iter
iter.next()
assert !iter
assert !iter.hasNext()

// Les dico doivent être non-vide
assert ['one':1]
assert ![:]

// Les Strings doivent être non-vide
assert 'a'
assert !''

// Les nombres(de tout type) doivent être non-nuls
assert 1
assert 1.1
assert 2.2f
assert 1.3g
assert 2L
assert 3G
assert !0

// Les objets doivent être non-nuls

assert !null
assert new Object()

// Valeur de vérité custom
class ToujoursFaux {
    boolean asBoolean() {false}
}
//appel directement asBoolean !!! wtf !
assert !new ToujoursFaux()
