import java.util.ArrayList;

public class Deck {
    private int cardsLeft;
    private ArrayList<Card> cards;

    public Deck(String[] rank, String[] suit, int[] value){
        cards = new ArrayList<Card>();
        makeDeck();
        cardsLeft = cards.size();
    }

    private void makeDeck(){

    }
}
