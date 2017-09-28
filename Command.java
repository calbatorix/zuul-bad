public class Command
{
    private String aCommandWord;
    private String aSecondWord;
    
    public Command(final String pCommandWord, final String pSecondWord)
    {
        this.aCommandWord = pCommandWord ;
        this.aSecondWord  = pSecondWord;
    }
    
    public String getCommandWord(){return this.aCommandWord;}
    public String getSecondWord(){return this.aSecondWord;}
    
    public boolean hasSecondWord()
    {
        return this.getSecondWord() != null;
    }
    
    public boolean isUnknown()
    {
        return this.getCommandWord() == null;
    }
} // Command
