package ms.wordpuzzle.lingo

import tool.mylambdas.distinct
import kotlin.random.Random

class AlgorithmQuality(private val wordList: List<String>) {

    private val gameRules = GameRules()
    private val firstGuess2 = guessAlgorithm2(wordList, wordList)

    fun maxAttemptsNecessary(): Int {
        println("first guess = $firstGuess2")
        val map = wordList
            .map { word -> word  to wordList.maxAttemptsNecessaryWithWord(word) }
            .sortedByDescending { it.second }

        return map.first().second
    }

    private fun List<String>.maxAttemptsNecessaryWithWord(chosenWord: String): Int {
        println("$chosenWord")
        var workingList = this
        var attemptCount = 0
        val usedWordList = mutableListOf<String>()
        while (workingList.size > 1) {
//            println("$attemptCount")

            val guess = if (attemptCount == 0) firstGuess2 else guessAlgorithm2(workingList, this-usedWordList)
            usedWordList.add(guess)

            val answer = gameRules.verify(guess, chosenWord)
            workingList = gameRules.filterList(workingList, guess, answer)
            attemptCount++
            if (attemptCount > 100) {
                println("$chosenWord duurt te lang")
                break
            }
        }
        if (workingList.isEmpty()) {
            throw Exception("algorithm gives a result of zero possibilities after $attemptCount attempts")
        }
        return attemptCount
    }

    fun guessAlgorithm1(list: List<String>): String {
        val letterCount = list
            .joinToString("") { it.distinct() }
            .groupingBy { it }.eachCount()
        return list
            .maxBy { it.distinct().sumOf { letter -> letterCount[letter] ?: 0 } }
    }

    fun guessAlgorithm2(possibleList: List<String>, chooseFromList: List<String>): String {
        val xx = chooseFromList.associateWith { guessWord ->
            possibleList.map { chosenWord -> gameRules.verify(guessWord, chosenWord) }
                    .groupingBy { it }.eachCount()
                    .values.max()
        }
        val bestWord = xx.minBy { it.value }.key
//        println(bestWord)
        return bestWord
    }

    fun doGuessRandom(list: List<String>): String {
        return list[Random.nextInt(0, list.size)]
    }


}