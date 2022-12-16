import kotlin.math.abs

fun main() {

    val dirs = mapOf(
        "U" to (0 to -1),
        "R" to (1 to 0),
        "D" to (0 to 1),
        "L" to (-1 to 0)
    )

    fun visualize(rope: List<Pair<Int, Int>>) {
        for (y in -10 .. 10) {
            for (x in -10..10) {
                val knot = rope.indexOf(x to y)
                if (knot == -1) print (".")
                else if (knot == 0) print("H")
                else print(knot)
            }
            print("\n")
        }
    }

    fun next(head: Pair<Int, Int>, tail: Pair<Int, Int>):Pair<Int, Int> {
        val oldX = tail.first
        val oldY = tail.second
        var newX = tail.first
        var newY = tail.second
        if (head.first == oldX && head.second != oldY) {
            newY = if (head.second > oldY) head.second - 1 else head.second + 1
        } else if (head.second == oldY && head.first != oldX) {
            newX = if (head.first > oldX) head.first - 1 else head.first + 1
        } else if (abs(head.first - oldX) + abs(head.second - oldY) > 2) {
            // move diagonal...
            // ..H  .*H   ..H
            // .*.  T..   ..*
            // T..  ...   .T,
            if (abs(head.first - oldX) == 2) {
                newY = if (head.second < oldY) oldY - 1 else oldY + 1
                newX = if (head.first > oldX) head.first - 1 else head.first + 1
            } else {
                newX = if (head.first < oldX) oldX - 1 else oldX + 1
                newY = if (head.second > oldY) head.second - 1 else head.second + 1
            }
        }
        return newX to newY
    }
    fun part1(input: List<String>): Int {
        var tails = mutableSetOf(0 to 0)

        var head = 0 to 0
        var tail = 0 to 0
        input.forEach {
            val (dir, times) = it.split(" ")
            repeat(times.toInt()) {
                head = (head.first + (dirs[dir]?.first ?: 0)) to (head.second + (dirs[dir]?.second ?: 0))
                tail = next(head, tail)
                tails.add(tail)
            }
        }
        return tails.size
    }


    fun part2(input: List<String>): Int {
        var tails = mutableSetOf(0 to 0)

        var rope = List(10) { 0 to 0 }
        var tail = 0 to 0

        input.forEach {
            val (dir, times) = it.split(" ")
            repeat(times.toInt()) { _ ->
                var head = rope.first().first + (dirs[dir]?.first ?: 0) to rope.first().second + (dirs[dir]?.second ?: 0)
                var prev = head
                rope = rope.mapIndexed { idx, knot ->
                    if (idx == 0) head
                    else {
                        prev = next(prev, knot)
                        prev
                    }

                }
                tail = rope.last()
                tails.add(tail)
            }

        }
        return tails.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test")
    val testInput2 = readInput("Day09_test2")
    check(part1(testInput) == 13)
    check(part2(testInput2) == 36)

    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}
