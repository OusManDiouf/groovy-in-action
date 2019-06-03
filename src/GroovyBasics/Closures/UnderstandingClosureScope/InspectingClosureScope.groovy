package GroovyBasics.Closures.UnderstandingClosureScope

/**
 * La régle Java et Groovy stipule que :
 * Toute "reference" non-qualifiée (à nue) represente un raccourcie qui raméne à "this.reference"
 *
 * A l'interieur d'une closure, on pourrait assumer que toute reference à this se rapporte à l'objet courrent,
 * ce qui veut dire, l'objet closure elle même, et que du coup,
 * toute reference sur une portée des environs necessiterait un qualificatif.
 * Ca ne serait pas trés pratique !
 *
 * Groovy suit une stratégie differente: qui est assez unique dans le paysage JVM
 * C'est le programmer qui controle comment les references sont résolues !
 * Bien qu'on ne puisse pas directement affecter une valeur differente sur l'objet this,
 * on peut affecter un délégué, qui sera utilisé à la résolution des variables libres.
 * Par defaut, le 'delegate' référe au script propriétaire.
 *
 * Cela peut sembler confus à prime abort, mais sachez que dans les CNTP(Condition Normal de Temperature et de Pression)
 * tout beigne nickel, sans qu'on ait a se soucier des régles de portées,
 * cependant, dans certains cas speciaux, on dispose là d'une énorme flexibilité
 *
 *
 */

class Mother {

    def prop = 'prop'

    static def method() { 'method' }

//    une méthode retournant une closure n'est pas trés courrante mais au besoin...
    Closure birth(param) {
        def local = 'local'
        def closure = { [this, prop, method(), local, param] }

        return closure
    }
}

Mother julia = new Mother()

/**Clorure Declaration Time
 *
 * Retourne un objet closure fraichement créé, noté qu'a ce niveau,
 * on est encore au moment de la déclaration (et non au runtime).
 * La liste que cette closure renverra au runtime(à l'invocation)
 * n'existe pas pour l'instant neanmoins les variables locales à cette closure
 * sont liées à leur references respectives: local et param
 */
def closure = julia.birth('param')

/**Closure Execution time
 *
 * A ce niveau, on invoque la clusure pour faire ressortir son contexte.
 * la closure construit la liste de toute ses references résolues
 * et on stock cette liste dans une variable pour inspection ultérieure.
 *
 */
def context = closure.call() // closure.call(this)

//la valeur du 'this' une fois la closure résolue.
assert context[0] == julia
// ces valeurs proviennent du contexte courant à la resolution de la closure (this.prop et this.method)
assert context[1, 2] == ['prop', 'method']
//valeur sans surprise car lié à la closure au moment de sa déclaration.
assert context[3, 4] == ['local', 'param']
//demande à la closure son contexte courrant (variable entre les executions)
assert closure.thisObject == julia
//demande à la closure son proprio actuel (graver dans la roche)
assert closure.owner == julia
// on voit que le 'delegate', le 'owner' et le 'thisObjet' référe tous au même objet
assert closure.delegate == julia
/*
 * Le fait d'avoir la portée locale de la closure à disposition,le delegate,
 * ainsi que le 'owner' souléve la question suivante:
 * Qui d'entre eux dispose de la presceance en cas de conflit et dans quel ordre !
 * On peut le configurer via les reglages de la méthode resolveStrategy
 * en l'affectant sur les config dispo :
 * OWNER_ONLY
 * OWNER_FIRST (default)
 * DELEGATE_ONLY
 * DELEGATE_FIRST
 * SELF_ONLY.
 */
assert closure.resolveStrategy == Closure.OWNER_FIRST

/**On peut maintenant exercer un control sur la portée.
 * On peut affecter le délégué à un objet different.
 * La méthode with() du GDK est l'outil qu'il faut: Execute une closure
 * en configurant d'abord le delegate sur le receveur de la méthode with
 *
 */

def map = [:]
// Lire: invoque la closure avec map comme delegate
map.with { // ici le delegate, c'est map !
    a:1
    b:2
}

//EXEMPLE SCOPING RULE
/**
 * We want to write a function that generates accumulators—a function that takes a number n,
 * and returns a function that takes another number i and returns n incremented by i.
 */

/**
 * En Javascript
 * > function func(n) {
 * >    return (i) =>n+=i;
 * > }
 * > const accumulator = func(10)
 * accumulator(2) return 12
 * accumulator(2) return 14
 * accumulator(2) return 16
 *
 * En Ruby
 * >> def func(n)
 * >>    lambda {|i| n+=i}
 * >> end
 * >> accumulator = func(10)
 * >> accumulator.call(2) return 12
 * >> accumulator.call(2) return 14
 * >> accumulator.call(2) return 16
 */

// concis
//static Closure func(Integer n) {
//    Closure inc = { i -> n+=i}
//    return inc
//}

// succint
static def func(n) { { i -> n+=i} }

def accumulator = func(10)

assert accumulator(2) == 12
assert accumulator(2) == 14

