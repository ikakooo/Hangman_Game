package com.example.hangman_game.console_game

import com.example.hangman_game.console_game.Extensions.isAlphabetOrNot
import com.example.hangman_game.console_game.Extensions.isTriedCharacter
import com.example.hangman_game.console_game.Extensions.printWithSpacesChars
import com.example.hangman_game.console_game.HangmanGameData.lives
import com.example.hangman_game.console_game.HangmanGameData.triedChars

object InputCharAndCheck {
    fun inputCharAndCheck(Word: String, incognitoWord: CharArray) {
        var charIsNotHere = true
        var allUnderscoreIsNotOpened = true //// განსაზღვრავს ციკლში შესვლა-გამოსვლას
/////////////////////// შევდივართ ციკლში რომელშიც ვცდილობთ ჩარაქთერების შეყვანით სიტყვის გამოცნობას ///////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        while (allUnderscoreIsNotOpened && lives > 0) {
            println("Enter Character: ")
            val inputWord = readLine()
            val inputtedCharInWord = if (inputWord?.length==1) { allUnderscoreIsNotOpened = false
                inputWord[0]} else '*'

            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //////////// ვამოწმებთ არის თუ არა უკვე შეყვანილი ჩვენს მიერ კონსოლში ////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            when {
                isTriedCharacter(inputtedCharInWord) && inputtedCharInWord != '*' -> {
                    allUnderscoreIsNotOpened = true
                    println("You already tried this character")
                    println("Lives remaining: $lives ")
                }
                //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                //////////// ვამოწმებთ არის თუ არა ლათინური ალფაბეტური ////////////////////////////////////////////////////////////////////
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                isAlphabetOrNot(inputtedCharInWord) && inputtedCharInWord != '*' -> {
                    /////////////// შევდივრთ ციკლში სადაც ხდება შეყვანილი ჩარაქთერის შემმოწმება/შედარება/ვალიდაცია/დამატება/დამახსოვრება   //////////////////
                    (Word.indices).forEach {
                        if (inputtedCharInWord.toLowerCase() == Word[it].toLowerCase()) {
                            ///////// აქ ხდება თითოეული ჩარაქთერის გახსნა/ამოცნობა/შედარება  ////////////
                            incognitoWord[it] = Word[it]
                            triedChars.add(CharsArray(inputtedCharInWord.toLowerCase()))
                            triedChars.add(CharsArray(inputtedCharInWord.toUpperCase()))//// ვამატებთ დაბალი და მაღალი რეგისტრის ჩარაქთერებს

                            charIsNotHere = false
                        }

                        if (incognitoWord[it] == '_') {
                            //////// თუ ყველა ქვედატირე გახსნილი არ არის, მაშინ ვიმეორებთ ციკლს //////////////////////
                            allUnderscoreIsNotOpened = true
                        }

                    }
                    when {
                        charIsNotHere -> {
                            println("There is no such character")
                            lives--
                            triedChars.add(CharsArray(inputtedCharInWord.toLowerCase()))
                            triedChars.add(CharsArray(inputtedCharInWord.toUpperCase()))
                        }
                        else -> println("Yes, it is there!!!")
                    }
                    charIsNotHere = true
                    println("Lives remaining: $lives ")
                    println(printWithSpacesChars(incognitoWord)) ///ვბეჭდავთ ნაწილობრივ გამოცნობილ/დაფარულ სიტყვას/////


                }
                else -> {
                    ///////////////////////////// თუ არ არის ალფაბეტური, მაშინ////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    println("Please, enter a valid character")
                    allUnderscoreIsNotOpened = true
                }
            }


        }

    }
}



