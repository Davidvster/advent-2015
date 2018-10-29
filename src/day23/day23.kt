package day23

import java.io.File

class Command(val name: String,
              val registry: String?,
              val offset: Int?)

fun main(args: Array<String>) {
    val commands = mutableListOf<Command>()
    val registrys = mutableMapOf<String, Long>()
    File("/Users/david.valic/IdeaProjects/AdventCalendar/src/day23/day23src.txt").forEachLine { line ->
        val l = line.replace(",", "").split(" ")
        if (l[0] != "jmp") {
            commands.add(Command(l[0], l[1], l.getOrNull(2)?.toInt()))
            if (registrys.containsKey(l[1]).not()) {
                registrys[l[1]] = 0
            }
        } else {
            commands.add(Command(l[0], null, l[1].toInt()))
        }
    }

    //part2
    registrys["a"] = 1

    var i = 0
    while (i < commands.size) {
        when (commands[i].name) {
            "inc" -> {
                registrys[commands[i].registry!!] = registrys[commands[i].registry!!]!! + 1
                i ++
            }
            "hlf" -> {
                registrys[commands[i].registry!!] = registrys[commands[i].registry!!]!! / 2
                i ++
            }
            "tpl" -> {
                registrys[commands[i].registry!!] = registrys[commands[i].registry!!]!! * 3
                i ++
            }
            "jmp" -> i += commands[i].offset?: 0
            "jie" -> {
                if (registrys[commands[i].registry!!]!! %2 == 0L) {
                    i += commands[i].offset?: 0
                } else {
                    i ++
                }
            }
            "jio" -> {
                if (registrys[commands[i].registry!!]!! == 1L) {
                    i += commands[i].offset?: 0
                } else {
                    i++
                }
            }
        }
    }
    println(registrys["b"])
}