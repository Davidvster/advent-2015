package day16

import java.io.File

fun main(args: Array<String>) {
    val aunt = mapOf<String, Int>(
            "children" to 3,
            "casts" to 7,
            "samoyeds" to 2,
            "pomeranians" to 3,
            "akitas" to 0,
            "vizslas" to 0,
            "goldfish" to 5,
            "trees" to 3,
            "cars" to 2,
            "perfumes" to 1
    )

    var matchingAunt = -1
    var topMatches = 0
    File("/Users/david.valic/IdeaProjects/AdventCalendar/src/day16/day16src.txt").forEachLine { line ->
        val l = line.replace(",", "").replace(":", "").split(" ")
        var matches = 0
        aunt.forEach { s , count ->
            if (l.contains(s)) {
                if ((s == "cats" || s == "trees") && l[l.indexOf(s)+1].toInt() > count) {
                    matches ++
                } else if ((s == "pomeranians" || s == "goldfish") && l[l.indexOf(s)+1].toInt() < count) {
                    matches ++
                } else if (l[l.indexOf(s)+1].toInt() == count) {
                    matches ++
                }
                //part1
//                if (l[l.indexOf(s)+1].toInt() == count) {
//                    matches ++
//                }
            }
        }
        if (matches > topMatches) {
            matchingAunt = l[1].toInt()
            topMatches = matches
        }
    }
    println(matchingAunt)
}