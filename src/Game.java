import java.util.ArrayList;
import java.util.Scanner;

public class Game
{
    private Player humanPlayer;
    private Player computerPlayer;
    public static Deck deck;
    private int currentPlayer;
    private String[] abilities = {"+2","+4","Wild"};
    private String[] colors = {"Red","Blue","Yellow","Green"};
    private int[] numbers = {0,1,2,3,4,5,6,7,8,9};
    public Card currentCard;
    private int sizeOfHand;

    public Game()
    {
        //
        this.currentPlayer = 1;
        // Creates the deck for the game
        deck = new Deck(abilities, colors, numbers);
        //
        Scanner input = new Scanner(System.in);
        System.out.println("How many cards should each player start with? (enter number)");
        sizeOfHand = input.nextInt();
        input.nextLine();
        //
        humanPlayer = new Player("Computer", makePlayerHand(sizeOfHand));
        computerPlayer = new Player(getName(), makePlayerHand(sizeOfHand));

    }

    public void playGame()
    {

    }

    public String getName()
    {
        //
        Scanner input = new Scanner(System.in);
        //
        System.out.println("Player, what is your name?");
        //
        return input.nextLine();
    }

    public void printRules()
    {
        System.out.println(" da rules ");
    }

    public ArrayList<Card> makePlayerHand(int sizeOfHand)
    {
        
    }


}
