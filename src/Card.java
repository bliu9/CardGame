import java.util.ArrayList;

public class Card {
    private String ability;
    private String color;
    private int number;

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

    public void checkCanPlay()
    {

    }
}
