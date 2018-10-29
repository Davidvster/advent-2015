package day17

import java.io.File

val possibleCombination = mutableListOf<MutableList<Int>>()

fun main(args: Array<String>) {
    val buckets = mutableListOf<Int>()
    val toFill = 150
    File("/Users/david.valic/IdeaProjects/AdventCalendar/src/day17/day17src.txt").forEachLine { line ->
        buckets.add(line.toInt())
    }
    buckets.sortDescending()
    var combinations = 0
    buckets.forEachIndexed { i, bucket ->
        combinations += combine(buckets.subList(i+1, buckets.size), toFill - bucket, mutableListOf(bucket))
    }
    //part1
    println(combinations)

    //part2
    possibleCombination.sortBy { it.size }
    println(possibleCombination.filter { it.size == possibleCombination[0].size}.size)
}

fun combine(buckets: List<Int>, toFill: Int, usedBuckets: MutableList<Int>): Int {
    var combinations = 0
    usedBuckets.sortDescending()
    if (toFill == 0 ) {
        possibleCombination.add(usedBuckets.toMutableList())
        return 1
    } else {
        buckets.forEachIndexed {i, bucket ->
            if (bucket <= toFill) {
                usedBuckets.add(bucket)
                combinations += combine(buckets.subList(i+1, buckets.size), toFill - bucket, usedBuckets)
                usedBuckets.remove(bucket)
            }
        }
    }
    return combinations
}