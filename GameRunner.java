import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
/**
 * The GameRunner class is the driver class that creates a GameRunner object and a SplashScreen object as well
 * as executes the program. 
 * 
 * @author Top of the Stack (Alice Zhang) on 05.11.16
 * @version 1 05.13.16 Spent 0.5 hours
 * 
 * @author of modification Top of the Stack (Alice Zhang)
 * @version 2 06.01.16 Spent 0.5 hours
 * This class now has a constructor that creates the central JFrame that the game will be played on. It will also create
 * a SplashScreen object and add it to the JFrame to start the game off. The main method now creates a GameRunner
 * object.
 */ 

public class GameRunner extends JFrame {
    private File scores = new File("./Resources/HighScoresFile.alca");
    private final String HEADER = "This file is compatible with .alca files.";
    /** The class constructor creates the JFrame that the other classes' JPanels are going to be added on. It also creates
      * a SplashScreen object to add it to the JFrame to start the game.
      */ 
    public GameRunner()
    {
        super("A Basket Full of Fun");
        add(new SplashScreen (this));
        try
        {
            BufferedReader in = new BufferedReader(new FileReader (scores));
            if(!(in.readLine().equals(HEADER)))
            {
                JOptionPane.showMessageDialog(null, "This file has been corrupted!", "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(FileNotFoundException e)
        {
            try
            {
                PrintWriter out = new PrintWriter(new FileWriter(scores));
                out.println(HEADER);
                out.close();
            }
            catch(IOException g)
            {}
        }
        catch(IOException i)
        {}
        setSize (1000, 810);
        setResizable(false);
        setVisible (true);
    }
    /** This method calls the GameGUIRunner constructor to
      * create the application.
      * 
      * @param args []  String array that allows command line
      * parameters to be used when executing the program.
      */ 
    public static void main(String[] args) { 
        new GameRunner();
    }
}