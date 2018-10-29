package day10

fun main(args: Array<String>) {
    var starInput =  "1113222113"
    var newNumber = starInput

    for (x in 0 until 50) {
        var input = newNumber
        newNumber = ""
        var i = 0
        while (i < input.length) {
            var count = 0
            while(count + i < input.length && input[i+count] == input[i]) {
                count ++
            }
            newNumber += "$count${input[i]}"

            i += count
        }
//        println(newNumber)
    }
    println(newNumber.length)
}