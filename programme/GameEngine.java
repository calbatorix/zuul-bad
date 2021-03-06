import java.util.HashMap;
import java.util.Stack;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
//import java.io.PrintWriter;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * La classe Room a pour but de generer tout les element du scenario (piece, item ...) et toute l'interface graphique
 * 
 * @author  Espinasse Baptiste
 * @version 2017.12.16
 */
public class GameEngine
{
    private Parser aParser;
    private HashMap<String, Room> aListeRoom;
    private UserInterface aGui;
    private Player aPlayer;
    private int aCommantInput;

    /**
     * Constructeur d'objets de classe GameEngine
     */
    public GameEngine()
    {
        this.createRooms();
        this.aPlayer = new Player("joueur", this.aListeRoom.get("Piece de depart"));
        //this.createPlayer();
        this.aParser = new Parser();
        this.aCommantInput = 0;
    }
    /**
     *Procedure de creation de l'une interface graphique
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
        Room currentRoom = this.aPlayer.getLocalisation();
        this.aGui.println(currentRoom.getLongDescription());
        this.aGui.showImage(this.aPlayer.getLocalisation().getImageName());
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

        aListeRoom = new HashMap();
        Item vTorche = new Item("vielle Torche",2,10);
        Item vMagicCookie = new Item("cookie magique",50,0);
        Item vBlocDePierre = new Item("gros bloc de pierre",0,700);
        Item vAntidote = new Item("antidote",1000,5);
        Beamer vBeamer = new Beamer("teleporteur",100,2);

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
        vPieceDeDepart.setExit("east",vCouloir1,false);
        vPieceDeDepart.setExit("west",vPrison,false);
        vPrison.setExit("east",vPieceDeDepart,false);
        vCouloir1.setExit("south",vCouloir2,false);
        vCouloir1.setExit("west",vPieceDeDepart,false);
        vCouloir2.setExit("north",vCouloir1,false);
        vCouloir2.setExit("south",vCouloir3,false);
        vCouloir2.setExit("east",vCouloir5,false);
        vCouloir3.setExit("north",vCouloir2,false);
        vCouloir3.setExit("west",vCouloir4,false);
        vCouloir4.setExit("south",vTourRDC,false);
        vCouloir4.setExit("east",vCouloir3,false);
        vCouloir4.setExit("west",vSortie,false);
        vCouloir5.setExit("east",vCouloir6,false);
        vCouloir5.setExit("west",vCouloir2,false);
        vCouloir6.setExit("north",vSalleDesGardes,false);
        vCouloir6.setExit("south",vCouloir7,false);
        vCouloir6.setExit("west",vCouloir2,false);
        vCouloir7.setExit("north",vCouloir6,false);
        vCouloir7.setExit("south",vJardin,false);
        vCouloir7.setExit("east",vCouloir8,false);
        vCouloir8.setExit("east",vBureau,true);
        vCouloir8.setExit("west",vCouloir7,false);
        vSortie.setExit("east",vCouloir4,false);
        vTourRDC.setExit("north",vCouloir4,false);
        vTourRDC.setExit("up",vTourHight,false);
        vTourHight.setExit("down",vTourRDC,false);
        vSalleDesGardes.setExit("south",vCouloir6,false);
        vJardin.setExit("north",vCouloir7,false);
        vBureau.setExit("east",vTresor,false);
        //vBureau.setExit("west",vCouloir8,true);
        vTresor.setExit("west",vBureau,false);
        
        vPieceDeDepart.takeItem("torche", vTorche);
        vPieceDeDepart.takeItem("magicCookie", vMagicCookie);
        vPieceDeDepart.takeItem("beamer", vBeamer);
        vPieceDeDepart.takeItem("blocDePierre", vBlocDePierre);
        vTresor.takeItem("antidote", vAntidote);
        //initialisation lieu courant
    }

    /**
     *Procedure qui a pour but d'appeler la bonne methode en fonction de la commande passé en parametre
     *@param pCommandLine un String contenant la commande tapée par le joueur
    */
    public void interpretCommand(String pCommandLine) 
    {   
        if(time())return;
        this.aGui.println(pCommandLine);
        Command command = this.aParser.getCommand(pCommandLine);

        if(command.isUnknown()) {
            this.aGui.println("I don't know what you mean...");
            return;
        }

        CommandWord commandWord = command.getCommandWord();
        switch(commandWord){
            case HELP  : printHelp(); break;
            case GO    : goRoom(command); break;
            case TEST  : test(command); break;
            case TAKE  : take(command); break;
            case DROP  : drop(command); break;
            case LOOK  : look(); break;
            case EAT   : eat(command); break;
            case BACK  : back(); break;
            case ITEMS : items(); break;
            case CHARGE: charge(command); break;
            case TP    : teleport(command); break;
            case QUIT  : if(command.hasSecondWord())
                             this.aGui.println("Quit what?");
                         else
                             endGame();
                         break;
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

        Room vNextRoom = this.aPlayer.getLocalisation().getExit(vDirection);

        if (vNextRoom == null) this.aGui.println("There is no door !");
        else
        {
            if(this.aPlayer.getLocalisation().isTrapDoor(vDirection)==true) this.aPlayer.resetLastRoom();
            else this.aPlayer.setLastRoom(this.aPlayer.getLocalisation());
            this.aPlayer.setLocalisation(vNextRoom);
            this.aGui.println(this.aPlayer.getLocalisation().getLongDescription());
            if(this.aPlayer.getLocalisation().getImageName() != null)
                this.aGui.showImage(this.aPlayer.getLocalisation().getImageName());
        }
    }

    /**
     *procedure effectuee lorsque la commande test est tapee
     *@param pCommand le deuxieme String de la commande tapee par le joueur qui est l'emplacement du fichier conteant la suite des commande de test
    */
    private void test(final Command pCommand)
    {
        if(!pCommand.hasSecondWord())
        {
            this.aGui.println("test what?");
            return;
        }         

        String vFile = pCommand.getSecondWord();
        Scanner vScan = null;

        try {vScan = new Scanner(new File("./"+vFile+".txt"));}
        catch ( final java.io.FileNotFoundException pException )
        {
            this.aGui.println("File not find");
        }

        while(vScan.hasNextLine())
        {
            String vLigne = vScan.nextLine();
            interpretCommand(vLigne);
        }
        if (vScan != null) {vScan.close();}
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
        this.aGui.println(this.aPlayer.getLocalisation().getLongDescription());
    }
    
    /**
     *procedure efectuee lorsque la commande eat est tapee
     *@param pCommand le deuxieme String de la commande tapee par le joueur qui est l'item a manger
    */
    private void eat(final Command pCommand)
    {
        if(!pCommand.hasSecondWord())
        {
            this.aGui.println("eat what?");
            return;
        }   

        String vItem = pCommand.getSecondWord();

        Item vToEat = this.aPlayer.getItem(vItem);

        if(vToEat == null) this.aGui.println("I don't have it !");
        else if(vItem.equals("magicCookie")){
            this.aPlayer.takeItem(vItem, vToEat);
            this.aPlayer.getLocalisation().dropItem(vItem);
            this.aPlayer.setStrong(this.aPlayer.getStrong()+100);
            this.aGui.println("You have eat the magic cookie");
        }
        else
            this.aGui.println("You have eaten now and you are not hungry any more.");
    }

    /**
     *procedure effectuee lorsque la commande back est tapee
     *la commande back permet de revenir dans ma salle precedante
    */
    private void back()
    {
        if(this.aPlayer.lastRoomsIsEmpty() == true)
        {
            this.aGui.println("You are all ready in your first localisation.");
        }
        else
        {
            this.aGui.println("your go back in the last room");
            this.aPlayer.setLocalisation(this.aPlayer.getLastRoom());
        }

        this.aGui.println(this.aPlayer.getLocalisation().getLongDescription());
        if(this.aPlayer.getLocalisation().getImageName() != null)
                this.aGui.showImage(this.aPlayer.getLocalisation().getImageName());
    }

    /**
     *procedure efectuee lorsque la commande take est tapee
     *@param pCommand le deuxieme String de la commande tapee par le joueur qui est l'item a prendre 
    */
    private void take(final Command pCommand)
    {
        if(!pCommand.hasSecondWord())
        {
            this.aGui.println("take what ?");
            return;
        }  

        String vItem = pCommand.getSecondWord();

        Item vToTake = this.aPlayer.getLocalisation().getItem(vItem);

        if (vToTake == null) this.aGui.println("this item is not here !");
        else if(this.aPlayer.canITake(vToTake.getPoidsItem()) == false) this.aGui.println("this item is too heavy !");
        else{
            this.aPlayer.takeItem(vItem, vToTake);
            this.aPlayer.getLocalisation().dropItem(vItem);
            this.aGui.println("I take the item");

        }
    }

    /**
     *procedure efectuee lorsque la commande drop est tapee
     *@param pCommand le deuxieme String de la commande tapee par le joueur qui est l'item a deposer 
    */
    private void drop(final Command pCommand)
    {
        if(!pCommand.hasSecondWord())
        {
            this.aGui.println("drop what ?");
            return;
        }

        String vItem = pCommand.getSecondWord();

        Item vToDrop = this.aPlayer.getItem(vItem);

        if(vToDrop == null) this.aGui.println("I don't have it !");
        else{
            this.aPlayer.getLocalisation().takeItem(vItem, vToDrop);
            this.aPlayer.dropItem(vItem);
            this.aGui.println("I have drop it !");
        }
    }

    /**
     *procedure effectuee lorsque la commande items est tapee
     *la commande items afiiche tout les items contenue dans l'inventaire du joueur
    */
    private void items()
    {
        this.aGui.println(this.aPlayer.getItemsString());
    }

    /**
     *procedure efectuee lorsque la commande charge est tapee
     *@param pCommand le deuxieme String de la commande tapee par le joueur qui est le beamer a charger 
    */
    private void charge(final Command pCommand)
    {
        if(!pCommand.hasSecondWord())
        {
            this.aGui.println("Charge what ?");
            return;
        }

        String vStringBeamer = pCommand.getSecondWord();
        Beamer vBeamer = (Beamer)this.aPlayer.getItem(vStringBeamer);

        if(vBeamer == null) this.aGui.println("I don't have it !");
        else{
            vBeamer.charge(this.aPlayer.getLocalisation());
            this.aGui.println("The beamer is charged");
        }

    }
    /**
     *procedure efectuee lorsque la commande tp est tapee
     *@param pCommand le deuxieme String de la commande tapee par le joueur qui est le beamer a utiliser
    */
    private void teleport(final Command pCommand)
    {
        if(!pCommand.hasSecondWord())
        {
            this.aGui.println("Teleport with what ?");
            return;
        }

        String vStringBeamer = pCommand.getSecondWord();
        Beamer vBeamer = (Beamer)this.aPlayer.getItem(vStringBeamer);

        if(vBeamer == null) this.aGui.println("I don't have it !");
        else if(vBeamer.isCharged()==false) this.aGui.println("The beamer is not charged");
        else{
            vBeamer.discharge();
            this.aPlayer.resetLastRoom();
            this.aPlayer.setLocalisation(vBeamer.getChargeRoom());
            this.aGui.println(this.aPlayer.getLocalisation().getLongDescription());
            if(this.aPlayer.getLocalisation().getImageName() != null)
                this.aGui.showImage(this.aPlayer.getLocalisation().getImageName());
        }
    }

    /**
     *methode qi veririfie si le nombre de commande max rentrer n'est pas depacee
     *@return true si le nombre de commande est depacee
    */
    private boolean time(){
        this.aCommantInput++;
        if(this.aCommantInput>200){
            this.aGui.println("You are out of time");
            this.aGui.enable(false);
            return true;
        }
        else return false;
    }

}