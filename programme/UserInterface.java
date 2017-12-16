import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
/**
 * This class implements a simple graphical user interface with a text entry
 * area, a text output area and an optional image.
 * 
 * @author  Espinasse Baptiste
 * @version 2017.12.16
 */
public class UserInterface implements ActionListener
{
    private GameEngine aEngine;
    private JFrame     aMyFrame;
    private JTextField aEntryField;
    private JTextArea  aLog;
    private JLabel     aImage;
    private JPanel     aButton;
    private JButton    aButtonN, aButtonS ,aButtonE, aButtonW, aButtonU, aButtonD;
    private JButton    aButtonEat, aButtonLook, aButtonHelp;
    /**
     * Construct a UserInterface. As a parameter, a Game Engine
     * (an object processing and executing the game commands) is
     * needed.
     * 
     * @param pGameEngine  The GameEngine object implementing the game logic.
     */
    public UserInterface( final GameEngine pGameEngine )
    {
        this.aEngine = pGameEngine;
        this.createGUI();
    } // UserInterface(.)

    /**
     *@param pText un String de ce u il faut afficher
     * Print out some text into the text area.
     */
    public void print( final String pText )
    {
        this.aLog.append( pText );
        this.aLog.setCaretPosition( this.aLog.getDocument().getLength() );
    } // print(.)

    /**
       *@param pText un String de ce u il faut afficher
     * Print out some text into the text area, followed by a line break.
     */
    public void println( final String pText )
    {
        this.print( pText + "\n" );
    } // println(.)

    /**
     * Show an image file in the interface.
     */
    public void showImage( final String pImageName )
    {
        URL vImageURL = this.getClass().getClassLoader().getResource( pImageName );
        if ( vImageURL == null )
            System.out.println( "image not found" );
        else {
            ImageIcon vIcon = new ImageIcon( vImageURL );
            this.aImage.setIcon( vIcon );
            this.aMyFrame.pack();
        }
    } // showImage(.)

    /**
     * Enable or disable input in the input field.
     */
    public void enable( final boolean pOnOff )
    {
        this.aEntryField.setEditable( pOnOff );
        if ( ! pOnOff )
            this.aEntryField.getCaret().setBlinkRate( 0 );

    } // enable(.)

    /**
     * Set up graphical user interface.
     */
    private void createGUI()
    {
        this.aMyFrame = new JFrame( "Zork" );
        this.aEntryField = new JTextField( 34 );

        this.aLog = new JTextArea();
        this.aLog.setEditable( false );
        JScrollPane vListScroller = new JScrollPane( this.aLog );
        vListScroller.setPreferredSize( new Dimension(200, 200) );
        vListScroller.setMinimumSize( new Dimension(100,100) );

        JPanel vPanel = new JPanel();
        this.aImage = new JLabel();
        this.makeBoutonBar();
        
        //this.aButton1.addActionListener();
        
        vPanel.setLayout( new BorderLayout() );
        vPanel.add( this.aImage, BorderLayout.NORTH );
        vPanel.add( vListScroller, BorderLayout.CENTER );
        vPanel.add( this.aEntryField, BorderLayout.SOUTH );
        vPanel.add( this.aButton, BorderLayout.EAST );

        this.aMyFrame.getContentPane().add( vPanel, BorderLayout.CENTER );

        // add some event listeners to some components
        this.aMyFrame.addWindowListener( new WindowAdapter() {
            public void windowClosing(WindowEvent e) { System.exit(0); }
        } );

        this.aEntryField.addActionListener( this );

        this.aMyFrame.pack();
        this.aMyFrame.setVisible( true );
        this.aEntryField.requestFocus();
    } // createGUI()

    public void makeBoutonBar()
    {
        aButton = new JPanel();
        aButton.setLayout(new GridLayout(0,1,3,5));
        
        this.aButtonN = new JButton("north");        
        this.aButtonN.addActionListener(this);
        this.aButtonS = new JButton("south");
        this.aButtonS.addActionListener(this);
        this.aButtonE = new JButton("east");
        this.aButtonE.addActionListener(this);
        this.aButtonW = new JButton("west");
        this.aButtonW.addActionListener(this);
        this.aButtonEat = new JButton("eat"); 
        this.aButtonEat.addActionListener(this);
        this.aButtonLook = new JButton("look"); 
        this.aButtonLook.addActionListener(this);
        this.aButtonHelp = new JButton("help"); 
        this.aButtonHelp.addActionListener(this);

        aButton.add( this.aButtonN);
        aButton.add( this.aButtonS);
        aButton.add( this.aButtonE);
        aButton.add( this.aButtonW);
        aButton.add( this.aButtonEat);
        aButton.add( this.aButtonHelp);
        aButton.add( this.aButtonLook);

    }
    
    /**
     * Actionlistener interface for entry textfield.
     */
    public void actionPerformed( final ActionEvent pE ) 
    {
        // no need to check the type of action at the moment.
        // there is only one possible action: text entry
        if(pE.getSource() == this.aButtonN){ this.aEngine.interpretCommand("go north");}
        if(pE.getSource() == this.aButtonS){ this.aEngine.interpretCommand("go south");}
        if(pE.getSource() == this.aButtonE){ this.aEngine.interpretCommand("go east");}
        if(pE.getSource() == this.aButtonW){ this.aEngine.interpretCommand("go west");}
        if(pE.getSource() == this.aButtonEat){ this.aEngine.interpretCommand("eat");}
        if(pE.getSource() == this.aButtonLook){ this.aEngine.interpretCommand("look");}
        if(pE.getSource() == this.aButtonHelp){ this.aEngine.interpretCommand("help");}
        if(pE.getSource() == this.aEntryField) {processCommand();}
    } // actionPerformed(.)

    /**
     * A command has been entered. Read the command and do whatever is 
     * necessary to process it.
     */
    private void processCommand()
    {
        String vInput = this.aEntryField.getText();
        this.aEntryField.setText( "" );

        this.aEngine.interpretCommand( vInput );
    } // processCommand()
} // UserInterface 
