import java.util.Scanner;

public class Game
{
    private Player player;
    private Deck deck;
    private int currentPlayer;

    public Game()
    {
        this.currentPlayer = 1;
    }

    public String[] getNames()
    {
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
