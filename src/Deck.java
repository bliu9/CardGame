import java.util.ArrayList;

public class Deck
{
    private int cardsLeft;
    private ArrayList<Card> cards;

    public Deck(String[] rank, String[] suit, int[] value)
    {
        cards = new ArrayList<Card>();
        fillDeck(rank, suit, value);
        cardsLeft = cards.size();
    }

    private void fillDeck(String[] rank, String[] suit, int[] value)
    {
        for (String r : rank)
        {
            for (String s : suit)
            {
                for (int v : value)
                {
                    cards.add(new Card(r,s,v));
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
        if (isEmpty())
        {
            return null;
        }
        cardsLeft = cardsLeft - 1;
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
