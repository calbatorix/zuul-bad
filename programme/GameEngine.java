import java.util.HashMap;
import java.util.Stack;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * La classe Room a pour but de generer tout les element du scenario (piece, item ...) et toute l'interface graphique
 * 
 * @author  Espinasse Baptiste
 * @version 2017.10.23
 */
public class GameEngine
{
    private Room aCurrentRoom;
    private Stack<Room> aLastRooms;
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
        this.aLastRooms = new Stack();
    }
    /**
     *
     *
    */
    public void setGUI(UserInterface pUserInterface)
    {
        this.aGui = pUserInterface;
        printWelcome();
    }

    /**
     *procedure affichant un message de bienvenu lors de l'ouverture de la fenetre de jeu
     *
    */
    private void printWelcome()
    {
        this.aGui.print("\n");
        this.aGui.println("Welcome to the World of Zuul!");
        this.aGui.println("World of Zuul is a new, incredibly boring adventure game.");
        this.aGui.println("Type 'help' if you need help.");
        this.aGui.print("\n");
        this.aGui.println(this.aCurrentRoom.getLongDescription());
        this.aGui.showImage(this.aCurrentRoom.getImageName());
    }

    /**
     *procedure qui à pour but de créé toute les piece du jeu et le contenu de ces pieces
     *
    */
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

    /**
     *Procedure qui a pour but d'appeler la bonne methode en fonction de la commande passé en parametre
     *@param pCommandLine un String contenant la commande tapée par le joueur
    */
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

    /**
     *procedure effectuee lorsque la commance help est tapee
     *permet de connaitre toute les commande existante
    */
    private void printHelp()
    {
        this.aGui.println("You are lost. You are alone.");
        this.aGui.println("You wander around at the university.");
        this.aGui.print("Your command words are: "); 
        this.aGui.print(this.aParser.showCommands());   
    }

    /**
     *procedure effectuee lorsque la commande go est tapee
     *@param pCommand le deuxieme String de la commande tapee par le joueur qui est la direction
    */
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
            this.aLastRooms.push(this.aCurrentRoom);
            this.aCurrentRoom = vNextRoom;
            this.aGui.println(this.aCurrentRoom.getLongDescription());
            if(this.aCurrentRoom.getImageName() != null)
                this.aGui.showImage(this.aCurrentRoom.getImageName());
        }
    }

    /**
     *precedure pour arreter le jeu
     *
    */
    private void endGame()
    {
        this.aGui.println("Thank you for playing.  Good bye.");
        this.aGui.enable(false);
    }

    /**
     *procedure effectuee lorsque la commande look est tapee par le joueur
     *permet d'avoir les information sur la piece actuel
    */
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
    
    /**
     *procedure efectuee lorsque la commande eat est tapee
     *
    */
    private void eat()
    {
        this.aGui.println("You have eaten now and you are not hungry any more.");
    }

    /**
     *procedure effectuee lorsque la commande back est tapee
     *la commande back permet de revenir dans ma salle precedante
    */
    private void back()
    {
        if(this.aLastRooms.empty() == true)
        {
            this.aGui.println("You are all ready in your first localisation.");
        }
        else
        {
            this.aGui.println("your go back in the last room");
            this.aCurrentRoom = this.aLastRooms.pop();
        }

        this.aGui.println(this.aCurrentRoom.getLongDescription());
        if(this.aCurrentRoom.getImageName() != null)
                this.aGui.showImage(this.aCurrentRoom.getImageName());
    }


}
