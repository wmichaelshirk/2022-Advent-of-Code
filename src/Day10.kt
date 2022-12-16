
fun main() {

    fun part1(input: List<String>): Int {

        val computation = sequence {
            var x = 1
            var cycle = 1
            for (instruction in input) {
                val splitInstruction = instruction.split(" ")
                val op = splitInstruction.first()
                val arg = splitInstruction.last()
                if (op == "addx") {
                    yield(cycle to x)
                    cycle += 1
                    yield(cycle to x)
                    cycle += 1
                    x += arg.toInt()
                } else {
                    yield(cycle to x)
                    cycle += 1
                }
            }
        }

        val signalSum = computation
            .drop(19)
            .windowed(1, 40, true)
            .take(6)
            .map {
                val pair = it.first()
                pair.first * pair.second
            }
            .sum()

        return signalSum
    }


    fun part2(input: List<String>): Int {

        val computation = sequence {
            var x = 1
            var cycle = 1
            for (instruction in input) {
                val splitInstruction = instruction.split(" ")
                val op = splitInstruction.first()
                val arg = splitInstruction.last()
                if (op == "addx") {
                    yield(cycle to x)
                    cycle += 1
                    yield(cycle to x)
                    cycle += 1
                    x += arg.toInt()
                } else {
                    yield(cycle to x)
                    cycle += 1
                }
            }
        }
        computation.forEach {
            val (cycle, x) = it
            val col = (cycle - 1) % 40
            val sprite = x-1 .. x+1
            if ((cycle - 1) % 40 == 0) {
                print("\n")
            }
            if (col in sprite) {
                print("#")
            } else {
                print(".")
            }

        }

        return 1

    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day10_test")
    check(part1(testInput) == 13140)
    check(part2(testInput) == 1)

    val input = readInput("Day10")
    println(part1(input))
    println(part2(input))
}
