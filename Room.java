public class Room
{
    //attribut
    private String aDescription;
    private Room aNorthExit;
    private Room aSouthExit;
    private Room aWestExit;
    private Room aEastExit;
    /****************************constructeur*********************************/
    /**
     * constructeur naturel de la class Room
     */
    public Room(final String pDescription)
    {
        this.aDescription = pDescription;
    }
    /**************************acesseur et modificateur **********************/
    /**
     * acesseur de Description
     */
    public String getDescription(){return this.aDescription;}

    public Room getExit(String pDirection)
    {
        if(pDirection.equals("north")) return this.aNorthExit;
        if(pDirection.equals("south")) return this.aSouthExit;
        if(pDirection.equals("east")) return this.aEastExit;
        if(pDirection.equals("west")) return this.aWestExit;
        return null;
    }

    public Room getExitString()
    {
        String vExitString="Exits: ";
        if(this.aNorthExit != null) vExitString += "north";
        if(this.aSouthExit != null) vExitString += "south";
        if(this.aEastExit != null) vExitString += "east";
        if(this.aWestExit != null) vExitString += "west";
        return vExitString;
    }
    
    /**
     *modificateur setExits 
     */
    public void setExits(final Room pNorthExit, final Room pSouthExit, final Room pEastExit, final Room pWestExit)
    {
        this.aNorthExit = pNorthExit;
        
        this.aSouthExit = pSouthExit;
        
        this.aEastExit  = pEastExit;
        
        this.aWestExit  = pWestExit;
    }
} // Room
