
/**
 * Décrivez votre classe Bearmer ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
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

    public boolean isCharged(){return this.aCharge;}

    public void charge(final Room pChargeRoom){
        this.aCharge = true;
        this.aChargeRoom = pChargeRoom;
    }
    public void discharge(){this.aCharge = false;}

    public Room getChargeRoom(){return this.aChargeRoom;}

}
