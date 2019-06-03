package GroovyBasics

import GroovyBasics.Annotations.GraverDansLaRoche
import GroovyBasics.Classes.*
import GroovyBasics.GroovyBeans.*

// Java importe automatiquement le package java.util.*  et rien d'autre.

/**
 /* Groovy importe automatiquement:
 * groovy.lang.*
 * groovy.util.*
 * java.lang.*
 * java.util.*
 * java.net.*
 * java.io.*
 * java.math.BigInteger.*
 * java.io.BigDecimal.*
 */


// URL encoding For java
// java.net.URLEncoder.encode("a b", "UTF-8)


// Groovy style !
def url = URLEncoder.encode 'a b', 'UTF-8'
println url

// Parathéses requises pour une expression inline.
// Aide le compilateur à déduire le type
println(URLEncoder.encode('a b', 'UTF-8'))


// Utilisation des assertions en Groovy
x = 1
assert x == 1
//assert x != 1  Assertion failed (message d'erreur premium :)

// Ici def signifie un typage dynamique
def y = 12; assert y == 12

// Exemple complexe
assert ('Hello' * 3 << ' lessou').size() == 5 * 3 + 7

// Note: Utiliser les assertions en lieu et place des sorties consoles (print et famille)
// Une sorte d'EXtreme TDD ! on garde le code et le resultat proche


// Usage d'une classe dans une fichier de script,
// ce dernier contient des expressions en dehors
// d'une definition de classe.

//###################################################################

// Le seule pré-requis est de faire figurer la classe dans le classpath
// Le runtime Groovy se chargera de la machinerie  Orientée Objet
//
Livre luxuryOOP = new Livre('Design Pattern in ruby')
luxuryOOP.getTitre() == 'Design Pattern in ruby'

// Un script peut méme contenir des definitions
// de méthodes pour mieux structurer le code.
assert getTitreReverse(luxuryOOP) == 'ybur ni nrettaP ngiseD'

// noté la definition tardive de cette méthode
// ce qui prouve que groovy est analysé , complilé et généré avant son execution !
static def getTitreReverse(livre) {
    String titre = livre.getTitre()
    return titre.reverse()
}

//###################################################################
// Groovy Bean
//###################################################################
def livreGroovy = new LivreBean()

//usage des props avec des getter/setter explicites.
livreGroovy.setTitre('Groovy en Action')
assert livreGroovy.getTitre() == 'Groovy en Action'

// Usage des props avec les raccourcis groovy
livreGroovy.titre = "Make your Java Groovy"
assert livreGroovy.titre == 'Make your Java Groovy'


//###################################################################
// Annotations
//###################################################################
zik1 = new GraverDansLaRoche('Funky Family')
zik2 = new GraverDansLaRoche(titre: 'Funky Family')

assert zik1.getTitre() == 'Funky Family'
assert zik1 == zik2

try {
//    Warning:(97, 5) Cannot assign a value to final field 'titre'
    zik1.titre = 'whatever' // zik1  is an immutable bean
    assert false, 'should not reach here !'
} catch (ReadOnlyPropertyException $expected) {
//    Warning:(99, 36) Unused catch parameter '$expected'
//    println "Expected Error:  '$expected.message'"
}

//###################################################################
// RegEx
//###################################################################

assert '12345' =~ /\d+/
assert 'XXXXX' == '12345'.replaceAll(/\d/, 'X')
