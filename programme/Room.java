import java.util.HashMap;
import java.util.Set;

public class Room
{
    //attribut
    private String aDescription;
    private HashMap<String, Room> aExits;
    /****************************constructeur*********************************/
    /**
     * constructeur naturel de la class Room
     */
    public Room(final String pDescription)
    {
        this.aDescription = pDescription;
        aExits = new HashMap<>();
    }
    /**************************acesseur et modificateur **********************/
    /**
     * acesseur de Description
     */
    public String getDescription(){return this.aDescription;}

    public Room getExit(String pDirection)
    {
        return this.aExits.get(pDirection);
    }

    public String getExitString()
    {
        String vReturnString = "Exits:";
        Set<String> vKeys = this.aExits.keySet();
        for(String vExit : vKeys)
        {
            vReturnString += " "+vExit;
        }
        return vReturnString;
    }
    
    /**
     *modificateur setExits 
     */
    public void setExit(final String pDirection, final Room pNeighbor)
    {
        this.aExits.put(pDirection, pNeighbor);
    }
} // Room
