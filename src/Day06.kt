fun main() {

    fun firstPacketIndex(dataStream: String, size: Int): Int {
        return dataStream.windowed(size, 1)
            .takeWhile { it.toSet().size < size }
            .size + size
    }
    val offset = 3 + 1 // The missing first three characters + the first successful
    fun part1(input: List<String>): Int {
        return firstPacketIndex(input.first(), 4)
    }

    fun part2(input: List<String>): Int {
        return firstPacketIndex(input.first(), 14)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 19)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
