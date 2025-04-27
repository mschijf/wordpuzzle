import ms.wordpuzzle.lingo.AlgorithmQuality
import ms.wordpuzzle.lingo.GameRules
import ms.wordpuzzle.WordList
import ms.wordpuzzle.tweevoortwaalf.TweeVoorTwaalf

fun main() {
    val wordList = WordList()

    val tvt = TweeVoorTwaalf(wordList.getWordsOfLength(12))
    println(tvt.getPossibleWordsFor("flapsikoea"))

//    val gameRules = GameRules()
//    val wordlistFive = wordList.getWordsOfLength(5)
//    val algorithmQuality = AlgorithmQuality(wordlistFive)
//
//    checkAlgorithm(wordlistFive)
//
//    var i = 1
//    var runningList = wordlistFive
//    while (runningList.count() > 1) {
//        val guessWord = algorithmQuality.guessAlgorithm1(runningList)
//        println("word:   $guessWord")
//        print("result: ")
//        val result = readln().lowercase().map { letter -> LetterAnswerColor.entries.first { it.colorLetter == letter}}
//        runningList = gameRules.filterList(runningList, guessWord, result)
//        println(runningList.size)
//        i++
//    }
//    println ("only possible word is ${runningList.firstOrNull()}")
}

fun checkAlgorithm(wordList: List<String>) {
    val algorithmQuality = AlgorithmQuality(wordList)
    println(algorithmQuality.maxAttemptsNecessary () )
}


