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
