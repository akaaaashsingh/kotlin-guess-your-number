package fr.caro.example.myapp

import java.util.*

class NumberGame {
    val supp = Supporters()

    fun computerPlayer() {
        val upLimit = 10000
        val lowLimit = 1
        val random = SplittableRandom()
        val max = random.nextInt(lowLimit, upLimit)
        val min = random.nextInt(lowLimit, max-1)
        var numberOfAttempts = 0
        val reader = Scanner(System.`in`)
        val goal = random.nextInt(min, max)
        var userGuess: Int

        supp.computerSays("I chose a number between $min and $max")
        supp.computerSays("How many tries you want?")
        val inputDemanded = Integer.valueOf(readLine())
        loop@ do {
            numberOfAttempts++
            supp.computerSays("What's your guess?")
            userGuess = reader.nextInt()
            when {
                userGuess > goal -> {
                    supp.computerSays("The number is less than $userGuess !")
                    if(numberOfAttempts < inputDemanded && numberOfAttempts%2==1) {
                        supp.computerSays("want a hint? (y/n)")
                        when (readLine()) {
                            "y" -> {
                                        val mini = goal - random.nextInt(10,50)
                                        val maxi = goal + random.nextInt(10,50)
                                        println("The number is somewhere between $mini and $maxi")
                                    }
                            "n" -> continue@loop
                            else -> continue@loop
                        }
                    }
                }
                userGuess < goal -> {
                    supp.computerSays("The number is more than $userGuess !")
                    if(numberOfAttempts < inputDemanded && numberOfAttempts%2==1) {
                        supp.computerSays("want a hint? (y/n)")
                        when (readLine()) {
                            "y" -> {
                                        val mini = goal - random.nextInt(10,50)
                                        val maxi = goal + random.nextInt(10,50)
                                        println("The number is somewhere between $mini and $maxi")
                                    }
                            "n" -> continue@loop
                            else -> continue@loop
                        }
                    }
                }
            }
        } while(userGuess != goal && numberOfAttempts < inputDemanded)
        if(userGuess == goal) {
            supp.computerSays("Congratulation ! You found in $numberOfAttempts attemps.")
        } else {
            supp.computerSays("Sorry ! You ran out of guesses")
            supp.computerSays("The correct guess was $goal")
        }
    }

    fun humanPlayer() {
        val upLimit = 10000
        val lowLimit = 1
        val random = SplittableRandom()
        val max = random.nextInt(lowLimit, upLimit)
        val min = random.nextInt(lowLimit, max-1)
        var numberOfAttempts = 0
        val reader = Scanner(System.`in`)
        var userGuess: Int
        var goal : Int

        supp.computerSays("Who is player 1?")
        var player1 = readLine()
        supp.computerSays("Who is player 2?")
        var player2 = readLine()
        if(player1.isNullOrBlank()) {
            player1 = "John Doe"
        }
        if (player2.isNullOrBlank()) {
            player2 = "Steve Smith"
        }
        supp.computerSays("$player1 chose a number between $min and $max, Tell $player2 not to see the screen while you do that!")
        var s = Integer.valueOf(readLine())
        supp.hideMyNum()
        while (s < min || s > max) {
            supp.computerSays("$player1 chose a number between $min and $max, Tell $player2 not to see the screen while you do that!")
            s = Integer.valueOf(readLine())
            supp.hideMyNum()
        }
        goal = s
        supp.player1Says("$player2 How many tries you want?")
        val inputDemanded = Integer.valueOf(readLine())
        loop@ do {
            numberOfAttempts++
            supp.player1Says("$player2 What's your guess?")
            userGuess = reader.nextInt()
            when {
                userGuess > goal -> {
                    supp.player1Says("The number is less than $userGuess !")
                    if( numberOfAttempts < inputDemanded && numberOfAttempts%2==1) {
                        supp.computerSays("want a hint? (y/n)")
                        when (readLine()) {
                            "y" -> {
                                val mini = goal - random.nextInt(10,50)
                                val maxi = goal + random.nextInt(10,50)
                                println("The number is somewhere between $mini and $maxi")
                            }
                            "n" -> continue@loop
                            else -> continue@loop
                        }
                    }
                }
                userGuess < goal -> {
                    supp.player1Says("The number is more than $userGuess !")
                    if(numberOfAttempts < inputDemanded && numberOfAttempts%2==1) {
                        supp.computerSays("want a hint? (y/n)")
                        when (readLine()) {
                            "y" -> {
                                val mini = goal - random.nextInt(10,50)
                                val maxi = goal + random.nextInt(10,50)
                                println("The number is somewhere between $mini and $maxi")
                            }
                            "n" -> continue@loop
                            else -> continue@loop
                        }
                    }
                }
            }
        } while(userGuess != goal && numberOfAttempts < inputDemanded)

        if(userGuess == goal) {
            supp.player1Says("Congratulation $player2 ! You found in $numberOfAttempts attemps.")
        } else {
            supp.player1Says("Sorry $player2 ! You ran out of guesses")
            supp.player1Says("The correct guess was $goal")
        }
    }

    fun play () {
        supp.computerSays("Want to play with me? or Human? (You/Human)")
        var usrInp = readLine()
        when (usrInp) {
            "You" -> computerPlayer()
            "Human" -> humanPlayer()
            else -> {
                supp.computerSays("Invalid Input!")
                play()
            }
        }
    }

    fun finalPlay () {
        play()
        supp.computerSays("Play Again? (y/n)")
        var playAgain = readLine()
        when (playAgain) {
            "y" -> {
                finalPlay()
            }
            "n" -> {
                supp.computerSays("Game Over")
            }
            else -> {
                println("Invalid Input! Game Over")
            }
        }
    }
}