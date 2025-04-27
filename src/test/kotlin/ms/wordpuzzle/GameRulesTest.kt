package ms.wordpuzzle

import ms.wordpuzzle.lingo.GameRules
import ms.wordpuzzle.lingo.LetterAnswerColor
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class GameRulesTest {

 private val gameRules = GameRules()

 @Test
 fun verify1() {
   assertEquals(
    expected=listOf(LetterAnswerColor.GREEN, LetterAnswerColor.GREEN, LetterAnswerColor.GREEN, LetterAnswerColor.GREEN, LetterAnswerColor.GREEN),
    actual=gameRules.verify(guess="lepel", chosenWord="lepel"))
 }

 @Test
 fun verify2() {
  assertEquals(
   expected=listOf(LetterAnswerColor.RED, LetterAnswerColor.GREEN, LetterAnswerColor.RED, LetterAnswerColor.ORANGE, LetterAnswerColor.RED),
   actual=gameRules.verify(guess="lepel", chosenWord="geest"))
 }

 @Test
 fun verify3() {
  assertEquals(
   expected=listOf(LetterAnswerColor.ORANGE, LetterAnswerColor.ORANGE, LetterAnswerColor.ORANGE, LetterAnswerColor.ORANGE, LetterAnswerColor.ORANGE),
   actual=gameRules.verify(guess="raden", chosenWord="arend"))
 }

 @Test
 fun verify4() {
  assertEquals(
   expected=listOf(LetterAnswerColor.ORANGE, LetterAnswerColor.ORANGE, LetterAnswerColor.ORANGE, LetterAnswerColor.RED, LetterAnswerColor.RED),
   actual=gameRules.verify(guess="draad", chosenWord="raden"))
 }

 @Test
 fun verify5() {
  assertEquals(
   expected=listOf(LetterAnswerColor.ORANGE, LetterAnswerColor.ORANGE, LetterAnswerColor.ORANGE, LetterAnswerColor.RED, LetterAnswerColor.RED),
   actual=gameRules.verify(guess="raden", chosenWord="draad"))
 }

 @Test
 fun verify6() {
  assertEquals(
   expected=listOf(LetterAnswerColor.RED, LetterAnswerColor.GREEN, LetterAnswerColor.ORANGE, LetterAnswerColor.RED, LetterAnswerColor.GREEN),
   actual=gameRules.verify(guess="draad", chosenWord="arend"))
 }

 @Test
 fun verify7() {
  assertEquals(
   expected=listOf(LetterAnswerColor.ORANGE, LetterAnswerColor.ORANGE, LetterAnswerColor.RED, LetterAnswerColor.RED, LetterAnswerColor.RED),
   actual=gameRules.verify(guess="eeeqq", chosenWord="xxxee"))
 }

}