/**
 * Hand represents a hand of playing cards using French suits containing a set
 * number of cards.
 */
public class Hand {
    private Card[] cardsInHand;
    public static final int STANDARD_HAND_SIZE = 5;
    Deck NewDeck = new Deck();
    /**
     * Constructs an empty hand of the provided size.
     * @param handSize     the number of cards the hand can hold
     * @throws IllegalArgumentException    if an non-positive hand size is provided
     */
    public Hand( int handSize ) {
        if (handSize <= 0) {
            throw new IllegalArgumentException();
        }
        this.cardsInHand = new Card[handSize];
        int HandIndex = 0;
        //for (int i = 0; i <= handSize ; i++ ) {
            cardsInHand[HandIndex] = this.NewDeck.drawCardFromTop();
            HandIndex++;
        //}
    }

    /**
     * Constructs an empty hand of the standard hand size.
     */
    public Hand() {
        this(STANDARD_HAND_SIZE);
    }
    /**
     * Draws a new hand from the provided deck, replacing the cards in the hand
     * with the top cards from the deck. The new top card of the deck is the
     * card after the last drawn card.
     *
     * @param deck     the number of cards the hand can hold
     */
    public void drawNewHandFrom( Deck deck ) {
        int HandIndex = 0;
        for (int i = 0; i < this.getHandSize(); i++ ) {
            cardsInHand[i] = deck.drawCardFromTop();
        }
    }

    /**
     * Returns the maximum hand size of this hand.
     *
     * @return          the number of card this hand can hold
     */
    public int getHandSize() {
        return this.cardsInHand.length;
    }

    /**
     * Returns the card at the given index in this hand.
     *
     * @param index     the index of the card in the hand
     * @return          the card at the provided index in the hand
     */
    public Card getCardAt( int index ) {
        return cardsInHand[index];
    }

    /**
     * Returns a string representation of this hand, including all cards in the
     * hand.
     *
     * @return         a string representation of the hand
     */
    @Override
    public String toString() {
        String handy = "";
        for (int i = 0; i < this.getHandSize(); i++) {
            handy += this.cardsInHand[i].toString() + "\n";
        }
        return handy;
    }

    /**
     * Returns whether this hand contains a card of the same rank suit as the
     * provided card.
     *
     * @param card     the card being searched for
     * @return         <code>true</code> if this hand contains an equivalent
     *                 card; <code>false</code> otherwise.
     */
    public boolean contains( Card card ) {
        for (int i = 0; i <= this.getHandSize(); i++ ) {
            if (this.cardsInHand[i].equals(card)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the number of cards in this hand with the provided rank.
     *
     * @param rank     the rank being searched for
     * @return         the number of cards in the hand with the provided rank
     * @throws IllegalArgumentException    if an invalid rank is provided
     */
    public int countInHand( int rank ) {
        int counter = 0;
        if (rank < 1 || rank > 13) {
            throw new IllegalArgumentException();
        } else {
            for (int i = 0; i < this.getHandSize(); i++) {
                if (this.cardsInHand[i].getRank() == rank) {
                    counter++;
                }
            } return counter;
        }
    }

    /**
     * Returns the number of cards in this hand with the provided suit.
     *
     * @param suit     the suit being searched for
     * @return         the number of cards in the hand with the provided suit
     */
    public int countInHand( Card.Suit suit ) {
        int counter = 0;
        for (int i = 0; i < this.getHandSize(); i++) {
            if (this.cardsInHand[i].getSuit() == suit) {
                counter++;
            }
        } return counter;
    }

    /**
     * Returns the number of cards in this hand with the "face" ranks of King,
     * Queen, and Jack.
     *
     * @return         the number of cards in the hand with face ranks
     */
    public int countOfFaceCards() {
        int counter = 0;
        for (int i = 0;i < this.getHandSize() ;i++) {
            if (this.cardsInHand[i].getRank() >= 11) {
                counter++;
            }
        } return counter;
    }
}
