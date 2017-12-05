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
    private Room aLocalisation;
    private Stack<Room> aLastRooms;
    private ItemList aItemsList;

    public Player(final String pName, final Room pLocalisation)
    {
        this.aName = pName;
        this.aLocalisation = pLocalisation;
        this.aWeight = 700;
        this.aLastRooms = new Stack();
        this.aItemsList = new ItemList();
    }

    public String getName(){return this.aName;}
    public void setName(final String pName){this.aName = pName;}

    public double getWeight(){return this.aWeight;}
    public void setWeight(final double pWeight){this.aWeight = pWeight;}

    public Room getLocalisation(){return this.aLocalisation;}
    public void setLocalisation(final Room pLocalisation){this.aLocalisation = pLocalisation;}

    public Room getLastRoom(){return this.aLastRooms.pop();}
    public void setLastRoom(final Room pLastRoom){this.aLastRooms.push(pLastRoom);}
    public boolean lastRoomsIsEmpty(){return this.aLastRooms.empty();}

    public void takeItem(final String pStringItem, final Item pItem){this.aItemsList.takeItem(pStringItem,pItem);}
    public void dropItem(final String pStringItem){this.aItemsList.dropItem(pStringItem);}
    public Item getItem(final String pItem){return this.aItemsList.getItem(pItem);}
}
