
val scores2 = mapOf<String, Int>(
    "A X" to 3, // Lose = 0, Scissors = 3
    "A Y" to 4, // Draw = 3, Rock = 1
    "A Z" to 8, // Win = 6, Paper = 2
    "B X" to 1, // Lose = 0, Rock = 1
    "B Y" to 5, // Draw = 3, Paper = 2
    "B Z" to 9, // Win = 6, Scissors = 3
    "C X" to 2, // Lose = 0, Paper = 2,
    "C Y" to 6, // Draw = 3, Scissors = 3
    "C Z" to 7, // Win = 6, Rock = 1
)
val scores = mapOf<String, Int>(
    "A X" to 4, // Rock = 1, Draw = 3
    "A Y" to 8, // Paper = 2, Win = 6
    "A Z" to 3, // Scissors = 3, Loss = 0
    "B X" to 1, // Rock = 1, Loss = 0
    "B Y" to 5, // Paper = 2, Draw = 3
    "B Z" to 9, // Scissors = 3, Win = 6
    "C X" to 7, // Rock = 1, Win = 6
    "C Y" to 2, // Paper = 2, Loss = 0
    "C Z" to 6, // Scissors = 3, Draw = 3
)

fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { scores.getOrDefault(it, 0) }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { scores2.getOrDefault(it, 0) }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
