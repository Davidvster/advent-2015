package day11

private const val alphabet = "abcdefghjkmnpqrstuvxyz"

fun main(args: Array<String>) {
    //part1
//    val passwordInput =  "hepxcrrq".toMutableList()
    //part2
    val passwordInput =  ("hepxxyzz").toMutableList()

    val expiredPassword = passwordInput.toMutableList()
    while (!checkPassword(passwordInput.joinToString("")) || passwordInput == expiredPassword) {
//        println(passwordInput.joinToString(""))
        var canIncrement = true
        for (j in passwordInput.size - 1 downTo -1) {
            if (canIncrement) {
                val incremented = increment(passwordInput[j])
                passwordInput[j] = incremented.first
                canIncrement = incremented.second
            }
        }
    }
    println(passwordInput.joinToString(""))
}

fun checkPassword(password: String) : Boolean {
    when {
        password.contains(Regex("i|o|l")) -> return false
        password.split(Regex("(qq|ww|ee|rr|tt|yy|uu|ii|oo|pp|aa|ss|dd|ff|gg|hh|jj|kk|ll|zz|xx|cc|vv|bb|nn|mm)")).size < 3 -> return false
        else -> {
            for (i in 0 until password.length-3) {
                if (alphabet.contains(password.substring(i, i+3))) {
                    return true
                }
            }
        }
    }
    return false
}

fun increment(c: Char) : Pair<Char, Boolean> {
    val i = alphabet.indexOf(c)
    return when {
        i + 1 > alphabet.length - 1 -> {
            Pair(alphabet[0], true)
        }
        else -> {
            Pair(alphabet[i + 1], false)
        }
    }
}