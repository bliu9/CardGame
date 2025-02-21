//Bryan Liu for CS2

import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;

public class Game {
    private Player Player1;
    private Player Player2;
    private Deck deck;
    private Player currentPlayer;
    private String[] abilities = {"+2", "+4", "Wild"};
    private String[] colors = {"Red", "Blue", "Yellow", "Green"};
    private int[] numbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    private int sizeOfHand;
    private Card topCard;
    private String currentColor;
    private GameViewer window;
    private String gameState;

    public Game() {

        this.gameState = "rules";
        this.window = new GameViewer(this);
        window.repaint();




        // Creates the deck for the game
        deck = new Deck(abilities, colors, numbers, window);
        // Gets the starting size of the players' hands
        Scanner input = new Scanner(System.in);
        System.out.print("How many cards should each player start with? ");
        sizeOfHand = input.nextInt();
        input.nextLine();
        // Create the two players
        Player1 = new Player(getName("Player 1"), makePlayerHand(sizeOfHand));
        Player2 = new Player(getName("Player 2"), makePlayerHand(sizeOfHand));
        // Set the current player to start at Player2
        this.currentPlayer = Player2;
        // Deal a card to be the starting card of the game
        topCard = deck.deal();
        // If the top card is an ability card
        while (topCard.getNumber() == -1) {
            // Re-deal the top card
            topCard = deck.deal();
        }
        // Set the current color of the game to the color of the top card
        currentColor = topCard.getColor();







        gameState = "playing";
        window.repaint();
    }

    public String getGameState() {
        return gameState;
    }

    public Player getPlayer(int playerNumber)
    {
        if (playerNumber == 1)
        {
            return Player1;
        }
        else if (playerNumber == 2)
        {
            return Player2;
        }
        return null;
    }

    public Player getCurrentPlayer()
    {
        return currentPlayer;
    }

    public void setGameState(String gameState)
    {
        this.gameState = gameState;
    }

    // Clears the screen
    public static void clearScreen() {
        // Prints 20 rows of blank to prevent players from being able to see their opponent's hand
        for (int i = 0; i < 20; i++) {
            System.out.println();
        }
    }

    // Main gameplay method
    public void playGame() {
        // Loops forever until a player has won
        while (true) {
            // Switches between player1 and player2
            switchPlayer();

            // Prints the top card and some formatting
            printTopCard();

            // Prints the player's deck with the index number of each card
            currentPlayer.printHand();

            // Prompts player to play a card and executes its ability
            playCard();

            // If the current player has won (has 0 cards), then exit the gameplay loop
            if (currentPlayer.checkWin()) {
                break;
            }

            // Checks to see if the player wants to call Uno and sees if they call Uno
            currentPlayer.setCalledUno(checkCalledUno(askForUno()));

            // Determine whether to apply the Uno punishment of adding a card
            doUnoPunishment();

            // Clears the screen
            clearScreen();
        }
    }

    // Determine whether to apply the Uno punishment of adding a card
    private void doUnoPunishment() {
        // If the current player has uno and the current player has not called uno
        if (isUno(currentPlayer) && !currentPlayer.getCalledUno()) {
            // Add a card to the current player
            currentPlayer.addCard(deck);
            // Tell the player they did not call uno on time
            System.out.println("You didn't call Uno!\nYou are forced to draw a card!");
        }
        // If the current player does not have uno and the current player does call uno
        else if (!isUno(currentPlayer) && currentPlayer.getCalledUno()) {
            // Add a card to current player
            currentPlayer.addCard(deck);
            // Tell player they called uno illegally
            System.out.println("You called Uno when you didn't have Uno!\nYou are forced to draw a card!");
        }
        // If the player does have uno and called it correctly
        else if (isUno(currentPlayer) && currentPlayer.getCalledUno()) {
            System.out.println("You called Uno correctly!\nOne card left to go!");
        }
    }

    // Prints the top card and some formatting
    private void printTopCard() {
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.print("Current top card: ");
        System.out.print(topCard);
        // If the card is a wild card
        if (topCard.getColor().equals("all")) {
            // Print a clarifying statement that says the chosen color of the wild
            System.out.println(" - " + currentColor);
        }
        // Else, print a new line
        else {
            System.out.println();
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }

    // Switches between player1 and player2
    private void switchPlayer() {
        // If the current player is player1 (determines by checking the names of the two)
        if (currentPlayer.getName().equals(Player1.getName())) {
            // Set the current player to player2
            currentPlayer = Player2;
        } else // Else, set the current player to the player1
        {
            currentPlayer = Player1;
        }
    }

    // Asks the player if they want to call Uno and returns their response
    private String askForUno() {
        // Prompts user for uno
        Scanner input = new Scanner(System.in);
        System.out.println("Would you like to call Uno? If so, type Uno!");
        // Returns their response
        return input.nextLine();
    }

    // Gets the name the user wants to name their player and returns this
    public String getName(String initName) {
        // Ask user for what they want their name to be
        Scanner input = new Scanner(System.in);
        System.out.print(initName + ", what is your name? ");
        // Returns their response
        return input.nextLine();
    }

    // Prints the rules of Uno with some formatting
    public static void printRules() {
        System.out.println("Welcome to Uno!");
        System.out.println("The goal of the game is to be the first player to play all your cards.");
        System.out.println("But be sure to call Uno when you have one card left, or you will be forced to draw a card.\n");
    }

    // Creates a hand for a player and returns it
    public ArrayList<Card> makePlayerHand(int sizeOfHand) {
        // Creates an array that will store the hand
        ArrayList<Card> playerHand = new ArrayList<Card>();
        // Adds the user-specified number of cards to the player's initial hand
        for (int i = 0; i < sizeOfHand; i++) {
            // Get a random card from the deck and add it to the player's hand
            playerHand.add(deck.getCard((int) (Math.random() * sizeOfHand)));
        }
        // Returns the hand
        return playerHand;
    }

    // Gets card player wants to play and executes its ability
    // Also handles drawing a card if the user wants to draw a card
    public void playCard() {
        // Checks if there is a card that the player can play
        // If there is, initializes the canPlayState at false
        // If there is not, initializes it to true
        boolean canPlayState = !isCardCanPlay();

        // If there is no card that the player can play, force them to draw a card
        if (canPlayState) {
            // Give text confirmation
            System.out.println("There is no card you can play. You are forced to draw a card.");
            // Add card to the current player's hand
            currentPlayer.addCard(deck);
        }

        // Initialize variables used in checking if the player's selected card is valid
        Scanner input = new Scanner(System.in);
        Card cardWantToPlay = null;
        int cardIndex;
        // While the player's selected card cannot be played
        while (!canPlayState) {
            System.out.print("What card would you like to play? (Type 0 to draw a card)" + "\n" + "Type number: ");
            // Converts the number of card that the player chooses to an index that can be directly used in code
            cardIndex = input.nextInt() - 1;
//            input.nextLine();///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            // If the player wants to play a card
            if (cardIndex > -1) {
                // Gets the card object that the player wants to play
                cardWantToPlay = currentPlayer.getHand().get(cardIndex);
                // Updates the canPlayState
                canPlayState = currentPlayer.playCardHelper(cardWantToPlay, topCard, currentColor);
                // Updates the current top card in the game to the card the player wanted to play
                topCard = cardWantToPlay;

                // Executes the ability of the card, if the card has an ability
                doCardAbility(cardWantToPlay);
            }
            // If the player chooses to draw a card
            else if (cardIndex == -1) {
                // Set the canPlayState to true
                canPlayState = true;
                // Draw a card
                currentPlayer.addCard(deck);
                // Prints out the player's turn
                System.out.println("\n" + currentPlayer.getName() + " draws a card\n");
            }
        }
    }

    // Executes a card's ability
    private void doCardAbility(Card cardWantToPlay) {
        // If the card has no ability, then update the currentColor and return
        if (cardWantToPlay.getAbility().equals("none")) {
            // Update the current color of the game to the color of the card the user wants to play
            currentColor = cardWantToPlay.getColor();
            return;
        }

        // Gets the next player
        // Defaults to Player 1
        Player nextPlayer = Player1;
        // If the current player is player1 (determines by checking the names of the two)
        if (currentPlayer.getName().equals(Player1.getName())) {
            // Set the current player to player2
            nextPlayer = Player2;
        } else // Else, set the current player to the player1
        {
            nextPlayer = Player1;
        }

        // Below checks what ability the card has and does that ability

        // If the card is a +2
        if (cardWantToPlay.getAbility().equals("+2")) {
            // Add 2 cards to the next player
            for (int i = 0; i < 2; i++) {
                nextPlayer.addCard(deck);
            }
        }

        // If the card is a +4
        if (cardWantToPlay.getAbility().equals("+4")) {
            // Add 4 cards to the next player
            for (int i = 0; i < 4; i++) {
                nextPlayer.addCard(deck);
            }
        }

        // If the card is a wild
        if (cardWantToPlay.getAbility().equals("Wild")) {
            // Initialize a scanner and boolean used to check for valid input
            Scanner input = new Scanner(System.in);
            boolean validInput = false;
            // Create a variable that will hold the user's desired color
            String color = "";

            // Keeps prompting user until a valid color is received
            while (!validInput) {
                // Gets color the player wants to change the deck to
                System.out.print("What color do you want to choose: ");
                color = input.nextLine();
                // If that input is a valid color
                if (color.equals("Red") || color.equals("Blue") || color.equals("Yellow") || color.equals("Green")) {
                    // Set the input to valid
                    validInput = true;
                }
            }
            // Update the game color to user's specified color
            currentColor = color;
        }
    }

    // Checks to see if a card can be played
    private boolean isCardCanPlay() {
        // Iterate through the current player's hand
        for (Card card : currentPlayer.getHand()) {
            // If the card can be played, return true (the player can make a unique move)
            if (card.checkCanPlay(topCard, currentColor)) {
                return true;
            }
        }
        // Return false, because the player is forced to draw a card
        return false;
    }

    // Checks to see if the player has Uno
    public boolean isUno(Player currentPlayer) {
        // If the player has 1 card, return true; if it does not, return false
        return currentPlayer.getHand().size() == 1;
    }

    // Check to see if the user has called Uno
    public boolean checkCalledUno(String input) {
        // If the user enters something close to "Uno", they have called uno
        return input.contains("uno") || input.contains("Uno") || input.contains("UNO");
    }

    public static void main(String[] args) {
        // Prints a refresher for the rules of Uno
        Game.printRules();
        // Creates a new game of Uno
        Game game = new Game();
        // Starts the game
        game.playGame();
        // Asks the user if they want to play again or not
        playAgain(game);
    }

    private static void playAgain(Game game) {
        // Asks user if they would like to play again
        Scanner input = new Scanner(System.in);
        System.out.println("Would you like to play again? (y/n)");
        // if the user agrees to start a new game, start a new game
        if (input.nextLine().equals("y") || input.nextLine().equals("Y") || input.nextLine().equals("yes") || input.nextLine().equals("Yes")) {
            // Clears the screen and starts a new game
            Game.clearScreen();
            game = new Game();
        }
        // If they don't want to start a new game, prints a goodbye statement
        System.out.println("Thanks for playing! Byeeee :)");
    }
}