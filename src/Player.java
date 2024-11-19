import java.util.ArrayList;

public class Player 
{
    private ArrayList<Card> hand;
    private int points;
    private String name;
    
    public Player(String name)
    {
        this.name = name;
        points = 0;
    }

    public Player(String name, ArrayList<Card> givenHand)
    {
        this.name = name;
        // Adds all cards from givenHand to hand
        this.hand.addAll(givenHand);
        points = 0;
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

    public void addCard(Card newCard)
    {
        hand.add(newCard);
    }

    public String toString()
    {
        return name + " has " + points + " points\n" + name + "'s cards: " + hand;
    }
}
