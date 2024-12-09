public class Card {
    private String ability;
    private String color;
    private int number;

    public Card (String ability, String color, int number)
    {
        // Sets ability, color, and number
        this.ability = ability;
        this.color = color;
        this.number = number;
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
            // If the card is wild
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
