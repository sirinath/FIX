import groovy.json.JsonParserType
import groovy.json.JsonSlurper

import java.beans.Introspector

def fixFile = new File("C:\\Users\\sirin_000\\IdeaProjects\\Trader\\FIX\\src\\main\\resources\\FIXSpec\\Fix8\\fix_4.4.xml")

def fix = new XmlParser().parse(fixFile)


def header = """\
mod ${fix.header[0].@msgcat} {
    struct FIX${fix.@major}_${fix.@minor}_${fix.@servicepack}_Header {
        ${fix.header[0].field.collect({ "" + Introspector.decapitalize(it.@name) + ": " + it.@name }).join(", ")}
    }
}
"""

println header

fix.messages[0].children().each {
    println it.@name
}

println()

fix.header[0].children().each {
    println it.@name
}

println()

fix.trailer[0].children().each {
    println it.@name
}

println()


fix.breadthFirst().findAll({ it.@msgcat != null }).groupBy({ it.@msgcat }).each {
    println it.key
    it.value.each {
        println it
    }
}

println()

fix.breadthFirst().findAll({ it.name() == "group" }).groupBy {
    def node = it
    while (node.parent() != null && node.parent().@msgcat != null)
        node = node.parent()

    node.@msgcat
} each {
    println it
}