data class Card(val suit: String, val value: Int){
    val getStringValue = when(value){
        1 -> "Ace"
        11 -> "Jack"
        12 -> "Queen"
        13 -> "King"
        else -> value.toString()
    }
}

class Deck(private val suits: Set<String>, private val values: Set<Int>){
    private var cards: MutableSet<Card> = mutableSetOf()
    private var deck = mutableSetOf<String>()

    fun getCard() = cards.first()

    fun shuffle(){
        cards = cards.shuffled().toMutableSet()
    }

   fun printDeck(){
       deck.forEach{
           println(it)
       }
   }

    init {
        println("A new deck has been created")
        createCards()
        for(i in cards.indices){
            deck.add("${cards.elementAt(i).getStringValue} of ${cards.elementAt(i).suit}")
        }
    }

    private fun createCards(){
        for(suit in suits.indices){
            for (value in values.indices){
                cards.add(Card(suits.elementAt(suit),values.elementAt(value) ))
            }
        }

    }
}