public class CardGame {
    public static final int END_SCORE = 50;

    private Hand handOne;

    private Hand handTwo;

    private Deck gameDeck;

    private int playerScore;

    private int oppoScore;
//constructs a card game with 2 standard size hands and a standard size deck.
    public CardGame(){
        this.handOne = new Hand();
        this.handTwo = new Hand();
        this.gameDeck = new Deck();
        this.playerScore = 0;
        this.oppoScore = 0;
    }
//constructs a card game with 2 hands of the provided size and a deck containing the given number of copies of each possible card.
    public CardGame( int handSize, int copiesPerDeck ){
        this.handOne = new Hand(handSize);
        this.handTwo = new Hand(handSize);
        this.gameDeck = new Deck(copiesPerDeck);
        this.playerScore = 0;
        this.oppoScore = 0;
    }
//plays out a single round of the game, drawing new hands for each player, scoring them, declaring the round's winner, and updating the overall scores.
    public void playRound(){
        int roundScoreOne = 0;
        int roundScoreTwo = 0;
        this.handOne.drawNewHandFrom(gameDeck);
        this.handTwo.drawNewHandFrom(gameDeck);
        this.scoreHand(handOne, handTwo);
        this.roundScoreOne = score;
        this.scoreHand(handTwo, handOne);
        this.roundScoreTwo = score;
        if (roundScoreOne > roundScoreTwo) {
            this.playerScore = roundScoreOne - roundScoreTwo;
        } else if (roundScoreTwo > roundScoreOne) {
            this.oppoScore = roundScoreTwo - roundScoreOne;
        }
    }
//returns the score of the first hand when compared to the second.
// Face cards (Kings, Queens, and Jacks) are worth 10 points if 2 other cards of the same suit as the face card are in the player's hand.
// Cards of rank 10 through 6 are worth 8 points if another card in the player's hand has a rank one higher or lower than card being scored.
// Cards of rank 5 through 2 are worth 7 points if the opponent's hand contains a face card.
// Cards of rank Ace are worth 15 points, regardless of the other cards in either player's hand.
    public static int scoreHand( Hand scoredHand, Hand otherHand ){
        int score = 0;
        for (int i = 0; i < scoredHand.getHandSize(); i++) {
            if (scoredHand.getCardAt(i).getRank()== 1) {
                score += 15;
            } else if (scoredHand.getCardAt(i).getRank() >= 11
                    && scoredHand.countInHand(scoredHand.getCardAt(i).getSuit()) > 2) {
                score += 10;
            } else if (scoredHand.getCardAt(i).getRank() >= 6
                    && scoredHand.getCardAt(i).getRank() <= 10
                    && (scoredHand.countInHand(scoredHand.getCardAt(i).getRank() + 1) >= 1
                    || scoredHand.countInHand(scoredHand.getCardAt(i).getRank() - 1) >= 1)) {
                score += 8;
            } else if (scoredHand.getCardAt(i).getRank() >= 2
                    && scoredHand.getCardAt(i).getRank() <= 5
                    && otherHand.countOfFaceCards() >= 1) {
                score += 7;
            }
        } return score;
    }
//returns the first player's score.
    public int getPlayerScore(){
        return this.playerScore;
    }
//returns the second player's score.
    public int getOpponentScore(){
        return this.oppoScore;
    }
//returns whether the first player is winning.
    public boolean amIWinning(){
        throw new UnsupportedOperationException();
    }
//returns whether the game is over based upon both players' scores and the cards remaining in the deck.
    public boolean isGameOver(){
        throw new UnsupportedOperationException();
    }
//plays out a multi-round game, printing out the results of each round, the final score, and the overall winner (if there is one).
    public void playGame(){
        throw new UnsupportedOperationException();
    }

}
