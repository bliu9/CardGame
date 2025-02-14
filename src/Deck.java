//Bryan Liu for CS2

import java.util.ArrayList;
import java.lang.Math;

public class Deck
{
    private int cardsLeft;
    private ArrayList<Card> cards;
    private GameViewer window;

    public Deck(String[] ability, String[] color, int[] number, GameViewer window)
    {
        this.window = window;

        // Creates an array list that will store the cards in the deck
        cards = new ArrayList<Card>();
        // Fills that deck with all possible cards using the given abilities, colors, and numbers
        fillDeck(ability, color, number);
        // Shuffles the deck
        shuffle();
        // Sets the cardsLeft counter to the last index of the deck
        cardsLeft = cards.size();
    }

    // Fills the deck with cards based on given abilities, colors, and numbers
    private void fillDeck(String[] ability, String[] color, int[] number)
    {
        int cardIndex = 0;
        // For each color, create cards with that color and all the number values
        for (String c : color)
        {
            for (int n : number)
            {
                cards.add(new Card("none", c, n, cardIndex, window));
                cardIndex++;
            }
        }

        // Make an ability card for every color
        for (String a : ability)
        {
            for (String c : color)
            {
                // If the ability card is a wild card
                if (a.equals("Wild") || a.equals("+4"))
                {
                    // Set the color of the card to all, with a number value of none (-1)
                    cards.add(new Card(a, "all", -1,cardIndex,window));
                    cardIndex++;
                }
                else
                {
                    // Make the ability card normally, with a number value of none (-1)
                    cards.add(new Card(a, c, -1,cardIndex,window));
                    cardIndex++;
                }
            }
        }
        System.out.println(cards);//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    // Checks to see if the deck is "empty"
    public boolean isEmpty()
    {
        // Returns if the cardsLeft index is at the final card in the deck
        return cardsLeft == 0;
    }

    // Get the number of cards left
    public int getCardsLeft()
    {
        // Return the size of the deck
        return cards.size();
    }

    // Deal a card from the deck
    public Card deal()
    {
        // If the deck is "empty" (cardsLeft == 0)
        if (isEmpty())
        {
            // Shuffle the deck and reset cardsLeft
            shuffle();
            cardsLeft = cards.size();
        }
        // Decrement cardsLeft, while simultaneously converting cardsLeft from normal index form to array index form
        cardsLeft = cardsLeft - 1;
        // return the card at that value
        return cards.get(cardsLeft);
    }

    // Shuffles the deck
    public void shuffle()
    {
        // Iterate through the deck starting from the last card to the first
        for (int i = cards.size()-1; i>0; i--)
        {
            // Generates a switchIndex between 0 and i inclusive
            int switchIndex = (int) (Math.random()*(i+1));
            // Stores one of the cards to be switched in an intermediate variable
            Card intermediateCard = cards.get(i);
            // Swaps the cards at index i and switchIndex
            cards.set(i,cards.get(switchIndex));
            cards.set(switchIndex, intermediateCard);

        }
    }

    // Returns a card from the deck with the given index
    public Card getCard(int index)
    {
        // Returns the card at index
        return cards.get(index);
    }
}
