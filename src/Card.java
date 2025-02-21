//Bryan Liu for CS2

import java.awt.*;

public class Card {
    private String ability;
    private String color;
    private int number;
    private int cardIndex;
    private int x;
    private int y;
    private GameViewer window;

    public Card (String ability, String color, int number, int cardIndex, GameViewer window)
    {
        // Sets instance variables
        this.ability = ability;
        this.color = color;
        this.number = number;
        this.cardIndex = cardIndex;
        this.x = -1;
        this.y = -1;
        this.window = window;
    }

    public int getCardIndex()
    {
        return cardIndex;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public void draw(Graphics g)
    {
        g.drawImage(window.getCardImages().get(cardIndex),x,y,window);
    }

    public void draw(Graphics g, int new_X, int new_Y)
    {
        g.drawImage(window.getCardImages().get(cardIndex),new_X,new_Y,window);
    }

    // Returns card ability
    public String getAbility()
    {
        return ability;
    }

    // Returns card color
    public String getColor()
    {
        return color;
    }

    // Returns card number
    public int getNumber()
    {
        return number;
    }

    // Sets card ability
    public void setAbility(String ability)
    {
        this.ability = ability;
    }

    // Sets card color
    public void setColor(String color)
    {
        this.color = color;
    }

    // Sets card number
    public void setNumber(int number)
    {
        this.number = number;
    }

    // Returns a signature for the card
    @Override
    public String toString()
    {
        // If the card has no number
        if (this.number == -1)
        {
            // If the card is wild or +4
            if (this.color.equals("all"))
            {
                // Return "Wild Card"
                return ability + " Card";
            }
            // If the card doesn't have an ability
            else if (!this.ability.equals("none"))
            {
                // Return "Blue +2"
                return color + " " + ability;
            }
        }
        // If the card has no ability
        else if (this.ability.equals("none"))
        {
            // Return "Blue 2"
            return color + " " + number;
        }
        // Used for debugging
        return "Error printing card";
    }

    // Checks if a card can be played
    public boolean checkCanPlay(Card currentCard, String currentColor)
    {
        // If the color of the card the player wants to play is "all" or the colors match
        if (this.color.equals("all") || this.color.equals(currentColor))
        {
            // Can play card
            return true;
        }

        if (this.ability.equals(currentCard.ability) && !this.ability.equals("none"))
        {
            return true;
        }

        // If neither of the numbers are -1 (no number) and the numbers match
        else if (this.number != -1 && currentCard.number != -1 && this.number == currentCard.number)
        {
            // Can play card
            return true;
        }
        // If none of those conditions met, cannot play card
        return false;
    }
}
