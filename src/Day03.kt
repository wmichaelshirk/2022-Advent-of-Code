

fun main() {

    fun findPriority (c: Char): Int {
        return if (c.isUpperCase()) {
            c.code - 64 + 26
        } else {
            c.code - 96
        }
    }

    fun part1(input: List<String>): Int {
        return input
            .map { it.chunked(it.length / 2) }
            .map { it.first().toSet().intersect(it.last().toSet()).first() }
            .sumOf { findPriority(it) }
    }

    fun part2(input: List<String>): Int {
        return input.windowed(3, 3)
            .map { it[0].toSet().intersect(it[1].toSet())
                .intersect(it[2].toSet()).first() }
                .sumOf { findPriority(it) }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
