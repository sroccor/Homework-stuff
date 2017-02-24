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
        int roundScoreTwo = 0 ;
        this.handOne.drawNewHandFrom(gameDeck);
        this.handTwo.drawNewHandFrom(gameDeck);
        roundScoreOne = this.scoreHand(handOne, handTwo);
        roundScoreTwo = this.scoreHand(handTwo, handOne);

        System.out.println("Your hand: ");
        System.out.println(handOne.toString());
        System.out.println("Score: " + roundScoreOne + "\n");

        System.out.println("Opponent's hand: ");
        System.out.println(handTwo.toString());
        System.out.println("Score: " + roundScoreTwo + "\n");

        if (roundScoreOne > roundScoreTwo) {
            int updateAmount = roundScoreOne - roundScoreTwo;
            this.playerScore += updateAmount;
            System.out.println("You score " + updateAmount + " points !");
            System.out.println("You: " + playerScore);
            System.out.println("Opponent: " + oppoScore);
        } else if (roundScoreTwo > roundScoreOne) {
            int updateAmount = roundScoreTwo - roundScoreOne;
            this.oppoScore += updateAmount;
            System.out.println("Opponent scores " + updateAmount + " points !");
            System.out.println("You: " + playerScore);
            System.out.println("Opponent: " + oppoScore);
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
        if (playerScore > oppoScore) {
            return true;
        } else {
            return false;
        }
    }

    //returns whether the game is over based upon both players' scores and the cards remaining in the deck.
    public boolean isGameOver(){
        if (gameDeck.remainingCards() < 10) {
            if (playerScore == oppoScore) {
                System.out.println("\n---GAME OVER---");
                System.out.println("Tie!!");
                System.out.println("You: " + playerScore);
                System.out.println("Opponent: " + oppoScore);
            } else if (playerScore > oppoScore) {
                System.out.println("\n---GAME OVER---");
                System.out.println("You Win!");
                System.out.println("You: " + playerScore);
                System.out.println("Opponent: " + oppoScore);
            } else {
                System.out.println("\n---GAME OVER---");
                System.out.println("Opponent Wins!");
                System.out.println("You: " + playerScore);
                System.out.println("Opponent: " + oppoScore);
            }
            return true;
        } else if(playerScore == oppoScore && playerScore >= END_SCORE ) {
            System.out.println("\n---GAME OVER---");
            System.out.println("Tie!!");
            System.out.println("You: " + playerScore);
            System.out.println("Opponent: " + oppoScore);
            return true;
        } else if (playerScore >= END_SCORE && playerScore > oppoScore) {
            System.out.println("\n---GAME OVER---");
            System.out.println("You Win!");
            System.out.println("You: " + playerScore);
            System.out.println("Opponent: " + oppoScore);
            return true;
        } else if (oppoScore >= END_SCORE && oppoScore > playerScore) {
            System.out.println("\n---GAME OVER---");
            System.out.println("Opponent Wins!");
            System.out.println("You: " + playerScore);
            System.out.println("Opponent: " + oppoScore);
            return true;
        } else {
            return false;
        }
    }

    //plays out a multi-round game, printing out the results of each round, the final score, and the overall winner (if there is one).
    public void playGame(){
        int roundNumber = 0;
        gameDeck.shuffle();
        while (!isGameOver()) {
            roundNumber++;
            System.out.println("\n---Round " + roundNumber + "---");
            playRound();
        }
    }

    public static void main(String[] args) {
        if (args.length == 2) {
            int handSize = Integer.parseInt(args[0]);
            int deckCopies = Integer.parseInt(args[1]);
            CardGame myCardGame = new CardGame(handSize, deckCopies);
            myCardGame.playGame();
        } else if (args.length == 0) {
            CardGame myCardGame = new CardGame();
            myCardGame.playGame();
        } else {
            System.out.println("Usage instructions: java CardGame [<hand size> <copies of each card in deck>]");
        }
    }
}
