//Bryan Liu for CS2

import java.util.ArrayList;

public class Player
{
    private ArrayList<Card> hand;
    private int points;
    private String name;
    private boolean calledUno;
    
    public Player(String name)
    {
        // Sets the name
        this.name = name;
        // Sets points to 0
        points = 0;
    }

    public Player(String name, ArrayList<Card> givenHand)
    {
        // Sets the state of if the player called uno to false
        calledUno = false;
        // Sets the name
        this.name = name;
        // Adds all cards from givenHand to hand
        this.hand = givenHand;
        // Sets points to 0
        points = 0;
    }

    // Return the state of player's uno call
    public boolean getCalledUno()
    {
        return this.calledUno;
    }

    // Return the player's hand
    public ArrayList<Card> getHand()
    {
        return this.hand;
    }

    // Return the points of the player
    public int getPoints()
    {
        return this.points;
    }

    // Returns the name of the player
    public String getName()
    {
        return this.name;
    }

    // Add a given number of points to the user
    public void addPoints(int points)
    {
        this.points += points;
    }

    // Adds a given card to the player
    public void addCard(Card card)
    {
        this.hand.add(card);
    }

    // Adds a card from the deck to the player
    public void addCard(Deck deck)
    {
        this.hand.add(deck.deal());
    }

    // Returns the signature of the player (all instance variables)
    public String toString()
    {
        return name + " has " + points + " points\n" + name + "'s cards: " + hand;
    }

    // Returns the signature of the player's hand
    public void printHand()
    {
        System.out.println(name+"'s cards: ");
        // Iterates through the player's hand
        for (int i=1; i<=hand.size(); i++)
        {
            // Prints the card in the form of "1. Blue 2"
            System.out.print(i+". ");
            System.out.println(hand.get(i-1));
        }
        // Prints a buffer line
        System.out.println();
    }

    // Checks to see if the specified card can be played and prints a confirmation statement
    public boolean playCardHelper(Card playCard, Card currentCard, String currentColor)
    {
        // Check if you can play the specified card
        if (!playCard.checkCanPlay(currentCard, currentColor))
        {
            System.out.println("That card can't be played");
            return false;
        }

        // Prints a line that says which card the player plays
        System.out.println("\n"+this.name + " plays a " + playCard+"\n");
        hand.remove(playCard);

        return true;
    }

    // Sets the called Uno state for player
    public void setCalledUno(boolean calledUno)
    {
        this.calledUno = calledUno;
    }

    // Checks to see if the player has won
    public boolean checkWin()
    {
        // if the player has no cards left
        if(hand.isEmpty())
        {
            //print congratulatory statement and return true
            System.out.println("CONGRATULATIONS " + name + "! YOU WIN!");
            return true;
        }

        return false;
    }
}
