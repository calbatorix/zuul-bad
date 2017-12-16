
/**
 * Décrivez votre classe Bearmer ici.
 *
 * @author  Espinasse Baptiste
 * @version 2017.12.16
 */
public class Beamer extends Item
{
    private boolean aCharge;
    private Room aChargeRoom;

    public Beamer(final String pDescription, final double pPrix, final double pPoids){
        super(pDescription,pPrix,pPoids);
        this.aCharge = false;
    }

    public Beamer(final String pDescription, final double pPrix, final double pPoids, final Room pChargeRoom){
        super(pDescription,pPrix,pPoids);
        this.aCharge = true;
        this.aChargeRoom = pChargeRoom;
    }

    /**
     *Methode pour connaitre l'etat de charge du beamer
     *@return true si l ebeamer est charger
    */
    public boolean isCharged(){return this.aCharge;}

    /**
     *Methode pour cherger le beamer
     *@param pChargeRoom un Room correspondant a la piece ou le bearmer est charger
    */
    public void charge(final Room pChargeRoom){
        this.aCharge = true;
        this.aChargeRoom = pChargeRoom;
    }

    /**
     *Methode pour decherger le beamer
     *
    */ 
    public void discharge(){this.aCharge = false;}

    /**
     *Methode pour connaitre dans quel Room a ete charger le beamer
     *@return un Room qui est la piece ou le beamer a ete charger pour la derniere fois
    */
    public Room getChargeRoom(){return this.aChargeRoom;}

}
