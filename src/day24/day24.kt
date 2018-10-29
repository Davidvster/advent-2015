package day24

import java.io.File

private val configurations = mutableListOf<Triple<MutableList<Int>, MutableList<Int>, MutableList<Int>>>()

fun main(args: Array<String>) {
    val boxes = mutableListOf<Int>()
    File("/Users/david.valic/IdeaProjects/AdventCalendar/src/day24/day24src24.txt").forEachLine { line ->
        boxes.add(line.toInt())
    }

    addBoxes(boxes, mutableListOf(), mutableListOf(), mutableListOf())

    val a = configurations.minBy {
        var b = 1
        it.first.forEach { b *= it }
        b
    }!!.first.size
    println(a)
}

fun addBoxes(boxes: MutableList<Int>, first: MutableList<Int>, second: MutableList<Int>, third: MutableList<Int>) {
//    first.sortDescending()
//    second.sortDescending()
//    third.sortDescending()
    if (boxes.isEmpty() && first.sum() == second.sum() && first.sum() == third.sum() && second.sum() == third.sum() && configurations.contains(Triple(first,second,third)).not()) {
        configurations.add(Triple(first, second, third))
        return
    } else {
        boxes.forEach { box ->
            second.add(box)
            val newBoxes = boxes.toMutableList()
            newBoxes.remove(box)
            addBoxes(newBoxes, first, second, third)
            second.remove(box)
        }
        boxes.forEach { box ->
            third.add(box)
            val newBoxes = boxes.toMutableList()
            newBoxes.remove(box)
            addBoxes(newBoxes, first, second, third)
            third.remove(box)
        }
        boxes.forEach { box ->
            first.add(box)
            val newBoxes = boxes.toMutableList()
            newBoxes.remove(box)
            addBoxes(newBoxes, first, second, third)
            first.remove(box)
        }
    }

    return
}