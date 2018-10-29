package day5

import java.io.File

fun main(args: Array<String>) {
    var niceStrings = 0
    File("/Users/david.valic/IdeaProjects/AdventCalendar/src/day5/day5src.txt").forEachLine { line ->
        //part1
//        var isNice = true
//        if (line.contains(Regex("(ab|cd|pq|xy)"))) {
//            isNice = false
//        }
//        if (isNice) {
//            if (line.count { it == "a".single() || it == "e".single() || it == "i".single() || it == "o".single() || it == "u".single()} < 3) {
//                isNice = false
//            }
//            if (isNice) {
//                if (line.contains(Regex("(qq|ww|ee|rr|tt|yy|uu|ii|oo|pp|aa|ss|dd|ff|gg|hh|jj|kk|ll|zz|xx|cc|vv|bb|nn|mm)"))) {
//                    niceStrings ++
//                }
//            }
//        }
        //part2
        var isNice1 = false
        var isNice2 = false
        line.forEachIndexed { i, c ->
            if (i < line.length) {
                for (j in i+1 until line.length) {
                    if (line[i] == line[j] && j - i == 2) {
                        isNice1 = true
                    }
                    if (i < line.length-1 && j < line.length-1 && line.substring(i, i+2) == line.substring(j, j+2) && j - i > 1) {
                        isNice2 = true
                    }
                }
            }
        }
        if (isNice1 && isNice2) {
            niceStrings++
        }
    }
    println(niceStrings)
}
