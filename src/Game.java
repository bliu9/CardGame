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

    public Game()
    {
        //
        this.currentPlayer = 1;
        // Creates the deck for the game
        deck = new Deck(abilities, colors, numbers);
        //
        humanPlayer = new Player();
        computerPlayer = new Player();

    }

    public void playGame()
    {

    }

    public String[] getName()
    {//CHANGE TO JUST GETTING ONE NAME FOR THE HUMAN PLAYER AND RETURNING THAT ONE NAME
        String[] names = new String[2];
        Scanner input = new Scanner(System.in);

        System.out.println("Player 1, what is your name?");
        names[0] = input.nextLine();

        System.out.println("Player 2, what is your name?");
        names[1] = input.nextLine();

        return names;
    }

    public void printRules()
    {
        System.out.println(" da rules ");
    }


}
