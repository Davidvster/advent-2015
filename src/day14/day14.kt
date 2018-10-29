package day14

import java.io.File

private const val finish = 2503

private data class Reindeer (val speed: Int,
                val duration: Int,
                val rest: Int,
                var runLeft: Int,
                var restLeft: Int,
                var distance: Int = 0,
                var points: Int = 0)

fun main(args: Array<String>) {
    //part1
    val distances = mutableListOf<Int>()
    //part1 + part2
    val reindeers = mutableListOf<Reindeer>()
    File("/Users/david.valic/IdeaProjects/AdventCalendar/src/day14/day14src.txt").forEachLine { line ->
        val l = line.split(" ")
        //part1 faster
//        distances.add(distanceAtFinish(l[3].toInt(), l[6].toInt(), l[13].toInt()))
        // part1 + part2
        reindeers.add(Reindeer(l[3].toInt(), l[6].toInt(), l[13].toInt(), l[6].toInt(), 0))
    }
    for (i in 0 until finish) {
        reindeers.forEach {r ->
            if (r.runLeft > 0) {
                r.distance += r.speed
                r.runLeft --
                if (r.runLeft == 0) {
                    r.restLeft = r.rest
                }
            } else {
                r.restLeft --
                if (r.restLeft == 0) {
                    r.runLeft = r.duration
                }
            }
        }
        val topDistance = reindeers.maxBy { it.distance }?.distance
        reindeers.forEach { r ->
            if (r.distance == topDistance) {
                r.points ++
            }
        }
    }
    //part1
    println(reindeers.maxBy { it.distance }!!.distance)
    //part2
    println(reindeers.maxBy { it.points }!!.points)

    //part1 faster
//    println(distances.max())
}

//part 1 faster
fun distanceAtFinish(speed: Int, duration: Int, rest: Int): Int {
    var distance = 0
    var time = 0
    var canRun = true
    while (time != finish) {
        if (canRun) {
            canRun = false
            if (time + duration >= finish) {
                val dur = finish - time
                distance += dur * speed
                time += dur
            } else {
                distance += duration * speed
                time += duration
            }
        } else {
            if (time + rest >= finish) {
                time += finish - time
            } else {
                time += rest
            }
            canRun = true
        }
    }
    return distance
}