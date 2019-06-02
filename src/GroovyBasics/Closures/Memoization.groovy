package GroovyBasics.Closures


/**
 * Lorsqu'on dispose d'une closure qui est souvent calculé avec les mêmes arguments
 * ou que l'execution de la closure est vraiment couteuse,
 * on peut choisir de mettre en cache le resultat.
 * Une closure Groovy nous procure une méthode pour effectuer ce genre d'action
 * via la méthode memoize
 *
 * There are also methods to get more fined-grained control over the cache: memoizeAtMost, memoizeAtLeast, and memoizeBetween.
 * These allow you to set one or both of an upper limit,
 * on the cache size with cache entries ejected on a least recently used (LRU) basis,
 * and a protected minimum limit. Cache entries outside the protected limit are subject to garbage collection,
 * and those below are protected. In section 9.2, you’ll also see the @Memoized AST transformation for memoizing methods.
 */



Closure<Integer> fib
BigDecimal start,stop

fib = { Integer x -> x < 2 ? 1 : fib(x - 1) + fib(x - 2) }

start = System.nanoTime()
assert fib(40) == 165_580_141
stop = System.nanoTime()
println ( (stop - start) * 1E-9)

fib = fib.memoize()

start = System.nanoTime()
assert fib(40) == 165_580_141
stop = System.nanoTime()
println ( (stop - start) * 1E-9)

