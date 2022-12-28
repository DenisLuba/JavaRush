package Test

import java.util.*

class Kata {
    companion object {
        fun sum(numbers: IntArray): Int = numbers.filter { i -> i > 0 }.sum()

        fun repeatStr(r: Int, str: String): String = str.repeat(r)

        fun numberToString(num: Int): String = "$num"

        fun abbrevName(name: String) =
            name.replace("(\\w)\\w*\\s(\\w)\\w*".toRegex(), "$1.$2").uppercase(Locale.getDefault())

        fun calculateYears(years: Int): Array<Int> = when (years) {
            1 -> arrayOf(1, 15, 15)
            else -> arrayOf(years, 24 + (years - 2) * 4, 24 + (years - 2) * 5)
        }

        fun getAscii(c: Char): Int = c.code

        fun sum(mixed: List<Any>): Int = mixed.sumOf { it.toString().toInt() }

        fun expressionsMatter(a: Int, b: Int, c: Int): Int =
            intArrayOf(a, b, c).fold(0) { i, j -> if (i <= 1 || j == 1) i + j else i * j }
                .coerceAtLeast(intArrayOf(a, b, c).foldRight(0) { i, j -> if (i <= 1 || j <= 1) i + j else i * j })
    }
    fun main() {
        val numbers: IntArray = intArrayOf(1, -1, 2, -2, 3)

//    println(sum(numbers))
//    println(repeatStr(4, "Hello"))
//    println(numberToString(123))
//    println(abbrevName("P Favuzzi"))
//    calculateYears(10).forEach {i -> print("$i ")}
//    println()
//    println(getAscii('A'))
//    println(sum(listOf(1, "2", "3", 4, "c")))
        println(expressionsMatter(1, 2, 3))
    }



}

