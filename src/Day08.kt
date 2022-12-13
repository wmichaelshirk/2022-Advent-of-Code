fun <T> Iterable<T>.takeUntil(predicate: (T) -> Boolean): Iterable<T> {
    var shouldContinue = true
    return takeWhile {
        val result = shouldContinue
        shouldContinue = !predicate(it)
        result
    }
}

fun main() {

    fun part1(input: List<String>): Int {
        val width = input[0].length
        val length = input.size

        return (0 until (width * length))
            .count { index ->
                val x = index % width
                val y = index.floorDiv(width)

                val row = input[y].map { it.digitToInt() }
                val col = input.map { it[x].digitToInt() }
                val hgt = row[x]

                (x == 0) ||
                        (x == width - 1) ||
                        (y == 0) ||
                        (y == length - 1) ||
                        (row.take(x).maxOrNull() ?: -1 < hgt) ||
                        (row.drop(x + 1).maxOrNull() ?: -1 < hgt) ||
                        (col.take(y).maxOrNull() ?: -1 < hgt) ||
                        (col.drop(y + 1).maxOrNull() ?: -1 < hgt)
            }
    }

    fun part2(input: List<String>): Int {
        val width = input[0].length
        val length = input.size

        val maxScore = (0 until (width * length))
            .maxOfOrNull { index ->
                val x = index % width
                val y = index.floorDiv(width)

                val row = input[y].map { it.digitToInt() }
                val col = input.map { it[x].digitToInt() }
                val hgt = row[x]

                val north = col.take(y).reversed().takeUntil { it >= hgt }.count()
                val south = col.drop(y + 1).takeUntil { it >= hgt }.count()
                val west = row.take(x).reversed().takeUntil { it >= hgt }.count()
                val east = row.drop(x + 1).takeUntil { it >= hgt }.count()

                north * west * east * south

            } ?: -1
        return maxScore
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    check(part1(testInput) == 21)
    check(part2(testInput) == 8)

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}
