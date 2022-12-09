
data class Instruction(val number: Int, val fromColumn: Int, val toColumn: Int)

fun main() {
    fun part1(input: List<String>): String {
        // build a map of the towers
        val towers = input.takeWhile { it.isNotBlank() }.reversed()
            .map { it
                .windowed(3, 4, true)
                .mapIndexed{ i, c -> i+1 to c[1]}
            }
        val towerMap = towers.take(1)
            .flatMap { it.map { i -> i.first to mutableListOf<Char>() } }
            .toMap().toMutableMap()
        towers.drop(1).forEach { row ->
            row.forEach { if (it.second.isLetter()) towerMap[it.first]?.add(it.second) }
        }

        // parse and execute the moves
        input.takeLastWhile { it.isNotBlank() }
            .map{ row ->
                val firstSplit = row.split(" from ")
                val number = firstSplit.first().drop(5).toInt()
                val (fromColumn, toColumn) = firstSplit.last().split(" to ").map { it.toInt() }
                Instruction(number, fromColumn, toColumn)
            }.forEach {
                val (num, from, to) = it
                val movedStack = towerMap[from]?.takeLast(num)?.reversed()?.toMutableList() ?: mutableListOf()
                towerMap[from] = towerMap[from]?.dropLast(num)?.toMutableList() ?: mutableListOf()

                towerMap[to]?.addAll(movedStack)
            }
        return towerMap.values.map { it.last() }.joinToString("")
    }

    fun part2(input: List<String>): String {
        // build a map of the towers
        val towers = input.takeWhile { it.isNotBlank() }.reversed()
            .map { it
                .windowed(3, 4, true)
                .mapIndexed{ i, c -> i+1 to c[1]}
            }
        val towerMap = towers.take(1)
            .flatMap { it.map { i -> i.first to mutableListOf<Char>() } }
            .toMap().toMutableMap()
        towers.drop(1).forEach { row ->
            row.forEach { if (it.second.isLetter()) towerMap[it.first]?.add(it.second) }
        }

        // parse and execute the moves
        input.takeLastWhile { it.isNotBlank() }
            .map{ row ->
                val firstSplit = row.split(" from ")
                val number = firstSplit.first().drop(5).toInt()
                val (fromColumn, toColumn) = firstSplit.last().split(" to ").map { it.toInt() }
                Instruction(number, fromColumn, toColumn)
            }.forEach {
                val (num, from, to) = it
                val movedStack = towerMap[from]?.takeLast(num)?.toMutableList() ?: mutableListOf()
                towerMap[from] = towerMap[from]?.dropLast(num)?.toMutableList() ?: mutableListOf()

                towerMap[to]?.addAll(movedStack)
            }
        return towerMap.values.map { it.last() }.joinToString("")
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
