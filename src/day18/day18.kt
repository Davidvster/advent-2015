package day18

import java.io.File

private const val gridSize = 100

fun main(args: Array<String>) {
    var grid = List (gridSize) { MutableList(gridSize) {0} }
    var currentLine = 0
    File("/Users/david.valic/IdeaProjects/AdventCalendar/src/day18/day18src.txt").forEachLine { line ->
        line.forEachIndexed { index, c ->
            when (c) {
                "#".single() -> grid[currentLine][index] = 1
            }
        }
        currentLine ++
    }

    (0..99).forEach {
        val newGrid = grid.map { it.toMutableList() }
        for (i in 0 until gridSize) {
            for (j in 0 until gridSize) {
                //part 1 without the if line, part2 needs the if line
                if (!((i == 0 && j == 0) || (i == 0 && j == gridSize-1) || (i == gridSize-1 && j == 0) || (i == gridSize-1 && j == gridSize-1))) {
                    var neighboursOn = 0
                    neighboursOn += grid.getOrNull(i+1)?.getOrNull(j)?:0
                    neighboursOn += grid.getOrNull(i+1)?.getOrNull(j+1)?:0
                    neighboursOn += grid.getOrNull(i+1)?.getOrNull(j-1)?:0
                    neighboursOn += grid.getOrNull(i-1)?.getOrNull(j)?:0
                    neighboursOn += grid.getOrNull(i-1)?.getOrNull(j+1)?:0
                    neighboursOn += grid.getOrNull(i-1)?.getOrNull(j-1)?:0
                    neighboursOn += grid.getOrNull(i)?.getOrNull(j+1)?:0
                    neighboursOn += grid.getOrNull(i)?.getOrNull(j-1)?:0
                    if (grid[i][j] == 1 && neighboursOn != 2 && neighboursOn != 3) {
                        newGrid[i][j] = 0
                    } else if (grid[i][j] == 0 && neighboursOn == 3) {
                        newGrid[i][j] = 1
                    }
                }
            }
        }
        grid = newGrid
    }

    println(grid.sumBy { it.sumBy { it } })
    
}