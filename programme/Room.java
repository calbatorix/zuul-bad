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
 * @version 2017.12.16
 */
public class Room
{
    //attribut
    private String aDescription;
    private HashMap<String, Room> aExits;
    private String aImageName;
    private ItemList aItemsList;
    private HashMap<String, Boolean> aTrapDoor;
    /****************************constructeur*********************************/
    /**
     * constructeur naturel de la class Room
     * @param pDescription un String decrivent la Room
     * @param pImageName String contenant le chemin de l'image
     */
    public Room(final String pDescription, String pImageName)
    {
        this.aDescription = pDescription;
        aExits = new HashMap<>();
        aTrapDoor = new HashMap<>();
        this.aImageName = pImageName;
        this.aItemsList = new ItemList();
    }

    /**************************acesseur et modificateur **********************/
    /**
     * acesseur de la description de Room
     * @return aDescription
     */
    public String getDescription(){return this.aDescription;}
    
    /**
     * acesseur des direction de sortie de la piece
     * @param pDirection un String contenant une direction
     * @return aExits
     */
    public Room getExit(String pDirection){return this.aExits.get(pDirection);}

    public Boolean isTrapDoor(String pDirection){return this.aTrapDoor.get(pDirection);}
    
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
    /**
     * acesseur transmettant une liste de tous les objets ce trouvant dans la piece
     * @return un String contenant tous les objets de la piece
     */
    public String getItemsString()
    {
        String vReturnString = "they are in the room : "+this.aItemsList.getItemString();
        return vReturnString;
    }
    
    /**
     *modificateur permetant de crée/modifier une direction de sortie de la piece courante 
     *@param pDirection demande un String contenant le nom de la direction de sorite
     *@param pNeighbor demande un String contenant le nom de la Room de destination
     */
    public void setExit(final String pDirection, final Room pNeighbor, final boolean pTrapDoor)
    {
        this.aExits.put(pDirection, pNeighbor);
        this.aTrapDoor.put(pDirection, pTrapDoor);
    }

    /**
     *methode ayant pour but de transmettre un String contenant tout les information de la piece, telle que les directions de sorties et les Items contenu dans la piece
     *@return un String contenant une description de la piece
    */
    public String getLongDescription()
    {
        return " You are in " + this.aDescription + ".\n" + getExitString()+"\n"+getItemsString();
    }

    /**
     *Methode pemetant de connaitre le chemin d'accee de l'image illustant la Room
     *@return un String contenant le chemin d'acces de l'image ilustrant la Room
    */
    public String getImageName(){return this.aImageName;}

    /**
     *Methode permmeatant d'ajouter un item dans l'inventaire de la Room
     *@param pStringItem est un String correspondant a la description/nom de l'item a ajouter dans l'inventaire
     *@param pItem est l'item a ajouter dansl'inventaire
    */
    public void takeItem(final String pStringItem, final Item pItem){this.aItemsList.takeItem(pStringItem,pItem);}

    /**
     *Methode permmeatant de retirer un item de l'inventaire de la Room
     *@param pStringItem est un String correspondant a la description/nom de l'item que l'on souhaite obtenir dans l'invantaire
    */
    public void dropItem(final String pStringItem){this.aItemsList.dropItem(pStringItem);}

    /**
     *Methode affichant tout les item contenu dans l'inventaire du player
     *@param pItem un String contenant le nom de l'item
     *@return un String contanant la liste des item dans l'inventaire de la Room
    */ 
    public Item getItem(final String pItem){return this.aItemsList.getItem(pItem);}
} // Room
