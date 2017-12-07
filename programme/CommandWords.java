/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration table of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kolling and David J. Barnes + D.Bureau
 * @version 2008.03.30 + 2013.09.15
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
        this.aValidCommands.put("go", CommandWord.GO);
        this.aValidCommands.put("help", CommandWord.HELP);
        this.aValidCommands.put("quit", CommandWord.QUIT);
        this.aValidCommands.put("look", CommandWord.LOOK);
        this.aValidCommands.put("eat", CommandWord.EAT);
        this.aValidCommands.put("back", CommandWord.BACK);
        this.aValidCommands.put("test", CommandWord.TEST);
        this.aValidCommands.put("take", CommandWord.TAKE);
        this.aValidCommands.put("drop", CommandWord.DROP);
        this.aValidCommands.put("ITEMS", CommandWord.ITEMS);

    } // CommandWords()

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
     * Print all valid commands to System.out.
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
