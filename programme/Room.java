import java.util.HashMap;
import java.util.Set;
/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * La classe Room a pour but de generer des pieces
 * Elle possede comme attribut une courte description de la piece ainsi que que toute les direction de sortie, liée à leur destination
 * 
 * @author  Espinasse Baptiste
 * @version 2017.09.30
 */
public class Room
{
    //attribut
    private String aDescription;
    private HashMap<String, Room> aExits;
    private Stack<Room> aLastRooms;
    private HashMap<String, Item> aItems;
    private String aImageName;
    /****************************constructeur*********************************/
    /**
     * constructeur naturel de la class Room
     * @param demande en parametre un String decrivent la Room
     */
    public Room(final String pDescription, String pImageName)
    {
        this.aDescription = pDescription;
        aExits = new HashMap<>();
        aItems = new HashMap<>();
        this.aImageName = pImageName;
    }
    /**************************acesseur et modificateur **********************/
    /**
     * acesseur de la description de Room
     * @return aDescription
     */
    public String getDescription(){return this.aDescription;}
    
    /**
     * acesseur des direction de sortie de la piece
     * @param demande un String contenant une direction
     * @return aExits
     */
    public Room getExit(String pDirection){return this.aExits.get(pDirection);}
    
    /**
     * acesseur transmettant toute les direction de sortie de la piece
     * @return un String contenant toute de la direction de sortie de la piece
     */
    public String getExitString()
    {
        String vReturnString = "Exits:";
        Set<String> vKeys = this.aExits.keySet();
        for(String vExit : vKeys)
        {
            vReturnString += " "+vExit;
        }
        return vReturnString;
    }

    public String getItemsString()
    {
        String vReturnString = "they are in the room : ";
        Set<String> vKeys = this.aItems.keySet();
        for(String vItem : vKeys)
        {
            vReturnString += " a "+vItem+"\n";
        }
        return vReturnString;
    }
    
    /**
     *modificateur permetant de crée/modifier une direction de sortie de la piece courante 
     *@param elle demande un String contenant le nom de la direction de sorite, et une Room de destination
     */
    public void setExit(final String pDirection, final Room pNeighbor)
    {
        this.aExits.put(pDirection, pNeighbor);
    }

    public void addItem(final String pNomItem, final Item pItem )
    {
        this.aItems.put(pNomItem, pItem);
    }

    public String getLongDescription()
    {
        return " You are in " + this.aDescription + ".\n" + getExitString()+"\n"+getItemsString();
    }

    public String getImageName(){return this.aImageName;}
} // Room
