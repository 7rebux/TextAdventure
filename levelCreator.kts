import java.io.File
import java.util.UUID
import java.lang.System
import java.util.Arrays

// file name and creation
print("Enter file name (leave blank for random UUID): ")
var fileName = readLine()!!
if (fileName == "")
    fileName = UUID.randomUUID().toString()
if (!fileName.endsWith(".lvl"))
    fileName = fileName + ".lvl"
val file = File("assets/levels", fileName)
if (!file.exists())
    file.createNewFile()
else {
    println("File called $fileName already exists!")
    System.exit(0)
}
println()

// attributes
print("Enter level name: ")
val levelName = readLine()!!
print("Enter character name: ")
val characterName = readLine()!!
println()

// dialog
print("Enter dialog text (use \"\\\\\" for new line): ")
val dialogText = readLine()!!
println()

// answers
val answer = arrayOfNulls<String>(3)
val continuation = arrayOfNulls<String>(3)
print("Enter first answer text: ")
answer[0] = readLine()!!
print("Enter first answer contination level name: ")
continuation[0] = readLine()!!
print("Enter second answer text: ")
answer[1] = readLine()!!
print("Enter second answer contination level name: ")
continuation[1] = readLine()!!
print("Enter third answer text: ")
answer[2] = readLine()!!
print("Enter third answer contination level name: ")
continuation[2] = readLine()!!

// write to file
file.printWriter().use() { it ->
    it.println("§levelName=$levelName")
    it.println("§characterName=$characterName")

    it.println("[DILOAG]$dialogText")

    for (i in 0..2)
        it.println("[ANSWER]${answer[i]}:${continuation[i]}")
}

println("\nSuccessfully created level file!")