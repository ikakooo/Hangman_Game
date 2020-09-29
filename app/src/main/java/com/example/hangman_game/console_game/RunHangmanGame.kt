package com.example.hangman_game.console_game

import com.example.hangman_game.console_game.Extensions.printWordUnderscores
import com.example.hangman_game.console_game.Extensions.printWithSpacesChars
import com.example.hangman_game.console_game.Extensions.whatWillHappen
import com.example.hangman_game.console_game.HangmanGameData.lives
import com.example.hangman_game.console_game.HangmanGameData.savedPlayersScore
import com.example.hangman_game.console_game.HangmanGameData.weArePlaying
import com.example.hangman_game.console_game.InputCharAndCheck.inputCharAndCheck

////////////// შევდივართ ციკლში, რომელშიც მთლინანი თამაშის პროცესი მიმდინარეობს
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @ExperimentalStdlibApi
    fun main() {
        while (weArePlaying) {
            println("Enter Name: ")
            val playerName: String = readLine().toString()
            println("Hello $playerName, Let’s play Hangman!")//// შეგვყავს მოთამაშის სახელი
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            println("Enter Word: ")
            val word: String = readLine().toString()//// შეგვყავს გამოსაცნობი სიტყვა
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            println("Game is starting… ")
            println("Lives remaining: $lives ")
            println("Current Word is: ")
//////////////////////////////////////////////////// გამოდის შეტყობინება თამაშის დაწყების შესახებ
            val incognitoWord = word.printWordUnderscores()
            //println(incognitoWord.printWithSpacesChars2())
            println(printWithSpacesChars(incognitoWord))///// -> აჩვენებს სიტყვის ზომას სფეისებით დაყოფილ და ქვედა ტირეებით->"_" დაფარულ მდგომარეობაში
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            inputCharAndCheck(word,incognitoWord) ///////////////////////////////////////////////////////////////////////////////
            // იშვებს ფუნქცია რომელშიც ვცდილობთ სიტყვის გამოცნობას, ჩაეწოდება სიტყვა და მისი ექვივალენტური დაფარული სიტყვა
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            savedPlayersScore.add(WinnersModel(playerName, lives))

            if (lives<=0){
                println("Sorry, you lost… The word was: $word")
                println("Want to play again? (Y/N/H) ")
                whatWillHappen()
            }else{
                println("Congratulations! Want to play again? (Y, H or N:")
                whatWillHappen()
            }

        }
    }




