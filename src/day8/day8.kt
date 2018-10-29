package day8

import java.io.File

fun main(args: Array<String>) {
    var length = 0
    File("/Users/david.valic/IdeaProjects/AdventCalendar/src/day8/day8src.txt").forEachLine { line ->
        val totalLength = line.length
        //part1
//        var removedLength = 0
//        //remove \\
//        var a = line.split("\\\\")
//        removedLength += a.size - 1
//        var b = ""
//        a.map { b += it }
//
//        // remove \"
//        a = listOf()
//        a = b.split("\\\"")
//        removedLength += a.size - 1
//        b = ""
//        a.map { b += it }
//
//        // remove \xXX
//        a = b.split(Regex("""\\x[a-zA-Z0-9]{2}"""))
//        removedLength += a.size - 1
//        b = ""
//        a.map { b += it }
//
//        // remove "
//        a = b.split("\"")
//        b = ""
//        a.map { b += it }
//
//        val endLength = b.length + removedLength
//
//        length += totalLength - endLength

        //part2
        var addedLength = 0
        //remove \\
        var a = line.split("\\\\")
        addedLength += (a.size - 1) * 4
        var b = ""
        a.map { b += it }

        // remove \"
        a = b.split("\\\"")
        addedLength += (a.size - 1) * 4
        b = ""
        a.map { b += it }

        // remove \xXX
        a = b.split(Regex("""\\x[a-zA-Z0-9]{2}"""))
        addedLength += (a.size - 1) * 5
        b = ""
        a.map { b += it }

        // remove "
        a = b.split("\"")
        addedLength += (a.size - 1) * 3
        b = ""
        a.map { b += it }

        val endLength = b.length + addedLength

        length += endLength - totalLength

    }
    println(length)
}