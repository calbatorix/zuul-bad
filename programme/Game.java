public class Game
{
    //attribut
    private Room aCurrentRoom;
    private Parser aParser;
    /**
     * Construteur par defaut de la classe Game
     */
    public Game()
    {
        this.createRooms();
        this.aParser = new Parser();
    }
    /**
     * Procedure de creation de toute les salles du jeu
     */
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
        Room vTourHight = new Room("Sommet de la tour");
        Room vSalleDesGardes = new Room("Salle des gardes");
        Room vJardin = new Room("Jardin");
        Room vBureau = new Room("Bureau");
        Room vTresor = new Room("salle au tresor");

        //positionement des sorties
        //vPieceDeDepart.setExits(null, null, vCouloir1, vPrison);
        vPieceDeDepart.setExit("east",vCouloir1);
        vPieceDeDepart.setExit("west",vPrison);
        //vPrison.setExits(null, null,vPieceDeDepart,null);
        vPrison.setExit("east",vPieceDeDepart);
        //vCouloir1.setExits(null, vCouloir2, null, vPieceDeDepart);
        vCouloir1.setExit("south",vCouloir2);
        vCouloir1.setExit("west",vPieceDeDepart);
        //vCouloir2.setExits(vCouloir1,vCouloir3,vCouloir5,null);
        vCouloir2.setExit("north",vCouloir1);
        vCouloir2.setExit("south",vCouloir3);
        vCouloir2.setExit("east",vCouloir5);
        //vCouloir3.setExits(vCouloir2,null,null,vCouloir4);
        vCouloir3.setExit("north",vCouloir2);
        vCouloir3.setExit("west",vCouloir4);
        //vCouloir4.setExits(null,vTourRDC,vCouloir3,vSortie);
        vCouloir4.setExit("south",vTourRDC);
        vCouloir4.setExit("east",vCouloir3);
        vCouloir4.setExit("west",vSortie);
        //vCouloir5.setExits(null,null,vCouloir6,vCouloir2);
        vCouloir5.setExit("east",vCouloir6);
        vCouloir5.setExit("west",vCouloir2);
        //vCouloir6.setExits(vSalleDesGardes,vCouloir7,null,vCouloir2);
        vCouloir6.setExit("north",vSalleDesGardes);
        vCouloir6.setExit("south",vCouloir7);
        vCouloir6.setExit("west",vCouloir2);
        //vCouloir7.setExits(vCouloir6,vJardin,vCouloir8,null);
        vCouloir7.setExit("north",vCouloir6);
        vCouloir7.setExit("south",vJardin);
        vCouloir7.setExit("east",vCouloir8);
        //vCouloir8.setExits(null,null,vBureau,vCouloir7);
        vCouloir8.setExit("east",vBureau);
        vCouloir8.setExit("west",vCouloir7);
        //vSortie.setExits(null,null,vCouloir4,null);
        vSortie.setExit("east",vCouloir4);
        //vTourRDC.setExits(vCouloir4,null,null,null);
        vTourRDC.setExit("north",vCouloir4);
        vTourRDC.setExit("up",vTourHight);
        vTourHight.setExit("down",vTourRDC);
        //vSalleDesGardes.setExits(null,vCouloir6,null,null);
        vSalleDesGardes.setExit("south",vCouloir6);
        //vJardin.setExits(vCouloir7,null,null,null);
        vJardin.setExit("north",vCouloir7);
        //vBureau.setExits(null,null,vTresor,vCouloir8);
        vBureau.setExit("east",vTresor);
        vBureau.setExit("west",vCouloir8);
        vTresor.setExit("west",vBureau);
        //initialisation lieu courant
        this.aCurrentRoom =vPieceDeDepart;
    }
    
    /**
     * Procedure affichant les information sur la salle actuel
     * comme sont nom ainsi que les sortis possible pour cette piece
     */
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
    
    /**
     * Procedure ayant pour but de permettre le deplacement du jour de piece en piece
     * @param demande une commande avec deux mots le premier go et le second la direction
     */
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
    
    /**
     * procedure affichant un message de bienvenue au jour
     */
    private void printWelcome()
    {
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        this.printLocationInfo();
    }
    
    /**
     * procedure affichant l'aide au joueur
     */
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
