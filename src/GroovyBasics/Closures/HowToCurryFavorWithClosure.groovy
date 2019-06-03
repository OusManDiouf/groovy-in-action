package GroovyBasics.Closures


//        {################################################################}

//currying élaboré avec les closures (le typage explicite n'est pas requis)
//RECETTE 1
Closure configurator = { Closure format, Closure filter,
                         GString ligne ->
    (filter(ligne)) ? format(ligne) : null
}

//RECETTE 2

/**
 * A simple appender
 *
 * @param closure config
 * @param closure append
 * @param gstring ligne
 */
Closure appender = { Closure config, Closure append,
                     GString ligne ->
    def sortie = config(ligne)
    if (sortie) append(sortie)
}
// INGREDIENTS
Closure<GString> dateFormater = { ligne -> "${new Date()}: $ligne" }
Closure<Boolean> debugFormater = { ligne -> ligne.contains('debug') }
Closure consoleAppender = { GString ligne -> println ligne }

//The basic idea of currying is to take a function with multiple parameters
// and transform it into a function with fewer parameters by fixing some of the values.
Closure myConf = configurator.curry(dateFormater, debugFormater)
Closure myLog = appender.curry(myConf, consoleAppender)

myLog("here is some debug message")
myLog("thi will not be printed")