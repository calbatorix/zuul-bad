
/**
 * Décrivez votre classe Bearmer ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Bearmer extends Item
{
    private boolean aCharge;

    public Bearmer(final String pDescription, final double pPrix, final double pPoids, final boolean pCharge){
        super(pDescription,pPrix,pPoids);
        this.aCharge = pCharge;
    }

    public boolean isCharged(){return this.aCharge;}

    public void charge(){this.aCharge = true;}
    public void discharge(){this.aCharge = false;}
}
