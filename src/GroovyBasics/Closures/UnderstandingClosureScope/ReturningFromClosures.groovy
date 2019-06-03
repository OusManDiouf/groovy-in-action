package GroovyBasics.Closures.UnderstandingClosureScope

/**
 * En principe, Il y deux maniéres de revenir d'entre les closures
 * -> La derniére expression d'un closure à été évaluée, et son resultat est retourné.
 * -> Le mot clé retun peut aussi être utilisé pour sortir d'une closure de maniére prématuré.
 *
 * Denja!: Il y a une difference entre utiliser le mot clé return
 *         à l'interieur et à l'exterieur d'une closure.
 *         A l'exterieur d'une closur, toute reference à return
 *         fait sortir de la méthode courrante.
 *         Alors qu'utilisé dans une closure, elle fait sortir de l'evaluation de la closure:
 *         ce qui constitue un effet disons un peu plus localisé.
 *         Exemple: avec List.each, appel à return dans la closure de la méthode each
 *         ne cause pas un retour prématuré de la méthode each,
 *         la closure sera neanmoins invoqué avec l'element suivant de la liste.
 */

[1].collect { it.toInteger() * 2 }
1..3.collect {
    if (it % 2 == 0) return it * 2
    return it
}

Integer[] list = [12,14,16,20]
list.each { item ->
//    if (item == 14) return item * 1000
    print "item = $item"
}
