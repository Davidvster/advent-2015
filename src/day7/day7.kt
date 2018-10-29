package day7

import java.io.File

data class Circuit(val name: String,
              var value: String? = null,
              var one: String? = null,
              var two: String? = null,
              var shift: Int? = null,
              var operator: String? = null)


private var circuits = mutableListOf<Circuit>()

fun main(args: Array<String>) {

    File("/Users/david.valic/IdeaProjects/AdventCalendar/src/day7/day7src.txt").forEachLine { line ->
        val l = line.split(" ")

        when {
            l.contains("AND") || l.contains("OR") -> {
                val circuit = Circuit(l[4], null, l[0], l[2], null, l[1])
                circuits.add(circuit)
            }
            l.contains("RSHIFT") || l.contains("LSHIFT") -> {
                val circuit = Circuit(l[4], null, l[0], null, l[2].toInt(), l[1])
                circuits.add(circuit)
            }
            l.contains("NOT") -> {
                val circuit = Circuit(l[3], null, l[1], null, null, l[0])
                circuits.add(circuit)
            }
            else -> {
                val circuit = Circuit(l[2], l[0])
                circuits.add(circuit)
            }
        }
    }
    //part1
    var circuitsCopy = circuits.map { it.copy(name = it.name, value = it.value, one = it.one, two = it.two, shift = it.shift, operator = it.operator) }.toMutableList()
    val a1 = compute(circuits.find { it.name == "a" }!!)
    println(a1)

    //part2
    circuits = circuitsCopy.map { it.copy(name = it.name, value = it.value, one = it.one, two = it.two, shift = it.shift, operator = it.operator) }.toMutableList()
    circuits.find { it.name == "b" }?.value = a1.toString()
    val a2 = compute(circuits.find { it.name == "a" }!!)
    println(a2)
}

private fun compute(circuit: Circuit?): Long? {
    if (circuit?.value != null) {
        return when (circuit.value!![0].isDigit()) {
            true -> circuit.value!!.toLong()
            false -> compute(circuits.find { it.name == circuit.value})
        }
    } else {
        when (circuit?.operator) {
            "AND" -> {
                val one = findValue(circuit.one)
                val two = findValue(circuit.two)
                circuits.find { it.name == circuit.name }?.value = one?.and(two!!).toString()
                return one?.and(two!!)
            }
            "OR" -> {
                val one = findValue(circuit.one)
                val two = findValue(circuit.two)
                circuits.find { it.name == circuit.name }?.value = one?.or(two!!).toString()
                return one?.or(two!!)
            }
            "RSHIFT" -> {
                val one = findValue(circuit.one)
                circuits.find { it.name == circuit.name }?.value = one?.shr(circuit.shift!!).toString()
                return one?.shr(circuit.shift!!)
            }
            "LSHIFT" -> {
                val one = findValue(circuit.one)
                circuits.find { it.name == circuit.name }?.value = one?.shl(circuit.shift!!).toString()
                return one?.shl(circuit.shift!!)
            }
            "NOT" -> {
                val one = findValue(circuit.one)
                circuits.find { it.name == circuit.name }?.value = one?.inv().toString()
                return one?.inv() // 65535 - one
            }
        }
    }
    return null
}

private fun findValue(v: String?) : Long? {
     return when(v!![0].isDigit()) {
        true -> v.toLong()
        false -> compute(circuits.find { it.name == v }!!)
    }
}
