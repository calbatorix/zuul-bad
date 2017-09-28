public class Game
{
    //attribut
    private Room aCurrentRoom;
    private Parser aParser;
    
    public Game()
    {
        this.createRooms();
        this.aParser = new Parser();
    }
    private void createRooms()
    {
        //declaration des room
        Room vPieceDeDepart = new Room("Piece de depart");
        Room vPrison = new Room("prison");
        Room vCouloir1 = new Room("couloir1");
        Room vCouloir2 = new Room("couloir1");
        Room vCouloir3 = new Room("couloir3");
        Room vCouloir4 = new Room("couloir4");
        Room vCouloir5 = new Room("couloir5");
        Room vCouloir6 = new Room("couloir6");
        Room vCouloir7 = new Room("couloir7");
        Room vCouloir8 = new Room("couloir8");
        Room vSortie = new Room("sortie");
        Room vTourRDC = new Room("RDC de la tour");
        Room vSalleDesGardes = new Room("Salle des gardes");
        Room vJardin = new Room("Jardin");
        Room vBureau = new Room("Bureau");
        Room vTresor = new Room("salle au tresor");

        //positionement des sorties
        vPieceDeDepart.setExits(null, null, vCouloir1, vPrison);
        vPrison.setExits(null, null,vPieceDeDepart,null);
        vCouloir1.setExits(null, vCouloir2, null, vPieceDeDepart);
        vCouloir2.setExits(vCouloir1,vCouloir3,vCouloir5,null);
        vCouloir3.setExits(vCouloir2,null,null,vCouloir4);
        vCouloir4.setExits(null,vTourRDC,vCouloir3,vSortie);
        vCouloir5.setExits(null,null,vCouloir6,vCouloir2);
        vCouloir6.setExits(vSalleDesGardes,vCouloir7,null,vCouloir2);
        vCouloir7.setExits(vCouloir6,vJardin,vCouloir8,null);
        vCouloir8.setExits(null,null,vBureau,vCouloir7);
        vSortie.setExits(null,null,vCouloir4,null);
        vTourRDC.setExits(vCouloir4,null,null,null);
        vSalleDesGardes.setExits(null,vCouloir6,null,null);
        vJardin.setExits(vCouloir7,null,null,null);
        vBureau.setExits(null,null,vTresor,vCouloir8);

        //initialisation lieu courant
        this.aCurrentRoom =vPieceDeDepart;
    }
    
    private void printLocationInfo()
    {
            System.out.println("You are in the "+ this.aCurrentRoom.getDescription());
            /*System.out.print("Exit(s):");
            if(this.aCurrentRoom.getExit("north") != null) System.out.print("north ");
            if(this.aCurrentRoom.getExit("south") != null) System.out.print("south ");
            if(this.aCurrentRoom.getExit("east") != null) System.out.print("east ");
            if(this.aCurrentRoom.getExit("west") != null) System.out.print("west");*/
            System.out.println(this.aCurrentRoom.getExitString());
    }
    
    private void goRoom(final Command pCommand)
    {
        if(!pCommand.hasSecondWord())
        {
            System.out.println("go where ?");
            return;
        }  
        
        String vDirection = pCommand.getSecondWord();

        Room vNextRoom = this.aCurrentRoom.getExit(vDirection);

        if (vNextRoom == null) System.out.println("There is no door !");
        else
        {
            this.aCurrentRoom = vNextRoom;
            this.printLocationInfo();
        }
    }
    
    private void printWelcome()
    {
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        this.printLocationInfo();
    }
    
    private void printHelp()
    {
        System.out.println("You are lost. You are alone.");
        System.out.println("You wander around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("    go quit help");
    }
    
    private boolean quit(final Command pCommand)
    {
        if(pCommand.hasSecondWord() == true)
        {
            System.out.println("Quit what ?");
            return false;
        }  
        else return true ;
    }
    
    public boolean processCommand(final Command pCommand)
    {
        if (! pCommand.getCommandWord().equals("help") 
                && !pCommand.getCommandWord().equals("quit")
                    && !pCommand.getCommandWord().equals("go"))
                {
                    System.out.println("I don't know what you mean...");
                    return false;
                }
        if (pCommand.getCommandWord().equals("help")) 
        {
            printHelp();
            return false;
        }
        if (pCommand.getCommandWord().equals("go"))
        {
            goRoom(pCommand);
            return false;
        }
        if (pCommand.getCommandWord().equals("quit"))
        { 
            Command vCommand = new Command(pCommand.getSecondWord(), null);
            return quit(vCommand);
        }
        return false;
    }
    
    public void play()
    {
     printWelcome(); 
     boolean vFinished = false;
     while(!vFinished)
     {
         Command vCommand = this.aParser.getCommand();
         vFinished = processCommand(vCommand);
     }
     System.out.println("Thank you for playing. Good bye.");
    }
} // Game
