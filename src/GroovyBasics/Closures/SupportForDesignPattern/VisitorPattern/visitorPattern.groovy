package GroovyBasics.Closures.SupportForDesignPattern.VisitorPattern

/**
 * Le visiteur pattern est particulierement utile lorsqu'on veut effectuer
 * des logiques de metiers sur une collection composite (list ou arbre)
 * d'une classe existante. Plûtot que d'alterer la classe existante pour
 * qu'elle contienne la logique de business désirée, une classe Visitor est introduit.
 * Le visiteur sait comment traverser la collection composite et
 * comment effectuer la logique metier sur les differents type de classes.
 * Si le composite change ou que la logique de business évolue,
 * seule la classe Visitor sera impactée.
 */

class Dessin {
    List<Forme> formes

    def accept(Closure yield) {
        formes.each { Forme forme -> forme.accept(yield) }
    }
}

class Forme {
    def accept(Closure yield) { yield(this) }
}

class Carre extends Forme {
    def largeur

    def surface() { largeur * 2 }
}

class Cercle extends Forme {
    def rayon

    def surface() { Math.PI * rayon**2 }
}

def image = new Dessin(formes: [new Carre(largeur: 10), new Cercle(rayon: 2)])
def total = 0
image.accept { total += it.surface() }
println("Les formes du dessin couvre une surface de $total unités")
println("Les principaux contributeurs sont:")
image.accept {
    println "${it.class.name}: ${it.surface()}"
}