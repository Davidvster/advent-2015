package day2

import java.io.File

fun main(args: Array<String>) {
    var paper = 0
    var ribbon = 0
    File("/Users/david.valic/IdeaProjects/AdventCalendar/src/day2/day2src.txt").forEachLine { line ->
        val box = line.split("x").map { it.toInt() }
        paper += 2 * (box[0] * box[1]) + 2 * (box[0] * box[2]) + 2 * (box[1] * box[2]) + Math.min(Math.min(box[0] * box[1], box[1] * box[2]), box[0] * box[2])
        ribbon += box[0] * box[1] * box[2]
        val min = Math.min(box[0], box[1])
        ribbon += 2 * min + 2 * Math.min(box[2], box[1 - box.indexOf(min)])
    }
    println("paper: $paper")
    print("ribbon: $ribbon")
}