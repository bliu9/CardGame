import java.util.ArrayList;

public class Deck
{
    private int cardsLeft;
    private ArrayList<Card> cards;

    public Deck(String[] ability, String[] color, int[] number)
    {
        cards = new ArrayList<Card>();
        fillDeck(ability, color, number);
        cardsLeft = cards.size();
    }

    private void fillDeck(String[] ability, String[] color, int[] number)
    {
        // For each color, create cards with that color and all the number values
        for (String c : color)
        {
            for (int n : number)
            {
                cards.add(new Card("none", c, n));
            }
        }

        // Make an ability card for every color
        for (String a : ability)
        {
            for (String c : color)
            {
                if (a.equals("Wild"))
                {
                    cards.add(new Card(a, "all", -1));
                }
                else
                {
                    cards.add(new Card(a, c, -1));
                }
            }
        }
    }

    public boolean isEmpty()
    {
        return cardsLeft == 0;
    }

    public int getCardsLeft()
    {
        return cards.size();
    }

    public Card deal()
    {
        // If the deck is "empty" (cardsLeft == 0)
        if (isEmpty())
        {
            // Shuffle the deck and reset cardsLeft
            shuffle();
            cardsLeft = cards.size();
            //return null;
        }

        // Decrement cardsLeft, while simultaneously converting cardsLeft from normal index form to array index form
        cardsLeft = cardsLeft - 1;
        // return the card at that value
        return cards.get(cardsLeft);
    }

    public void shuffle()
    {
        for (int i = cardsLeft-1; i >= 0; i--)
        {
            int switchIndex = (int) (Math.random()*(i+1));
            cards.set(i,cards.set(switchIndex, cards.get(i)));
        }
    }
}
