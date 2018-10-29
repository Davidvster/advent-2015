package day19

import java.io.File

fun main(args: Array<String>) {
    var medicine = ""
    val replacements = HashMap<String, String>()
    var nextMedicine = false
    File("/Users/david.valic/IdeaProjects/AdventCalendar/src/day19/day19src.txt").forEachLine { line ->
        when {
            line == "" -> nextMedicine = true
            nextMedicine -> medicine = line
            else -> {
                val l = line.split(" ")
                replacements[l[2]] = l[0]
            }
        }
    }
    //part1
    val calibratedMedicines = mutableListOf<String>()
    replacements.forEach { replacement, original ->
        medicine.forEachIndexed { index, c ->
            val twoParts = medicine.substring(index)
            val newPart = twoParts.replaceFirst(original, replacement)
            val calibrated = medicine.substring(0, index) + newPart
            if (!calibratedMedicines.contains(calibrated) && calibrated != medicine) {
                calibratedMedicines.add(calibrated)
            }
        }
    }

    //part2 recursion

    println(calibratedMedicines.size)
}