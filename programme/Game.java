import java.util.HashMap;

public class Game
{
    //attribut
    private Room aCurrentRoom;
    private Parser aParser;
    private HashMap<String, Room> aListeRoom;
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

        aListeRoom = new HashMap();
        this.aListeRoom.put("Piece de depart",vPieceDeDepart);
        this.aListeRoom.put("couloir 1",vcouloir1);
        this.aListeRoom.put("couloir 2",vcouloir2);
        this.aListeRoom.put("couloir 3",vcouloir3);
        this.aListeRoom.put("couloir 4",vcouloir4);
        this.aListeRoom.put("couloir 5",vcouloir5);
        this.aListeRoom.put("couloir 6",vcouloir6);
        this.aListeRoom.put("couloir 7",vcouloir7);
        this.aListeRoom.put("couloir 8",vcouloir8);
        this.aListeRoom.put("Sortie",vSortie);
        this.aListeRoom.put("RDC de la tour",vTourRDC);
        this.aListeRoom.put("Sommet de la tour",)vTourHight;
        this.aListeRoom.put("Salle des gardes",vSalleDesGardes);
        this.aListeRoom.put("Jardin",vJardin);
        this.aListeRoom.put("Bureau",vBureau);
        this.aListeRoom.put("Salle au tresor",vTresor);

        //positionement des sorties
        vPieceDeDepart.setExit("east",vCouloir1);
        vPieceDeDepart.setExit("west",vPrison);
        vPrison.setExit("east",vPieceDeDepart);
        vCouloir1.setExit("south",vCouloir2);
        vCouloir1.setExit("west",vPieceDeDepart);
        vCouloir2.setExit("north",vCouloir1);
        vCouloir2.setExit("south",vCouloir3);
        vCouloir2.setExit("east",vCouloir5);
        vCouloir3.setExit("north",vCouloir2);
        vCouloir3.setExit("west",vCouloir4);
        vCouloir4.setExit("south",vTourRDC);
        vCouloir4.setExit("east",vCouloir3);
        vCouloir4.setExit("west",vSortie);
        vCouloir5.setExit("east",vCouloir6);
        vCouloir5.setExit("west",vCouloir2);
        vCouloir6.setExit("north",vSalleDesGardes);
        vCouloir6.setExit("south",vCouloir7);
        vCouloir6.setExit("west",vCouloir2);
        vCouloir7.setExit("north",vCouloir6);
        vCouloir7.setExit("south",vJardin);
        vCouloir7.setExit("east",vCouloir8);
        vCouloir8.setExit("east",vBureau);
        vCouloir8.setExit("west",vCouloir7);
        vSortie.setExit("east",vCouloir4);
        vTourRDC.setExit("north",vCouloir4);
        vTourRDC.setExit("up",vTourHight);
        vTourHight.setExit("down",vTourRDC);
        vSalleDesGardes.setExit("south",vCouloir6);
        vJardin.setExit("north",vCouloir7);
        vBureau.setExit("east",vTresor);
        vBureau.setExit("west",vCouloir8);
        vTresor.setExit("west",vBureau);
        //initialisation lieu courant
        this.aCurrentRoom =vPieceDeDepart;
    }
    
    public Room getRoom(final String pNomRoom){return this.aListeRoom.get(pNomRoom);}
    /**
     * Procedure affichant les information sur la salle actuel
     * comme sont nom ainsi que les sortis possible pour cette piece
     */
    private void printLocationInfo()
    {
            System.out.println("You are in the "+ this.aCurrentRoom.getDescription());
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
        System.out.println(this.aCurrentRoom.getLongDescription());
    //    this.printLocationInfo();
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
        aParser.showCommands();
    }

    private void look()
    { 
        System.out.println(this.aCurrentRoom.getLongDescription());
    }
    
    private void eat()
    {
        System.out.println("You have eaten now and you are not hungry any more.");
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
        if(pCommand.isUnknown()) System.out.println("I don't know what you mean...");
        if (pCommand.getCommandWord().equals("help"))  printHelp();
        if (pCommand.getCommandWord().equals("go"))    goRoom(pCommand);
        if (pCommand.getCommandWord().equals("look"))  look();
        if (pCommand.getCommandWord().equals("eat"))   eat();
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
