package GroovyBasics.Closures.UnderstandingClosureScope

/**
 * Il est evident au vu de cette exemple que la closure a accés à la variable x,
 * qui est localement accéssible lorsque la closure est déclaré.
 * Rappelez vous que les accolates montrent le moment de declaration de la closure
 * et non son moment d'exectution.
 * La closure peut accéder(en mode lecture/ecriture) aux variables libres au moment de sa declaration
 * Ce qui nous améne à la question...
 * Comment la closure se rappelle t'elle de x au moment de l'exectution ?
 * La closure, au moment de sa déclaration, se rappele de son contexte de naissance,
 * durant tout le long de son cycle de vie. De ce fait, elle peut travailler sur CE contexte originel,
 * lorsque le besoin se presente.
 * CE contexte de naissance de la closure doit être representé sous forme de REFERENCE et non une copie,
 * si le contexte represente une copie du contexte original,
 * on ne pourrait pas changer la valeur du contexte via la closure elle même !
 * DONC LE CONTEXTE DE NAISSANCE DOIT ETRE UNE REFERENCE
 *
 */

/**
 * CONCEPT
 * Le script proriététaire(script courrant) crée une closure.
 * Cette closure retient la reference sur laquelle pointe la variable x,
 * qui est local au script propriétaire.
 * Le script appel la méthode times() du nombre Entier Objet 10,
 * en lui passant la closure déclaré en param.
 * En d'autre terme, lorsque times est invoqué,
 * une reference sur l'objet closure se trouve dans le stack.
 * La méthode times() utilise cette reference pour invoquer
 * la méthode call() sur la closure, en lui passant la variable local count (non-utilisé ici)
 * ici la méthode call() opére uniquement sur la ref de x provenant de son contexte de naissance.
 */
def x = 0
10.times { x++ }
assert x == 10// x is a ref otherwise, the assertion fail


