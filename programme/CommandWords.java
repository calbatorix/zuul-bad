/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration table of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Espinasse Baptiste
 * @version 2017.12.16
 */

import java.util.HashMap;

public class CommandWords
{
    private HashMap<String,CommandWord> aValidCommands;

    /**
     * Constructeur par defaut
     */
    public CommandWords()
    {
        this.aValidCommands = new HashMap<String, CommandWord>();
        for(CommandWord command : CommandWord.values()){
            if(command != CommandWord.UNKNOWN)
                this.aValidCommands.put(command.toString(), command);
        }
    } // CommandWords()
    /**
    *Cette methode a pour but de retouver la commande souhaiter dans le hasmap
    *@param pCommandWord Un String correspondant a la commande que l'on souhaite realiser
    *@return Le CommandWord correspondant au String en parametre
    */
    public CommandWord getCommandWord(String pCommandWord)
    {
        CommandWord vCommand = this.aValidCommands.get(pCommandWord);
        if(vCommand != null) {
            return vCommand;
        }
        else {
            return CommandWord.UNKNOWN;
        }
    }

    /**
     * Verifie si une String donnee fait partie des commandes valides. 
     * @param pString la String a tester
     * @return true si pString est une comande valide, false sinon
     */
    public boolean isCommand( final String pString )
    {
        return this.aValidCommands.containsKey(pString);
    } // isCommand()

    /**
     * cette method à pour but de retournee un String contenant toute les commandes realisable
     *@return Un String contenant toute les commandes
     */
    public String getCommandList() 
    {
        StringBuilder commands = new StringBuilder();
        for(String vCommand : this.aValidCommands.keySet()) {
            commands.append( vCommand + "  " );
        }
        return commands.toString(); 
    }

} // CommandWords
