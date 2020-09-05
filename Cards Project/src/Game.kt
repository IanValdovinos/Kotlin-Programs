import java.lang.Exception
import kotlin.system.exitProcess

fun main() {
    val game = Game()
    game.play()
}

class Game {

    private val suits = setOf<String>("Spades", "Clubs", "Hearts", "Diamonds")
    private val values = setOf<Int>(1,2,3,4,5,6,7,8,9,10,11,12,13)
    private var attempt = 0

    fun play(){

        val deck = Deck(suits, values)
        deck.shuffle()
        val cardToGuess = deck.getCard()

        while (true) {
            println("Player's card:")
            val input = readLine()
            if (processInput(input, cardToGuess)) {
                println("Correct! Thanks for playing")
                exitProcess(0)
            } else {
                attempt++
                println("Incorrect. You have $attempt of 4 attempts.")
                attemptHint(attempt, cardToGuess)
            }
        }
    }

    private fun processInput(input: String?, card: Card):Boolean {
        input?.let {
            return try {
                val (value, joint , suit) = input.split(" ")
                value.toLowerCase() == card.getStringValue.toLowerCase() && suit.toLowerCase() == card.suit.toLowerCase() && joint.toLowerCase() == "of"
            } catch (e: Exception){
                println("Invalid Input!")
                false
            }
        }

        return false
    }

    private fun attemptHint(attemptNum: Int, card: Card){
        if (attemptNum < 4) {
            val message = when (attemptNum) {
                1 -> attemptOne(card)
                2 -> attemptTwo(card)
                3 -> "You have on more attempt."
                else -> "Error!"
            }
            println(message)
        } else{
            println("You lost!\nYour card was: ${card.getStringValue} of ${card.suit}")
            exitProcess(0)
        }
    }

    private fun attemptOne(card: Card):String {
        return when (card.suit.toLowerCase()) {
            "hearts" -> "HINT: Your card's suit is ${card.suit}"
            "spades" -> "HINT: Your card's suit is ${card.suit}"
            "clubs" -> "HINT: Your card's suit is ${card.suit}"
            "diamonds" -> "HINT: Your card's suit is ${card.suit}"
            else -> "Error "
        }
    }

    private fun attemptTwo(card: Card):String {
        return when (card.value) {
            in 0..5 -> "HINT: Your card's value is between Ace and 5"
            in 6..10 -> "HINT: Your card's value is between 6 and 10"
            in 11..13 -> "HINT: Your card's value is between Jack and King"
            else -> "Error"
        }
    }

    init {
        println("One card from a 52 card deck has been selected for you to guess!\n" +
                "You have for opportunities to guess what card it is!")
    }
}