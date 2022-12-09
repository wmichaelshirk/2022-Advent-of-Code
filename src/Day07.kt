
interface FileOrDirectory {
    val size: Int
}

data class File(val name: String, override val size: Int): FileOrDirectory
class Directory(val name: String): FileOrDirectory {

    private var files = mutableListOf<FileOrDirectory>()
    fun addFile(newFile: FileOrDirectory) {
        files.add(newFile)
    }

    val directories: List<Directory>
        get() {
            return files.filterIsInstance<Directory>()
        }
    override val size: Int
        get() {
            return files.sumOf { it.size }
        }

    override fun toString(): String {
        return "$name: $size"
    }

    val flatten : List<Directory>
        get() {
            return listOf(this) + directories.flatMap { it.flatten }
        }

}

fun main() {

    var root: Directory = Directory("/")

    fun parse(input: List<String>) {
        root = Directory("/")
        val directories = mutableListOf<Directory>()
        var currentDirectory = root
        input.forEach {
            if (it.contains("$ cd")) {
                val dirName = it.split(" ").last()
                currentDirectory = if (dirName == "..") {
                    val newCur = directories.removeLast()
                    newCur
                } else if (dirName == "/") {
                    root
                } else {
                    directories.add(currentDirectory)
                    val newCur = currentDirectory.directories.first { f -> f.name == dirName }
                    newCur
                }
            } else if (it.contains("$ ls")) {
            } else {
                // It's a file.
                val (size, filename) = it.split(" ")
                if (size != "dir") {
                    val parsedSize = size.toInt()
                    val newFile = File(filename, parsedSize)
                    currentDirectory.addFile(newFile)
                } else {
                    val newFile = Directory(filename)
                    currentDirectory.addFile(newFile)
                }
            }
        }
    }

    fun part1(input: List<String>): Int {
        parse(input)
        return root.flatten.filter { it.size <= 100000 }.sumOf { it.size }
    }

    fun part2(input: List<String>): Int {
        val diskSize = 70000000
        val needed = 30000000

        parse(input)

        val available = diskSize - (root.flatten.find { it.name == "/" }?.size ?: 0)
        val needToRemove = needed - available

        return root.flatten.map { it.size }.filter { it > needToRemove }.min()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 95437)
    check(part2(testInput) == 24933642)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
