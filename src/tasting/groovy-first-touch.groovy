package tasting

println "#############################################"

// Print file with line number
def number = 0
new File('/home/oussoulessou/.zshrc').eachLine { line ->
   number++
   println "$number: $line"
}

println "#############################################"

// Print package for each class in the array
def classes = [String, List, File]
for (classe in classes) {
   println classe.package.name
}


println "#############################################"

// Alternative syntax:  Property access to a list
def classes1 = [String, List, File]
println classes1*.package*.name


println "#############################################"

// ou
println( [File, Integer, String]*.package*.name)


println "#############################################"

// Le caractére * est optionel, on l'a ajouté pour montrer que l'accés
// à la prop package et name est appliqué sur chaque element de la liste
println([List, String].package.name)


println "#############################################"

// XML handling with GPATH
def customers = new XmlSlurper().parse(new File('customers.xml'))
for (customer in customers.corporate.customer) {
  println "${customer.@name} work for ${customer.@company}"
}

println "#############################################"
def customers1 = new XmlSlurper().parse(new File('customers.xml'))
for (customer in customers1.consumer.customer) {
  println "Command sent to ${customer.@name}"
}


println "#############################################"


// Recher les mots clef dans un site
import static groovyx.gpars.GParsPool.withPool

def urls = [
        'http://groovy-lang.org',
        'http://gpars.org',
        'http://gr8conf.eu',
]*.toURL()

println withPool {
    urls.collectParallel {
        it.text.findAll(~/[gG]roovy/).size()
    }
}

