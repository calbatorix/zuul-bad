import java.util.HashMap;
import java.util.Set;
/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * @author  Espinasse Baptiste
 * @version 2017.12.16
 */
public class ItemList
{
    private HashMap<String, Item> aInventary;

    /**
     * Constructeur d'objets de classe ItemList
     */
    public ItemList()
    {
        this.aInventary = new HashMap<>();
    }

    /**
     *cette procedure a pour but d'afficher tout les items contenu dans l'inventaire dela piece ou du player
     *@return Un String de tout les items contenu dans l'inventaire
    */
    public String getItemString(){
        String vReturnString = "";
        Set<String> vKeys = this.aInventary.keySet();
        for(String vItem : vKeys)
        {
            vReturnString += " a "+vItem+"\n";
        }
        return vReturnString;
    }

    /**
     *methode permettant de mettre un item en plus dans l'inventaire
     *@param pStringItem est un String correspondant a la description/nom de l'item a ajouter dans l'inventaire
     *@param pItem est l'item a ajouter dansl'inventaire
    */
    public void takeItem(final String pStringItem, final Item pItem){this.aInventary.put(pStringItem, pItem);}

    /**
     *methode permettant de retirer un item de l'inventaire
     *@param pStringItem Un string contenant la description/nom de l'item a retirer de l'inventaire
    */
    public void dropItem(final String pStringItem){this.aInventary.remove(pStringItem);}

    /**
     *getter d'un item a partir de la description/nom de l'item
     *@param pItem est un String correspondant a la description/nom de l'item que l'on souhaite obtenir dans l'invantaire
    */
    public Item getItem(final String pItem){return this.aInventary.get(pItem);}

}
