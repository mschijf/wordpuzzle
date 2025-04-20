package ms.wordpuzzle

import java.io.File

class WordList(path: String = "data/", fileName: String = "wordlist.txt") {

    private val wordList = getAllTransferedWords(File(path+fileName))

    fun getWordsOfLength(length: Int): List<String> {
        return wordList
            .filter { it.count() == length }
            .distinct()
    }

    private fun getAllTransferedWords(fromFile: File): List<String> {
        return fromFile
            .readLines()
            .map{ it.removeAccents() }
            .filter { it.all { letter -> letter in "abcdefghijklmnopqrstuvwxyz" } }
    }

    private fun String.removeAccents(): String {
        val accents = mapOf(
            'é' to 'e', 'è' to 'e', 'ê' to 'e', 'ë' to 'e',
            'á' to 'a', 'à' to 'a', 'â' to 'a', 'ä' to 'a',
            'í' to 'i', 'ì' to 'i', 'î' to 'i', 'ï' to 'i',
            'ó' to 'o', 'ò' to 'o', 'ô' to 'o', 'ö' to 'o',
            'ú' to 'u', 'ù' to 'u', 'û' to 'u', 'ü' to 'u',
            'ç' to 'c', 'ñ' to 'n'
        )
        return this.map { char -> accents[char] ?: char }.joinToString("")
    }

}