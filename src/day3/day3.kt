package day3

import java.io.File

private val houses = HashMap<Pair<Int, Int>, Int>()
private var presents = 0

fun main(args: Array<String>) {

    var sx = 0
    var sy = 0

    var rx = 0
    var ry = 0

    houses[Pair(sx, sy)] = 1
    presents ++
    File("/Users/david.valic/IdeaProjects/AdventCalendar/src/day3/day3src.txt").forEachLine { line ->
        line.forEachIndexed { i, char ->
            var tx = rx
            var ty = ry
            if (i %2 == 0) {
                tx = sx
                ty = sy
            }
            when (char) {
                '^' -> {
                    ty++
                    presents += visitHouse(tx, ty)
                }
                'v' -> {
                    ty--
                    presents += visitHouse(tx, ty)
                }
                '>' -> {
                    tx++
                    presents += visitHouse(tx, ty)
                }
                '<' -> {
                    tx--
                    presents += visitHouse(tx, ty)
                }
            }
            if (i %2 == 0) {
                sx = tx
                sy = ty
            } else {
                rx = tx
                ry = ty
            }
        }
    }
    println(presents)
}

fun visitHouse(x: Int, y: Int): Int {
    if (houses[Pair(x, y)] == null) {
        houses[Pair(x, y)] = 1
        return 1
    }
    return 0
}