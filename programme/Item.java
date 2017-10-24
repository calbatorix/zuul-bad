/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * La classe Item a pour but de créé des Items à placer dans les differante piece du jeu
 * Elle s'occupe aussi de tout le traitment des objets de cette class
 *
 * @author  Espinasse Baptiste
 * @version 2017.10.23
 */
public class Item
{
    private String aDescription;
    private double aPrix;
    private double aPoids;

    /**
     *constructeur naturel de la class Item
     *
    */
    public Item(final String pDescription, final double pPrix, final double pPoids)
    {
        this.aDescription = pDescription;
        this.aPrix        = pPrix;
        this.aPoids       = pPoids;
    }

    public String getDescriptionItem(){return this.aDescription;}
    public double getPrixItem(){return this.aPrix;}
    public double getPoidsItem(){return this.aPoids;}


    public void setDescriptionItem(final String pDescription){this.aDescription=pDescription;}
    public void setPrixItem(final double pPrix){this.aPrix=pPrix;}
    public void setPoidsItem(final double pPoids){this.aPoids=pPoids;}

    /**
     *redefinition de la methode toString
     *@return retourne un String contenant la description de l'Item
    */
    @Override
    public String toString()
    {
        return this.aDescription + "cette objet pèse " +this.aPoids+"Kg et coute "+this.aPrix+"piece d'or.\n";
    }

}
