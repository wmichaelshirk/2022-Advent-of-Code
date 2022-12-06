import kotlin.math.max

fun main() {


    fun inputToRangePairs (input: String): Pair<IntRange, IntRange> {
        val pairList = input.split(",")
            .map { p ->
                p.split("-")
                    .map { p -> p.toInt() }
            }
            .map { p ->
                p.first()..p.last()
            }
        return  pairList.first() to pairList.last()
    }

    fun part1(input: List<String>): Int {
        return input
            .map { inputToRangePairs(it) }
            .count { it.first.union(it.second).count() == max(it.first.count(), it.second.count()) }
    }

    fun part2(input: List<String>): Int {
        return input.map { inputToRangePairs(it) }
            .count {
                it.first.intersect(it.second).isNotEmpty()
            }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
