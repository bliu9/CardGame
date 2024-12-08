import java.util.ArrayList;

public class Player 
{
    private ArrayList<Card> hand;
    private int points;
    private String name;
    private boolean calledUno;
    
    public Player(String name)
    {
        this.name = name;
        points = 0;
    }

    public Player(String name, ArrayList<Card> givenHand)
    {
        //
        calledUno = false;
        //
        this.name = name;
        // Adds all cards from givenHand to hand
        this.hand = givenHand;
        points = 0;
    }

    public boolean getCalledUno()
    {
        return calledUno;
    }

    public ArrayList<Card> getHand()
    {
        return hand;
    }

    public int getPoints()
    {
        return points;
    }

    public String getName()
    {
        return name;
    }

    public void addPoints(int points)
    {
        this.points += points;
    }

    public void addCard(Card card)
    {
        hand.add(card);
    }

    public void addCard(Deck deck)
    {
        hand.add(deck.deal());
    }

    public String toString()
    {
        return name + " has " + points + " points\n" + name + "'s cards: " + hand;
    }

    public void printHand()
    {
        System.out.println("Your cards: ");
        for (int i=1; i<=hand.size(); i++)
        {
            System.out.print(i+". ");
            System.out.println(hand.get(i-1));
        }
    }

    public boolean playCardHelper(Card playCard, Card currentCard, String currentColor)
    {
        // Check if you can play the specified card
        if (!playCard.checkCanPlay(currentCard, currentColor))
        {
            System.out.println("That card can't be played");
            return false;
        }

        // Prints a line that says which card the player plays
        System.out.println(this.name + " plays a " + playCard);
        hand.remove(playCard);

        return true;
    }

    public void setCalledUno(boolean calledUno)
    {
        this.calledUno = calledUno;
    }

    public boolean checkWin()
    {
        // if the player has no cards left
        if(hand.size() == 0)
        {
            //print congratulatory statement and return true
            System.out.println("CONGRATULATIONS " + name + "! YOU WIN!");
            return true;
        }

        return false;
    }
}
