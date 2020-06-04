package fr.caro.example.myapp

class Supporters {
    fun computerSays(text: String = "Hello From Computer") {
        println("Computer : $text")
    }

    fun player1Says(text: String) {
        println("Player 1: $text")
    }

    fun hideMyNum() {
        var i = 0
        while (i < 14) {
            println(".")
            i++
        }
    }
}