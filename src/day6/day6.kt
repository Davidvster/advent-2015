package day6

import java.io.File

fun main(args: Array<String>) {
    var lightsTurnedOn = 0
    var grid = Array(1000) {Array(1000) {0}}
    File("/Users/david.valic/IdeaProjects/AdventCalendar/src/day6/day6src.txt").forEachLine { line ->
        //part1
//        if(line.contains("turn on")) {
//            val start = line.substring(8, line.indexOf("through")-1).split(",")
//            val end = line.substring(line.indexOf("through") + 8).split(",")
//            for (i in start[0].toInt() until end[0].toInt()+1) {
//                for (j in start[1].toInt() until end[1].toInt()+1) {
//                    grid[i][j] = 1
//                }
//            }
//        } else if (line.contains("turn off")) {
//            val start = line.substring(9, line.indexOf("through")-1).split(",")
//            val end = line.substring(line.indexOf("through") + 8).split(",")
//            for (i in start[0].toInt() until end[0].toInt()+1) {
//                for (j in start[1].toInt() until end[1].toInt()+1) {
//                    grid[i][j] = 0
//                }
//            }
//        } else if (line.contains("toggle")) {
//            val start = line.substring(7, line.indexOf("through")-1).split(",")
//            val end = line.substring(line.indexOf("through") + 8).split(",")
//            for (i in start[0].toInt() until end[0].toInt()+1) {
//                for (j in start[1].toInt() until end[1].toInt()+1) {
//                    grid[i][j] = Math.abs(grid[i][j] - 1)
//                }
//            }
//        }
        when {
            line.contains("turn on") -> {
                val start = line.substring(8, line.indexOf("through")-1).split(",")
                val end = line.substring(line.indexOf("through") + 8).split(",")
                for (i in start[0].toInt() until end[0].toInt()+1) {
                    for (j in start[1].toInt() until end[1].toInt()+1) {
                        grid[i][j] ++
                    }
                }
            }
            line.contains("turn off") -> {
                val start = line.substring(9, line.indexOf("through")-1).split(",")
                val end = line.substring(line.indexOf("through") + 8).split(",")
                for (i in start[0].toInt() until end[0].toInt()+1) {
                    for (j in start[1].toInt() until end[1].toInt()+1) {
                        if (grid[i][j] > 0) {
                            grid[i][j] --
                        }

                    }
                }
            }
            line.contains("toggle") -> {
                val start = line.substring(7, line.indexOf("through")-1).split(",")
                val end = line.substring(line.indexOf("through") + 8).split(",")
                for (i in start[0].toInt() until end[0].toInt()+1) {
                    for (j in start[1].toInt() until end[1].toInt()+1) {
                        grid[i][j] += 2
                    }
                }
            }
        }
    }
    grid.forEach { lightsTurnedOn += it.sum() }
    println(lightsTurnedOn)
}
