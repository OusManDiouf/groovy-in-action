package GroovyBasics.Closures

/**
 * Certaines operations recurssive peuvent entrainer un stackOverflow,
 * et puisque le JVM ne dispose pas de 'tail elimination',
 * ces genres de problémes sont difficiles à solutionner.
 * Groovy offre deux approches :
 *  --> l'algorithme tranpoline (uniquement valable pour les closures tails recurssives
 *  --> la tranformation AST @TailRecursive(utilisable sur les méthodes simples)
 */

Closure last
BigDecimal start, stop

//retrouve le dernier element de toute structure de donnée comportant un size, head, et un tail.
// sans trampoline le code lance l'erreur java.lang.StackOverflowError aprés quelques iterations.
//last = { it.size() == 1 ? it.head() : last(it.tail()) }

last = { it.size() == 1 ? it.head() : last.trampoline(it.tail()) }
last = last.trampoline()

start = System.nanoTime()
assert last(0..10_000) == 10_000
stop = System.nanoTime()
println((stop - start) * 1E-9)




