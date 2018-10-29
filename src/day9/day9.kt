package day9

import java.io.File

fun main(args: Array<String>) {
    val cities = mutableListOf<String>()
    val distances = HashMap<Pair<String, String>, Int>()
    File("/Users/david.valic/IdeaProjects/AdventCalendar/src/day9/day9src.txt").forEachLine { line ->
        val l = line.split(" ")
        if (cities.contains(l[0]).not()) {
            cities.add(l[0])
        }
        if (cities.contains(l[2]).not()) {
            cities.add(l[2])
        }
        distances[Pair(l[0], l[2])] = l[4].toInt()
    }
    var distance = Int.MIN_VALUE
    cities.forEach {
        val tmpCities = cities.toMutableList()
        tmpCities.remove(it)
        val tmpDistance = calcDist(it, tmpCities, distances)
        if (tmpDistance > distance) {
            distance = tmpDistance
        }
    }
    println(distance)
}

fun calcDist(city: String, cities: MutableList<String>, distances: HashMap<Pair<String, String>, Int>): Int {
    //part1 Int.MAX_VALUE and > instead of <

    var distance = Int.MIN_VALUE
    if (cities.size == 1) {
        return distances[Pair(city, cities[0])]?:distances[Pair(cities[0], city)]?:Int.MIN_VALUE
    } else {
        cities.forEach {
            var tmpDistance = 0
            tmpDistance += distances[Pair(city, it)]?:distances[Pair(it, city)]?:Int.MIN_VALUE
            val tmpCities = cities.toMutableList()
            tmpCities.remove(it)
            tmpDistance += calcDist(it, tmpCities, distances)
            if (tmpDistance > distance) {
                distance = tmpDistance
            }
        }

    }
    return distance
}