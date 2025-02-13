//Bryan Liu for CS2

import javax.swing.*;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.util.ArrayList;

public class GameViewer extends JFrame
{
    private Game game;
    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 700;
    private final int TITLE_BAR_HEIGHT = 30;
    private ArrayList<Image> cardImages;
    private Image backgroundImage;
    private final int RULES_START_X = 80;
    private final int RULES_START_Y = 80;
    private final int RULES_SPACING_Y = 50;

    public GameViewer(Game game)
    {
        this.game = game;

        // Save background image
        this.backgroundImage = new ImageIcon("Resources/background2.jpg").getImage();
        //change this image to the background image ued in aquarium to debug

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
        g.drawImage(backgroundImage,0,TITLE_BAR_HEIGHT,WINDOW_WIDTH,WINDOW_HEIGHT,this);

        if (game.getGameState().equals("rules"))
        {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial Black",Font.BOLD,50));
            g.drawString("Welcome to Uno!",RULES_START_X,RULES_START_Y);
            g.drawString("The goal of the game is to be the first player to play all your cards.",RULES_START_X,RULES_SPACING_Y+RULES_START_Y);
            g.drawString("But be sure to call Uno when you have one card left, or you will be forced to draw a card.",RULES_START_X,2*RULES_SPACING_Y+RULES_START_Y);
        }
    }

    public ArrayList<Image> getCardImages()
    {
        return cardImages;
    }

}
