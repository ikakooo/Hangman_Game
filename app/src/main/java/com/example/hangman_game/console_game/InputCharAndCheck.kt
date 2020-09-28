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
            allUnderscoreIsNotOpened = false
            println("Enter Character: ")
            val inputtedCharInWord = readLine()?.get(0)

            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //////////// ვამოწმებთ არის თუ არა უკვე შეყვანილი ჩვენს მიერ კონსოლში ////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            when {
                isTriedCharacter(inputtedCharInWord) -> {
                    allUnderscoreIsNotOpened = true
                    println("You already tried this character")
                    println("Lives remaining: $lives ")
                }
                //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                //////////// ვამოწმებთ არის თუ არა ლათინური ალფაბეტური ////////////////////////////////////////////////////////////////////
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                isAlphabetOrNot(inputtedCharInWord) -> {
                    /////////////// შევდივრთ ციკლში სადაც ხდება შეყვანილი ჩარაქთერის შემმოწმება/შედარება/ვალიდაცია/დამატება/დამახსოვრება   //////////////////
                    (Word.indices).forEach {
                        if (inputtedCharInWord?.toLowerCase() == Word[it].toLowerCase()) {
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
                    // return@forEach
                    when {
                        charIsNotHere -> {
                            println("There is no such character")
                            lives--
                            if (inputtedCharInWord != null) {
                                triedChars.add(CharsArray(inputtedCharInWord.toLowerCase()))
                            }
                            if (inputtedCharInWord != null) {
                                triedChars.add(CharsArray(inputtedCharInWord.toUpperCase()))
                            }
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
                }
            }


        }

    }
}



