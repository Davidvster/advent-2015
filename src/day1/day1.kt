package day1

import java.io.File

fun main(args: Array<String>) {
    var floor = 0
    File("/Users/david.valic/IdeaProjects/AdventCalendar/src/day1/day1src.txt").forEachLine { line ->
        line.forEachIndexed { i, char ->
            if (char == '(') {
                floor ++
            } else if (char == ')') {
                floor --
            }
            if (floor == -1) {
                println(i+1)
            }
        }
    }
    println(floor)
}