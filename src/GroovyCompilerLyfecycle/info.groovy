/**
 * Supposons qu'on ait un fichier MyScript.groovy et qu'on l'execute via
 * la commande "groovy MyScript.groovy":
 * Les étapes suivante representent la generation de classes:
 *  -> Le fichier est parcouru par le parser
 *  -> Le parser genere l'AST representant le fichier source.
 *  -> Le generateur de classe Groovy prend l'AST puis genere le bytecode correspondant
 *  -> En fonction du contenu du fichier, cela peut résulter en plusieurs classes.
 *     Classes qui sont maintenant disponible au niveau du classLoeader de Groovy.
 *  -> Le JRE est invoqué comme si on avait invoqué la commande java MyScript.
 *
 * La même procédure de génération de code est utilisé
 * avec la commande "groovyc Myscript.groovy"(pré-compilation)
 * au lieu de "groovy MyScript.groovy" (mode direct).
 * Cette fois les classes sont générées avec une extension *.class
 *
 *  Pré-compilation ==> groovyc MyScript.groovy ==> *.class ==> Java classLoader
 *  Mode Direct     ==> groovy  MyScript.groovy ==>  Groovy classLoader
 *
 * Les deux variantes utilisent le mécanisme de génération de code ci-dessus.
 *
 * Q: En quoi groovy est-il un language dynamique
 *    si tout les fichiers sources *.groovy
 *    finissent dans un format static *.class?
 *
 * ### Groovy est un language dynamic.
 *
 *    Qu'est ce qui caractérise un language dynamic ?
 *    C'est le mecanisme de dispatching des méthodes et non le typage dynamique !
 *    Ex: Avec le framework Grail, l'expression Album.findByArtist('lessou')
 *        invoque la méthode findByArtist sur la classe Album,
 *        or cette méthode est inexistante sur cette classe.
 *
 *    L'astuce, c'est que les invocations de méthodes sont canalisé à travers l'objet MetaClass,
 *    qui prend acte de l'absence d'une tel méthode sur l'objet cible, et du coup passe le relais
 *    au handler missignMethod.
 *    Ce dernier connait les conventions de nomage utilisées par les méthodes de recherches dynamiques
 *    du framework Grail.
 *
 * Q: Puisque Groovy est compilé bytecode java, comment se passe l'invocation de l'objet MetaClass ?
 *    Le bytecode produit par le generateur Groovy est necessairement different dy bytecode généré
 *    par java (diffent du point de vu du contenu généré et non du format produit)
 *    Supposons une fonction foo(), groovy produit quelque chose de la sorte:
 *      getMetaClass().invoqueMethod(this,"foo", EMPTY_PARAMS_ARRAY)
 *     Ainsi, les appels de méthodes sont redirigés à travers l'objet MetaClass.
 *     Cette dernier peut alors faire sont travail de redirection, d'interception,
 *     d'ajout/suppression de méthodes au runtime.
 *     Ce mecanisme est commun à tout code invoqué à partir de groovy independament du fait
 *     que les appels se font sur des objets Groovy ou Java.
 *     (aprés tout, du POV du JVM, il n'y a pas de diff)
 *
 *     Cependant cette flexibilité n'est pas sans impact sur la performance au runtime,
 *     N'empêche, cette pénalité reste minime et de plus l'implementation de l'objet MetaClass
 *     utilise des procédé de mise en cache et des stratégie de court-circuitage qui permettent
 *     au JIT compiler et au hot-spot du JVM de reduire la casse.
 *     Lorsqu'on a besoin de performance proche de Java, on peut utiliser la déco @CompileStatic
 *     ce qui évite le passage par l'objet MetaClass lors de la génération de code.
 *
 * ### Groovy est un language static.
 *     Lorsqu'on ait pas assez rassuré par les aléas du style dynamique,
 *     on peut toujours forcer le compilateur à éfféctué une vérification du typage static
 *     (ce dernier utilise un mécanisme de déduction de type trés sophistiqué)
 *     via l'annotation @TypeChecked, appliqué sur les bouts de codes
 *     dont on désire exclurent les fonctionnalités dynamiques.
 *     Ce mécanisme est extensible pour les situations qui demande plus de restriction.
 *
 *
 *
 */