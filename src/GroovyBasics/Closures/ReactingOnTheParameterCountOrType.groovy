package GroovyBasics.Closures

/**
 * Certaines fonctions sont capables d'adapter leur comportement selon le nombre param
 * reçu par la closure auquelle elles sont associées, on peut accomplir la même fonctionnalité
 * pour nos propres méthodes en retrouvant le nombre de paramétre appliqués à leur closure
 * via les methodes getMaximumNumberOfParameters et  getParameterTypes
 */

/**
 *
 * @param closure
 * @return
 */
static int numParams(Closure closure) { closure.getMaximumNumberOfParameters() }

assert numParams { one -> } == 1
assert numParams { one, two -> } == 2

static Class[] paramTypes(Closure closure) { closure.getParameterTypes() }

assert paramTypes { String s -> } == [String]
assert paramTypes { Number n, Date d -> } == [Number, Date]