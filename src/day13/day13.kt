package day13

import java.io.File

fun main(args: Array<String>) {
    var maxHappines = 0
    val people = mutableListOf<String>()
    val relations = HashMap<Pair<String, String>, Int>()
    File("/Users/david.valic/IdeaProjects/AdventCalendar/src/day13/day13src.txt").forEachLine { line ->
        val l = line.split(Regex(" "))
        if (people.contains(l[0]).not()) {
            people.add(l[0])
        }
        if (l.contains("gain")) {
            relations[Pair(l[0], l[10].substring(0, l[10].length-1))] = l[3].toInt()
        } else if (l.contains("lose")) {
            relations[Pair(l[0], l[10].substring(0, l[10].length-1))] = -l[3].toInt()
        }
    }
    people.add("ME")
    var happiness = Int.MIN_VALUE
    people.forEach {
        val tmpPeople = people.toMutableList()
        tmpPeople.remove(it)
        val tmpHappiness = calcHappiness(it, it, tmpPeople, relations)
        if (tmpHappiness > happiness) {
            happiness = tmpHappiness
        }
    }
    println(happiness)
}

fun calcHappiness(firstPerson: String, person: String, people: MutableList<String>, relations: HashMap<Pair<String, String>, Int>): Int {
    var happiness = Int.MIN_VALUE
    if (people.size == 1) {
        return (relations[Pair(person, people[0])]?:0) + (relations[Pair(people[0], person)]?:0) + (relations[Pair(people[0], firstPerson)]?:0) + (relations[Pair(firstPerson, people[0])]?:0)
    } else {
        people.forEach {
            var tmpHappiness = 0
            tmpHappiness += (relations[Pair(person, it)]?:0) + (relations[Pair(it, person)]?:0)
            val tmpCities = people.toMutableList()
            tmpCities.remove(it)
            tmpHappiness += calcHappiness(firstPerson, it, tmpCities, relations)
            if (tmpHappiness > happiness) {
                happiness = tmpHappiness
            }
        }

    }
    return happiness
}