public class Card {
    private String ability;
    private String color;
    private int number;
    //private String wildChosenColor;

    public Card (String ability, String color, int value)
    {
        this.ability = ability;
        this.color = color;
        this.number = value;
    }

    public String getAbility()
    {
        return ability;
    }

    public String getColor()
    {
        return color;
    }

    public int getNumber()
    {
        return number;
    }

    public void setAbility(String ability)
    {
        this.ability = ability;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }

    @Override
    public String toString()
    {
        if (this.number == -1)
        {
            if (this.color.equals("all"))
            {
                return ability + " Card";
            }
            else if (!this.ability.equals("none"))
            {
                return color + " " + ability;
            }
        }
        return color + " " + number;
    }

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
