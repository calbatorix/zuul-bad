import java.util.HashMap;

public class GameEngine
{
    private Room aCurrentRoom;
    private Room aLastRoom;
    private Parser aParser;
    private HashMap<String, Room> aListeRoom;
    private UserInterface aGui;

    /**
     * Constructeur d'objets de classe GameEngine
     */
    public GameEngine()
    {
        this.createRooms();
        this.aParser = new Parser();
    }

    public void setGUI(UserInterface pUserInterface)
    {
        this.aGui = pUserInterface;
        printWelcome();
    }

    private void printWelcome()
    {
        this.aGui.print("\n");
        this.aGui.println("Welcome to the World of Zuul!");
        this.aGui.println("World of Zuul is a new, incredibly boring adventure game.");
        this.aGui.println("Type 'help' if you need help.");
        this.aGui.print("\n");
        this.aGui.println(this.aCurrentRoom.getLongDescription());
        this.aGui.showImage(this.aCurrentRoom.getImageName());
    //    this.printLocationInfo();
    }

    private void createRooms()
    {
        //declaration des room
        Room vPieceDeDepart = new Room("Piece de depart","images/PieceDeDepart.jpg");
        Room vPrison = new Room("prison","images/Prison.jpg");
        Room vCouloir1 = new Room("couloir1","images/Couloir.jpg");
        Room vCouloir2 = new Room("couloir1","images/Couloir.jpg");
        Room vCouloir3 = new Room("couloir3","images/Couloir.jpg");
        Room vCouloir4 = new Room("couloir4","images/Couloir.jpg");
        Room vCouloir5 = new Room("couloir5","images/Couloir.jpg");
        Room vCouloir6 = new Room("couloir6","images/Couloir.jpg");
        Room vCouloir7 = new Room("couloir7","images/Couloir.jpg");
        Room vCouloir8 = new Room("couloir8","images/Couloir.jpg");
        Room vSortie = new Room("sortie","images/Sortie.jpg");
        Room vTourRDC = new Room("RDC de la tour","images/RDCTour.jpg");
        Room vTourHight = new Room("Sommet de la tour","images/SommetTour.jpg");
        Room vSalleDesGardes = new Room("Salle des gardes","images/SalleDesGardes.jpg");
        Room vJardin = new Room("Jardin","images/Jardin.jpg");
        Room vBureau = new Room("Bureau","images/Bureau.jpg");
        Room vTresor = new Room("salle au tresor","images/SalleAuTresor.jpg");
        
        Item vTorche = new Item("vielle Torche",2,05);

        aListeRoom = new HashMap();
        this.aListeRoom.put("Piece de depart",vPieceDeDepart);
        this.aListeRoom.put("couloir 1",vCouloir1);
        this.aListeRoom.put("couloir 2",vCouloir2);
        this.aListeRoom.put("couloir 3",vCouloir3);
        this.aListeRoom.put("couloir 4",vCouloir4);
        this.aListeRoom.put("couloir 5",vCouloir5);
        this.aListeRoom.put("couloir 6",vCouloir6);
        this.aListeRoom.put("couloir 7",vCouloir7);
        this.aListeRoom.put("couloir 8",vCouloir8);
        this.aListeRoom.put("Sortie",vSortie);
        this.aListeRoom.put("RDC de la tour",vTourRDC);
        this.aListeRoom.put("Sommet de la tour",vTourHight);
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
        
        vPieceDeDepart.addItem("torche", vTorche);
        //initialisation lieu courant
        this.aCurrentRoom =vPieceDeDepart;
    }

    public Room getRoom(final String pNomRoom){return this.aListeRoom.get(pNomRoom);}

    public void interpretCommand(String pCommandLine) 
    {
        this.aGui.println(pCommandLine);
        Command command = this.aParser.getCommand(pCommandLine);

        if(command.isUnknown()) {
            this.aGui.println("I don't know what you mean...");
            return;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))        printHelp();
        else if (commandWord.equals("go"))     goRoom(command);
        else if (commandWord.equals("look"))   look();
        else if (commandWord.equals("eat"))    eat();
        else if (commandWord.equals("back"))   back();
        else if (commandWord.equals("quit")) {
            if(command.hasSecondWord())
                this.aGui.println("Quit what?");
            else
                endGame();
        }
    }

    private void printHelp()
    {
        this.aGui.println("You are lost. You are alone.");
        this.aGui.println("You wander around at the university.");
        this.aGui.print("Your command words are: "); 
        this.aGui.print(this.aParser.showCommands());   
    }

    private void goRoom(final Command pCommand)
    {
        if(!pCommand.hasSecondWord())
        {
            this.aGui.println("go where ?");
            return;
        }  
        
        String vDirection = pCommand.getSecondWord();

        Room vNextRoom = this.aCurrentRoom.getExit(vDirection);

        if (vNextRoom == null) this.aGui.println("There is no door !");
        else
        {
            this.aLastRoom = this.aCurrentRoom;
            this.aCurrentRoom = vNextRoom;
            this.aGui.println(this.aCurrentRoom.getLongDescription());
            if(this.aCurrentRoom.getImageName() != null)
                this.aGui.showImage(this.aCurrentRoom.getImageName());
        }
    }

    private void endGame()
    {
        this.aGui.println("Thank you for playing.  Good bye.");
        this.aGui.enable(false);
    }

    private void look()
    { 
    //    if(!pCommand.hasSecondWord())
    //    {
            this.aGui.println(this.aCurrentRoom.getLongDescription());
    //        return;
    //    } 

    //    String vNameitem = pCommand.getSecondWord();

    //    if (vNameitem == null) this.aGui.println("There is no item !");
    //    else
    //    {
    //        this.aGui.println(this.aCurrentRoom.getLongDescription());
    //        if(this.aCurrentRoom.getImageName() != null)
    //            this.aGui.showImage(this.aCurrentRoom.getImageName());
    //    }
    }
    
    private void eat()
    {
        this.aGui.println("You have eaten now and you are not hungry any more.");
    }

    private void back()
    {
        Room vCurrentRoom = this.aCurrentRoom;
        this.aCurrentRoom = this.aLastRoom;
        this.aLastRoom = vCurrentRoom;

        this.aGui.println(this.aCurrentRoom.getLongDescription());
        if(this.aCurrentRoom.getImageName() != null)
            this.aGui.showImage(this.aCurrentRoom.getImageName());
    }

}
