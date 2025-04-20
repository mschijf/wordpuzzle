package ms.wordpuzzle

import tool.mylambdas.distinct
import kotlin.random.Random

class AlgorithmQuality(private val wordList: List<String>) {

    private val allResulCombinations = allResults(wordList.first().length)
    private val gameRules = GameRules()

    fun maxAttemptsNecessary(): Int {
        val map = wordList
            .map { word -> word  to wordList.maxAttemptsNecessaryWithWord(word) }
            .sortedByDescending { it.second }

        return map.first().second
    }

    private fun List<String>.maxAttemptsNecessaryWithWord(chosenWord: String): Int {
        println("$chosenWord")
        var workingList = this
        var attemptCount = 0
        while (workingList.size > 1) {
//            println("$attemptCount")
            val guess = guessAlgorithm2(workingList)
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

    fun guessAlgorithm2(list: List<String>): String {
        var mini = Int.MAX_VALUE
        var bestWord = ""
        list.forEach { word ->
            println("   examine word: $word")
            var maxi = -1
            allResulCombinations.forEach { result ->
                val resultListSize = gameRules.filterList(list, word, result).size
                if (resultListSize > maxi) {
                    maxi = resultListSize
                }
            }
            if (maxi < mini) {
                mini = maxi
                bestWord = word
            }
        }
        return bestWord


//        return list
//            .minBy { word -> allResulCombinations.maxOf { result -> gameRules.filterList(list, word, result).size }  }
    }

    fun allResults(size: Int): List<List<LetterAnswerColor>> {
        return if (size == 0) {
            listOf(emptyList())
        } else {
            allResults(size-1).map { it + LetterAnswerColor.RED } +
            allResults(size-1).map { it + LetterAnswerColor.ORANGE } +
            allResults(size-1).map { it + LetterAnswerColor.GREEN }
        }
    }

    fun doGuessRandom(list: List<String>): String {
        return list[Random.nextInt(0, list.size)]
    }


}