package GroovyBasics.ControlStructure

import java.lang.reflect.Array

list = ['Funky', 'Sniper', 'Sinik', 'Booba', 'Ninho', 'K.James']


if (list[1] == 'Sniper')
    assert true, 'Classic'
else
    assert false, 'Nop'

for (item in list) {
    print "Artiste: $item \n"
}

for (item in 1..6) {
    print "Item$item \n"
}

idx = 0
while (idx < 10) {
    println "i = $idx | "
    idx++
}
assert idx == 10

state = 'High'
switch (state) {
    case 'low':
        println "I'm low"
        break
    case 'mid':
        println "I'm Mid"
        break
    case 'high':
        println "I'm High"
        break
    default:
        println 'no match !'
}

List<String>  Jlist= ['I','II', 'III', 'IV', 'V', 'VI', 'VII']
arr = Jlist.toArray()
print("java.lang.reflect.Array.getLength: ")
println Array.getLength(arr)
println arr.size()

StringBuffer str = new StringBuffer()
str.append('javascript')
str.append('java')
str.append('groovy')
str.append('scala')
str.append('kotlin')
str.append('closure')

arr.eachWithIndex { item, index->
//    Warning:(58, 23) 'getAt' in 'org.codehaus.groovy.runtime.DefaultGroovyMethods' cannot be applied to '(java.lang.Integer)'
    assert item == arr[index]
}

println "StringBuffer Length : $Array.getLength($str)"
