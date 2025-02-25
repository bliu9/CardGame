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
    private final int RULES_START_Y = 275;
    private final int RULES_SPACING_Y = 50;
    private final int RULES_FONT_SIZE = 50;
    private final int CARD_WIDTH = 100;
    private int CARD_HEIGHT;
    private final double CARD_WIDTH_TO_HEIGHT_RATIO = 1.495327102804;
    private final int CARD_DISPLAY_SPACING = 15;
    private final int CARD_START_DISPLAY = 25;

    public GameViewer(Game game)
    {
        CARD_HEIGHT = (int)(CARD_WIDTH*CARD_WIDTH_TO_HEIGHT_RATIO);





        // Create reference to backend
        this.game = game;

        // Save background image
        this.backgroundImage = new ImageIcon("Resources/background2.jpg").getImage();

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

        // Draws the instructions screen
        if (game.getGameState().equals("rules"))
        {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial Black",Font.BOLD,RULES_FONT_SIZE));
            g.drawString("Welcome to Uno!",250,RULES_START_Y);

            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial Black",Font.BOLD,RULES_FONT_SIZE/2));
            g.drawString("The goal of the game is to be the first player to play all your cards.",25,RULES_SPACING_Y+RULES_START_Y);
            g.drawString("But be sure to call Uno when you have one card left, or you will be",25,2*RULES_SPACING_Y+RULES_START_Y);
            g.drawString("forced to draw a card.",325,3*RULES_SPACING_Y+RULES_START_Y);
        }

        // If the game is in the playing state
        else if (game.getGameState().equals("playing") || game.getGameState().contains("Uno") || game.getGameState().contains("Wild"))
        {
            // Draw the player's hand
            for (int i = 0; i<game.getCurrentPlayer().getHand().size(); i++)
            {
                g.setColor(Color.BLACK);
                g.setFont(new Font("Arial Black",Font.BOLD,RULES_FONT_SIZE));
                // Sets the text color for player 1 to blue and player 2 to red
                if (game.getCurrentPlayer().getName().equals(game.getPlayer(1).getName()))
                {
                    g.setColor(Color.BLUE);
                }
                else
                {
                    g.setColor(Color.RED);
                }
                g.drawString(game.getCurrentPlayer().getName()+"'s Cards:",CARD_START_DISPLAY,WINDOW_HEIGHT-CARD_HEIGHT-100);
                g.drawString(""+(i+1),CARD_START_DISPLAY+i*(CARD_WIDTH+CARD_DISPLAY_SPACING)+(CARD_WIDTH/2)-17,WINDOW_HEIGHT-CARD_HEIGHT-35);
                game.getCurrentPlayer().getHand().get(i).draw(g,CARD_START_DISPLAY+i*(CARD_WIDTH+CARD_DISPLAY_SPACING),WINDOW_HEIGHT-CARD_HEIGHT-25);
            }

            // Draw top card of the deck
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial Black",Font.BOLD,RULES_FONT_SIZE));
            g.drawString("Top Card",100,100);
            game.getTopCard().draw(g,150,125,true);

            // Print card choosing prompt
            g.setFont(new Font("Arial Black",Font.BOLD,RULES_FONT_SIZE/2));
            g.setColor(Color.WHITE);
            g.fillRoundRect(515,275,350,100,20,20);
            g.setColor(Color.BLACK);
            g.drawString("Choose a Card",590,334);






            // If the player didn't call uno when they had uno
            if (game.getGameState().equals("Uno Punishment 1"))
            {
                g.setFont(new Font("Arial Black",Font.BOLD,RULES_FONT_SIZE/2));
                g.setColor(Color.WHITE);
                g.fillRoundRect(515,275,350,100,20,20);
                g.setColor(Color.BLACK);
                g.drawString("You Had UNO!",590,334);
            }
            // If the player called uno when they didn't have uno
            else if (game.getGameState().equals("Uno Punishment 2"))
            {
                g.setFont(new Font("Arial Black",Font.BOLD,RULES_FONT_SIZE/2));
                g.setColor(Color.WHITE);
                g.fillRoundRect(515,275,350,100,20,20);
                g.setColor(Color.BLACK);
                g.drawString("You Didn't Have UNO!",530,334);
            }
            // if the player called uno correctly
            else if (game.getGameState().equals("Uno Success"))
            {
                g.setFont(new Font("Arial Black",Font.BOLD,RULES_FONT_SIZE/2));
                g.setColor(Color.WHITE);
                g.fillRoundRect(515,275,350,100,20,20);
                g.setColor(Color.BLACK);
                g.drawString("Great Job! Only One Card Left!",510,334);
            }
//            // Else just tell the user to press enter to continue
//            else
//            {
//                g.setFont(new Font("Arial Black",Font.BOLD,RULES_FONT_SIZE/2));
//                g.setColor(Color.WHITE);
//                g.fillRoundRect(515,275,350,100,20,20);
//                g.setColor(Color.BLACK);
//                g.drawString("Press Enter to Continue",520,334);
//            }




            // If the user played a wild card
            if (game.getGameState().equals("Wild"))
            {
                g.setFont(new Font("Arial Black",Font.BOLD,RULES_FONT_SIZE/2));
                g.setColor(Color.WHITE);
                g.fillRoundRect(515,275,350,100,20,20);
                g.setColor(Color.BLACK);
                g.drawString("Choose a Wild Color",550,334);
            }
            //
            if (game.getGameState().equals("Wild Chosen"))
            {
                g.setFont(new Font("Arial Black",Font.BOLD,RULES_FONT_SIZE/2));
                g.setColor(Color.WHITE);
                g.fillRoundRect(515,275,350,100,20,20);
                g.setColor(Color.BLACK);
                g.drawString("The Color is "+game.getCurrentColor(),550,334);
            }
        }
    }

    public ArrayList<Image> getCardImages()
    {
        return cardImages;
    }

}
