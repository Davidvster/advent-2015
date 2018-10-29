package day25

fun main(args: Array<String>) {
    val row = 2981
    val column = 3075
    var code = 0
    val diagonal = row + column-1
    (1.rangeTo(diagonal)).forEach {
        code += it
    }
    code -= (diagonal - column)
    println(code)

    var value = 20151125L
    (1 until code).forEach {
        value = (value * 252533L) % 33554393L
//        println(it)
    }
    println(value)
//    println((31916031L * 252533L).toLong() % 33554393.toLong())
}
