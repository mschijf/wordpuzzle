package ms.wordpuzzle.tweevoortwaalf

class TweeVoorTwaalf(private val wordList: List<String>) {
    fun getPossibleWordsFor(correctLetters: String): List<String> {
        return wordList.filter { word ->
            correctLetters.all{ letter -> word.contains(letter) }
        }
    }
}