package day12

import netscape.javascript.JSObject
import java.io.File

fun main(args: Array<String>) {
    var sum = 0
    File("/Users/david.valic/IdeaProjects/AdventCalendar/src/day12/day12src.txt").forEachLine { line ->
        //part2
        var ln = line
        while(ln.contains("red")) {
            val index = ln.indexOf("red")
            val startIndex = checkLineBackward(ln, index)
            var endIndex = checkLineForward(ln, index)
            if (endIndex != null) {
                endIndex ++
            }
            if (startIndex == null && endIndex == null) {
                ln = ln.replaceFirst("red", "fin")
            }
            ln = ln.substring(0, startIndex?:index) + ln.substring(endIndex?:index, ln.length)
        }
        //part1 + part2
        val l = ln.replace("\"", ",")
                .replace(":", ",")
                .replace("{", ",")
                .replace("}", ",")
                .replace("[", ",")
                .replace("]", ",")
                .replace(" ", ",")
        val alphabet = Regex("[A-Za-z]")
        val numbers = alphabet.replace(l, "").split(",")
        numbers.forEach {
            if (it.isNotEmpty()) {
                sum += it.toInt()
            }
        }
    }
    println(sum)
}

fun checkLineForward(s: String, red: Int): Int? {
    var curly = 0
    var square = 0
    for (i in red until s.length) {
        when (s[i]) {
            "{".single() -> curly ++
            "}".single() -> curly --
            "[".single() -> square ++
            "]".single() -> square --
        }
        if (curly == -1) {
            return i
        } else if (square == -1) {
            return null
        }
    }
    return null
}

fun checkLineBackward(s: String, red: Int): Int? {
    var curly = 0
    var square = 0
    for (i in red downTo 0) {
        when (s[i]) {
            "{".single() -> curly ++
            "}".single() -> curly --
            "[".single() -> square ++
            "]".single() -> square --
        }
        if (curly == 1) {
            return i
        } else if (square == 1 ) {
            return null
        }
    }
    return null
}