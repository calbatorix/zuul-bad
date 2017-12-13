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
    private double aStrong;
    private double aWeight;
    private Room aLocalisation;
    private Stack<Room> aLastRooms;
    private ItemList aItemsList;

    public Player(final String pName, final Room pLocalisation)
    {
        this.aName = pName;
        this.aLocalisation = pLocalisation;
        this.aStrong = 700;
        this.aWeight = 0;
        this.aLastRooms = new Stack();
        this.aItemsList = new ItemList();
    }

    /**
     *getter du nom du player
     *@return un String contenant le nom du player
    */
    public String getName(){return this.aName;}

    /**
     *setter permattant de modifier le nom du player
     *@param pName est un String contenant le nouveau nom du player
    */
    public void setName(final String pName){this.aName = pName;}

    /**
     *getter du poid max que peut porter le player
     *@return un double correspondant au poid max que le player peut transporter
    */
    public double getStrong(){return this.aStrong;}

    /**
     *setter permattant de modifier le poid max que la player peut transporter 
     *@param pStrong est un double contenant le nouveaux poid max que peut transporter le player
    */
    public void setStrong(final double pStrong){this.aStrong = pStrong;}

    /**
     *getter permettant de connaitre le poid que le player porte
     *@return un double contenant le poid que transporte le player
    */
    public double getWeight(){return this.aWeight;}

    /**
     *setter permattant de modifier le poid que le player transporte a l'intant t
     * @param pWeight est un double contenant le nouveaux poid que le player transporte
    */
    public void setWeight(final double pWeight){this.aWeight = pWeight;}

    /**
     *getter permettant de connaitre la localisation du player
     *@return un Room qui est la localisation actuelle du player
    */
    public Room getLocalisation(){return this.aLocalisation;}

    /**
     *setter permattant de modifier la Room dans laquel le player ce trouve
     * @param pLocalisation est un Room qui est la nouvelle localisation du player
    */
    public void setLocalisation(final Room pLocalisation){this.aLocalisation = pLocalisation;}

    /**
     *getter permettant de connaitre la precedente localisation du player
     *@return un Room qui est la localisation precedante du player
    */
    public Room getLastRoom(){return this.aLastRooms.pop();}

    /**
     *setter permattant de modifier le Room precedante ou ce trouver le player en l'ajoutant a la pile des Room precedante
     *@param pLastRoom est un Room qui a ajouter a la liste des localisation precedente
    */
    public void setLastRoom(final Room pLastRoom){this.aLastRooms.push(pLastRoom);}

    /**
     *Methode permetant de savoir si la liste des Room precedante est vide
     *@return true si la liste des localisation precedante du player est vide
    */
    public boolean lastRoomsIsEmpty(){return this.aLastRooms.empty();}

    /**
     *methode permettant de vider la liste des Room precedante
     *
    */
    public void resetLastRoom(){this.aLastRooms.clear();}

    /**
     *methode permettant de mettre un item en plus dans l'inventaire du player
     *@param pStringItem est un String correspondant a la description/nom de l'item a ajouter dans l'inventaire
     *@param pItem est l'item a ajouter dansl'inventaire
    */
    public void takeItem(final String pStringItem, final Item pItem){this.aItemsList.takeItem(pStringItem,pItem);}

    /**
     *methode permettant de retirer un item de l'inventaire du player
     *@param pStringItem Un string contenant la description/nom de l'item a retirer de l'inventaire
    */
    public void dropItem(final String pStringItem){this.aItemsList.dropItem(pStringItem);}

    /**
     *getter d'un item a partir de la description/nom de l'item
     *@param pStringItem est un String correspondant a la description/nom de l'item que l'on souhaite obtenir dans l'invantaire
    */
    public Item getItem(final String pItem){return this.aItemsList.getItem(pItem);}

    /**
     *Methode permmattant de savoir si le player peut prendre un item d'un certaint poid
     *@param pWeight est un double qui contient le poid de l'objet a ajouter dans l'inventaire
     *@return true si le player peut l'ajouter dans sont inventaire
    */
    public boolean canITake(final double pWeight){
        return (pWeight+this.aWeight <= this.aStrong) ;
    }

    /**
     *Methode affichant tout les item contenu dans l'inventaire du player
     *@return un String contanant la liste des item dans l'inventaire du player
    */
    public String getItemsString()
    {
        String vReturnString = "you got : "+this.aItemsList.getItemString();
        return vReturnString;
    }
}
