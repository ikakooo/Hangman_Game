package com.example.hangman_game.console_game

import com.example.hangman_game.console_game.HangmanGameData.lives
import com.example.hangman_game.console_game.HangmanGameData.savedPlayersScore
import com.example.hangman_game.console_game.HangmanGameData.triedChars

object Extensions {

    fun String.printWordUnderscores(): CharArray {
        val playerGuess = CharArray(toCharArray().size)
        for (i in playerGuess.indices) {
            playerGuess[i] = '_'
        }
        return playerGuess
    }

    fun printWithSpacesChars(array: CharArray): String {
        var word = ""
        for (i in array.indices) {
            word += array[i].toString() + " "
        }
        return word
    }
//    @ExperimentalStdlibApi
//    fun CharArray.printWithSpacesChars2(): String {
//        var word = ""
//        for (i in indices) {
//            word += concatToString()[i] + " "
//        }
//        return word
//    }


    fun isAlphabetOrNot(char: Char?): Boolean = char in 'a'..'z' || char in 'A'..'Z'

    fun isTriedCharacter(char: Char?): Boolean {
        triedChars.indices.forEach { if (triedChars[it].char == char) return true }
        return false
    }

    fun whatWillHappen() {

        val inputWord = readLine()
        val answer = if (inputWord?.length==1) inputWord[0] else '*'
        when {
            answer.toLowerCase() == 'y' -> {
                HangmanGameData.weArePlaying = true
                lives = 8
                triedChars.clear()
            }
            answer.toLowerCase() == 'h' -> {
                savedPlayersScore.sortBy { it.WinnerLives }
                val reversed = savedPlayersScore.asReversed()
                reversed.indices.forEach {
                    if (it<5)println("${it+1})  ${reversed[it].WinnerName} - ${reversed[it].WinnerLives} Lives")
                }


                println("Want to play again? (Y/N/H): ")
                repeat(1) { whatWillHappen() }
            }
            answer.toLowerCase() == 'n' -> {
                HangmanGameData.weArePlaying = false
                println("Thanks for playing!")

            }
        }
    }



}