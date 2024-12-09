import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        // Prints a refresher for the rules of Uno
        Game.printRules();
        // Creates a new game of Uno
        Game game = new Game();
        // Starts the game
        game.playGame();
        // Asks the user if they want to play again or not
        playAgain(game);
    }

    private static void playAgain(Game game)
    {
        // Asks user if they would like to play again
        Scanner input = new Scanner(System.in);
        System.out.println("Would you like to play again? (y/n)");
        // if the user agrees to start a new game, start a new game
        if (input.nextLine().equals("y") || input.nextLine().equals("Y") || input.nextLine().equals("yes") || input.nextLine().equals("Yes"))
        {
            // Clears the screen and starts a new game
            Game.clearScreen();
            game = new Game();
        }
        // If they don't want to start a new game, prints a goodbye statement
        System.out.println("Thanks for playing! Byeeee :)");
    }
}