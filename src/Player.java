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

    public void addCard(Card card)
    {
        hand.add(card);
    }

    public void addCard()
    {
        hand.add(Game.deck.deal());
    }

    public String toString()
    {
        return name + " has " + points + " points\n" + name + "'s cards: " + hand;
    }

    public void playCard(Card playCard)
    {
        // check if you can play the specified card
        playCard.checkCanPlay();

        // Prints a line that says which card the player plays
        System.out.println(this.name + " plays a " + playCard.toString());
        hand.remove(playCard);

        // Executes the ability of the card, if the card has an ability
        if (!playCard.getAbility().equals("none"))
        {
            playCard.doAbility();
        }
    }
}
