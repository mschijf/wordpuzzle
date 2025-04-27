package ms.wordpuzzle.lingo

class GameRules {

    fun verify(guess: String, chosenWord: String): List<LetterAnswerColor> {
        check(guess.length == chosenWord.length) { "guess length must be equal to chosenWord length" }

        val answer = MutableList(chosenWord.length) { LetterAnswerColor.NONE }
        val newWord = chosenWord.toMutableList()

        guess.forEachIndexed { index, letter ->
            if (letter == newWord[index]) {
                answer[index] = LetterAnswerColor.GREEN
                newWord[index] = '@'
            }
        }
        guess.forEachIndexed { index, letter ->
            if (answer[index] == LetterAnswerColor.NONE) {
                val find = newWord.indexOfFirst { it == letter }
                if (find < 0) {
                    answer[index] = LetterAnswerColor.RED
                } else {
                    answer[index] = LetterAnswerColor.ORANGE
                    newWord[find] = '@'
                }
            }
        }
        check(answer.none{ it == LetterAnswerColor.NONE } ) { "Still NONEs in answer for $guess and chosenWord: $chosenWord" }
        return answer
    }

    fun filterList(list: List<String>, guessWord: String, result: List<LetterAnswerColor>): List<String> {
        return list.filter { word -> word.isPossible(guessWord, result) }
    }

    private fun String.isPossible(guessWord: String, result: List<LetterAnswerColor>): Boolean {
        val goodLetters = guessWord.filterIndexed { index, _ -> result[index] == LetterAnswerColor.ORANGE || result[index] == LetterAnswerColor.GREEN }
        val redLetters = guessWord.filterIndexed { index, _ -> result[index] == LetterAnswerColor.RED }

        result.forEachIndexed { index, c ->
            when (c) {
                LetterAnswerColor.RED -> if ((guessWord[index] == this[index]) or (guessWord[index] in this && guessWord[index] !in goodLetters)) { return false}
                LetterAnswerColor.GREEN -> if (guessWord[index] != this[index]) { return false}
                LetterAnswerColor.ORANGE -> if (guessWord[index] == this[index] || guessWord[index] !in this) { return false}
                else -> throw Exception("unexpected result '$c' for letter in result '$result'")
            }
        }

        val letterMap = goodLetters.groupingBy { it }.eachCount()
        letterMap.forEach { (goodLetter, countGood) -> if (goodLetter in redLetters && this.count { it == goodLetter } > countGood) { return false }}

        return true
    }

}

enum class LetterAnswerColor(val colorLetter: Char) {
    RED('r'),
    GREEN('g'),
    ORANGE('o'),
    NONE(' ')
}