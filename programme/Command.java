/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * La classe Command s'occupe de tout ce qui est la verification de chaine de String contenant les commandes
 * 
 * @author  Espinasse Baptiste
 * @version 2017.10.23
 */
public class Command
{
    private String aCommandWord;
    private String aSecondWord;
    
    /**
     *constructeur naturel de la class Commade
     *
    */
    public Command(final String pCommandWord, final String pSecondWord)
    {
        this.aCommandWord = pCommandWord ;
        this.aSecondWord  = pSecondWord;
    }

    public String getCommandWord(){return this.aCommandWord;}
    public String getSecondWord(){return this.aSecondWord;}

    /**
     *methode de test pour savoir si il y a un second mot dans la comamande
     *@return si il y a une deuxieme mot retourne true
    */
    public boolean hasSecondWord()
    {
        return this.getSecondWord() != null;
    }

    /**
     *methode de test pour pour savoir si la commande existe
     *@return si la commande n'est pas comnue retourne true
    */    
    public boolean isUnknown()
    {
        return this.getCommandWord() == null;
    }
} // Command
