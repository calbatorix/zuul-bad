/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * @author  Espinasse Baptiste
 * @version 2017.12.16
 */
public class Game
{
    private UserInterface aGui;
    private GameEngine aEngine;
    /**
     * Construteur par defaut de la classe Game
     */
    public Game()
    {
        this.aEngine = new GameEngine();
        this.aGui = new UserInterface(this.aEngine);
        this.aEngine.setGUI(this.aGui);
    }
} // Game
