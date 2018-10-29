package day4
import java.security.MessageDigest

fun main(args: Array<String>) {
    val puzzle = "yzbqklnj"
    var notFound = true
    var number = 0L
    while (notFound) {
        number++
        var md5 = hashString("$puzzle$number")
        if (md5.substring(0, 6) == "000000") {
            notFound = false
        }
    }
    println(number)
}

fun hashString( input: String): String {
    val HEX_CHARS = "0123456789ABCDEF"
    val bytes = MessageDigest
            .getInstance("MD5")
            .digest(input.toByteArray())
    val result = StringBuilder(bytes.size * 2)

    bytes.forEach {
        val i = it.toInt()
        result.append(HEX_CHARS[i shr 4 and 0x0f])
        result.append(HEX_CHARS[i and 0x0f])
    }

    return result.toString()
}
