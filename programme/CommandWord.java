/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * @author  Espinasse Baptiste
 * @version 2017.12.16
 */
public enum CommandWord
{
    // A value for each command word, plus one for unrecognised
    // commands.
    GO("go"), QUIT("quit"), HELP("help"), LOOK("look"), EAT("eat"), BACK("back"), TAKE("take"), DROP("drop"), ITEMS("items"), TEST("test"), UNKNOWN("?"), CHARGE("charge"), TP("tp");

    private String commandString;

    /**
     * Initialise with the corresponding command word.
     * @param commandString commandWord The command string.
     */
    CommandWord(String commandString){this.commandString = commandString;}

    /**
     * @return The command word as a string.
     */    
    public String toString(){return commandString;}
}
