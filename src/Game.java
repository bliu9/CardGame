import java.util.ArrayList;
import java.util.Scanner;

public class Game
{
    private Player humanPlayer;
    private Player computerPlayer;
    private Deck deck;
    private Player currentPlayer;
    private String[] abilities = {"+2","+4","Wild"};
    private String[] colors = {"Red","Blue","Yellow","Green"};
    private int[] numbers = {0,1,2,3,4,5,6,7,8,9};
    //private Card currentCard; WAIT WHAT WHY WAS THIS EVEN HERE
    private int sizeOfHand;
    private Card topCard;
    private String currentColor;


    public Game()
    {
        // Creates the deck for the game
        deck = new Deck(abilities, colors, numbers);
        //
        Scanner input = new Scanner(System.in);
        System.out.print("How many cards should each player start with? ");
        sizeOfHand = input.nextInt();
        input.nextLine();
        //
        humanPlayer = new Player("Computer", makePlayerHand(sizeOfHand));
        computerPlayer = new Player(getName(), makePlayerHand(sizeOfHand));

        //
        this.currentPlayer = humanPlayer;

        // Deal the top card
        topCard = deck.deal();
        // If the top card is an ability card
        while (topCard.getNumber() == -1)
        {
            // Re-deal the top card
            topCard = deck.deal();
        }

        // Set the current color of the game to the color of the top card
        currentColor = topCard.getColor();

    }

    public void playGame()
    {
        while (true)
        {
            // If the current player is the human player (determines by checking the names of the two)
            if (currentPlayer.getName().equals(humanPlayer.getName())) {
                currentPlayer = computerPlayer;
            } else // Else, set the current player to the human player
            {
                currentPlayer = humanPlayer;
            }

            // Prints the top card and a buffer line
            System.out.print("Current top card: ");
            System.out.println(topCard + "\n");

            // Prints the player's deck with the index number of each card
            currentPlayer.printHand();

            //
            playCard();

            //
            if (currentPlayer.checkWin())
            {
                break;
            }

            // Checks to see if the player wants to call Uno and sees if they call Uno
            currentPlayer.setCalledUno(checkCalledUno(askForUno()));

            // Determine whether to apply the Uno punishment of adding a card
            // If the current player has uno and the current player has not called uno
            if (isUno(currentPlayer) && !currentPlayer.getCalledUno()) {
                // Add a card to the current player
                currentPlayer.addCard(deck);
                // Tell the player they did not call uno on time
                System.out.println("You didn't call Uno! You are forced to draw a card!");
            }
            // If the current player does not have uno and the current player does call uno
            if (!isUno(currentPlayer) && currentPlayer.getCalledUno()) {
                // Add a card to current player
                currentPlayer.addCard(deck);
                // Tell player they called uno illegally
                System.out.println("You called Uno when you didn't have Uno! You are forced to draw a card!");
            }
        }

        Scanner input = new Scanner(System.in);
        System.out.println("Would you like to play again? (y/n) ");
        if (input.nextLine().equals("y") || input.nextLine().equals("Y") || input.nextLine().equals("yes") || input.nextLine().equals("Yes"))
        {
            playGame();
        }

        System.out.println("Thanks for playing! Byeeee :)");
    }

    private String askForUno()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Would you like to call Uno? If so, type Uno! If not, idk do what you want :)");
        return input.nextLine();
    }

    public String getName()
    {
        //
        Scanner input = new Scanner(System.in);
        //
        System.out.print("Player, what is your name? ");
        //
        return input.nextLine();
    }

    public void printRules()
    {
        System.out.println(" da rules ");
    }

    public ArrayList<Card> makePlayerHand(int sizeOfHand)
    {
        ArrayList<Card> playerHand = new ArrayList<Card>();
        for (int i=0; i<sizeOfHand; i++)
        {
            // Get a random card from the deck and add it to the player's hand
            playerHand.add(deck.getCard((int)(Math.random()*sizeOfHand)));
        }
        return playerHand;
    }

    public void playCard()
    {
        // Checks if there is a card that the player can play
        // If there is, initializes the canPlayState at false
        // If there is not, initializes it to true
        boolean canPlayState = !isCardCanPlay();

        // If there is no card that the player can play, force them to draw a card
        if (canPlayState)
        {
            System.out.println("There is no card you can play. You are forced to draw a card.");
            currentPlayer.addCard(deck);
        }

        Scanner input = new Scanner(System.in);
        Card cardWantToPlay = null;
        int cardIndex;
        while (!canPlayState)
        {
            System.out.print("What card would you like to play? (Type 0 to draw a card)" + "\n" + "Type number: ");
            // Converts the number of card that the player chooses to an index that can be directly used in code
            cardIndex = input.nextInt() - 1;
            input.nextLine();

            // If the player wants to play a card
            if (cardIndex > -1)
            {
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
            else if (cardIndex == -1)
            {
                // Set the canPlayState to true
                canPlayState = true;
                // Draw a card
                currentPlayer.addCard(deck);
            }
        }
    }

    private void doCardAbility(Card cardWantToPlay)
    {
        // If the card has no ability, then do nothing and return
        if (!cardWantToPlay.getAbility().equals("none"))
        {
            return;
        }

        // Gets the next player
        // Defaults to the computer player
        Player nextPlayer = computerPlayer;
        // If the current player is the computer player
        if (currentPlayer.getName().equals("Computer"))
        {
            // set the next player to the human player
            nextPlayer = humanPlayer;
        }

        // Below checks what ability the card has and does that ability

        // If the card is a +2
        if(cardWantToPlay.getAbility().equals("+2"))
        {
            // Add 2 cards to the next player
            for (int i = 0; i < 2; i++)
            {
                nextPlayer.addCard(deck);
            }
        }

        // If the card is a +4
        if(cardWantToPlay.getAbility().equals("+4"))
        {
            // Add 2 cards to the next player
            for (int i = 0; i < 4; i++)
            {
                nextPlayer.addCard(deck);
            }
        }

        // If the card is a wild
        if(cardWantToPlay.getAbility().equals("Wild"))
        {
            // Initialize a scanner and boolean used to check for valid input
            Scanner input = new Scanner(System.in);
            boolean validInput = false;
            // Create a variable that will hold the user's desired color
            String color = "";

            // Keeps prompting user until a valid color is received
            while (!validInput)
            {
                // Gets color the player wants to change the deck to
                System.out.print("What color do you want to choose: ");
                color = input.nextLine();
                // If that input is a valid color
                if (color.equals("Red") || color.equals("Blue") || color.equals("Yellow") || color.equals("Green"))
                {
                    // Set the input to valid
                    validInput = true;
                }
            }
            // Update the game color to user's specified color
            currentColor = color;
        }
    }

    private boolean isCardCanPlay()
    {
        // Iterate through the current player's hand
        for (Card card: currentPlayer.getHand())
        {
            // If the card can be played, return true (the player can make a unique move)
            if (card.checkCanPlay(topCard, currentColor))
            {
                return true;
            }
        }
        // Return false, because the player is forced to draw a card
        return false;
    }

    public boolean isUno(Player currentPlayer)
    {
        return currentPlayer.getHand().size() == 1;
    }

    public boolean checkCalledUno(String input)
    {
        return input.contains("uno") || input.contains("Uno") || input.contains("UNO");
    }
}