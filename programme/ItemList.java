import java.util.HashMap;
import java.util.Set;
/**
 * Décrivez votre classe ItemList ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
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
     *@param pStringItem est un String correspondant a la description/nom de l'item que l'on souhaite obtenir dans l'invantaire
    */
    public Item getItem(final String pItem){return this.aInventary.get(pItem);}

}
