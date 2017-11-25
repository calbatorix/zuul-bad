import java.util.HashMap;
import java.util.Stack;

/**
 * Décrivez votre classe Player ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Player
{
    private String aName;
    private double aWeight;
    private double aPV;
    private double aMana;
    private Room aLocalisation;
    private HashMap<Item, int> aInventary;
    private Stack<Room> aLastRooms;

    public Player(final String pName, final double pPv, final double pMana, final Room pLocalisation)
    {
        this.aName = pName;
        this.aPV = pPV;
        this.aMana = pMana;
        this.aLocalisation = pLocalisation;
        this.aWeight = 700;
        this.aInventary = new HashMap<>();
        this.aLastRooms = new Stack()
    }

    public String getName(){return this.aName;}
    public void setName(final String pName){this.aName = pName;}

    public double getWeight(){return this.aWeight;}
    public void setWeight(final double pWeight){this.aWeight = pWeight;}

    public double getPV(){return this.aPV;}
    public void setPV(final double pPV){this.aPV = pPV;}

    public double getMana(){return this.aMana;}
    public void setMana(final double pMana){this.aMana = pMana;}

    public Room getLocalisation(){return this.aLocalisation;}
    public void setLocalisation(final Room pLocalisation){this.aLocalisation = pLocalisation;}

    public 

}
