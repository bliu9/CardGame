//Bryan Liu for CS2

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameViewer extends JFrame
{
    private Game game;
    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 1000;
    private final int TITLE_BAR_HEIGHT = 32;
    private ArrayList<Image> cardImages;

    private Image backgroundImage;

    public GameViewer(Game game)
    {
        this.game = game;

        // Save background image
        this.backgroundImage = new ImageIcon("Resources/background.png").getImage();

        // Create and fill an arraylist with card images for the uno cards
        cardImages = new ArrayList<Image>();
        for (int i=0; i<52 ;i++)
        {
            cardImages.add(new ImageIcon("Resources/"+i+".png").getImage());
        }

        // Set up the window and set it to visible
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("UNO YAYYYYYYYYYYY!!!!!!!!!!!!!!!!!");
        this.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        this.setVisible(true);
    }

    public void paint(Graphics g)
    {
        g.drawImage(backgroundImage,30,30,WINDOW_WIDTH,WINDOW_HEIGHT,this);

        if (game.getGameState().equals("rules"))
        {
            g.drawString("Welcome to Uno!",50,50);
            g.drawString("The goal of the game is to be the first player to play all your cards.",50,100);
            g.drawString("But be sure to call Uno when you have one card left, or you will be forced to draw a card.",50,150);
        }
    }

}
