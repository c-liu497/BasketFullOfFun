import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import java.lang.*;
import java.awt.image.*;
/**
 * The Menus class creates a JPanel for the Main Menu and Level Select screens depending on the constructor's parameter
 * pass. The Main Menu screen will have a title, a button to view instructions, a button to go to level select to play
 * the game, a button to view the high scores, and a button to exit the game. The Level Select screen will have a
 * title, 3 buttons for level difficulty, and 3 levels of gameplay for each level of difficulty. 
 *   
 * 
 * @author Top Of the Stack (Alice Z)
 * @version 1 05.16.16 Spent 3 hours
 * 
 * @author of modification C Liu on 05.26.16
 * @version 2 05.26.16 Spent 3 hours
 * added paintComponents in order to create graphics(20 clouds), imported images,
 * changed constraints in order to center buttons
 * 
 * @author Top Of the Stack (Alice Z)
 * @version 3 06.02.16 Spent 5 minutes
 * Now, the JFrame from GameRunner is being passed in as a reference variable. It is used to switch the panels that are
 * being displayed on it.
 * 
 * @author Top Of the Stack (Alice Z)
 * @version 3 06.04.16 Spent 2.5 hours
 * Main menu can now smoothly transition to instructions, high scores, level select, and goodbye. Also, the panel will
 * respond to pressing the "F1" key.
 * 
 * @author Top Of the Stack (Alice Z)
 * @version 3.1 06.04.16 Spent 1 hour
 * Added code into the ActionListeners of the Level 2 and Level 3 buttons so that unless the previous level is complete,
 * the user will get an error message telling them to complete the previous level.
 * 
 * @author of modification C Liu on 06.08.16
 * @version 4 06.08.16 Spent  10 mins
 * edited x values of graphics to center buttons
 * 
 * @author Top Of the Stack (Alice Z)
 * @version 5 06.09.16 Spent 1 hours
 * Level buttons now are fully functional. Level 2 and 3 are locked when the user first enters the game. A level can
 * only be unlocked if the previous level is played through successfully. If the level is unlocked, it prompts them 
 * for user input for their name. This is used for the high scores.
 * 
 * @author Top Of the Stack (Alice Z)
 * @version 5.1 06.09.16 Spent 1 hours
 * Added a few keyboard shortcuts. Shortcuts work but haven't coded what they do. Removed the add name dialog box
 * that appears after user clicks a level.
 * 
 * @author Top Of the Stack (Caroline L)
 * @version 5.1 06.09.16 Spent 10 mins 
 * Added the KeyboardFocus layouts in order to allow for key presses
 * to be heard.
 * 
 * @author Top Of the Stack (Alice Z)
 * @version 5.1 06.09.16 Spent 1 hour
 * Updated code for key shortcuts and are now all fully functional. Updated javadoc.
 * 
 * <b> Instance variables: </b>
 * <p>
 * <b> gbc </b> This is a reference variable to a GridBagConstraints object.
 * <p>
 * <b> gbl </b> This is a reference variable to a GridBagLayout object.
 * <p>
 * <b> j </b> This is a reference variable to the main JFrame object.
 * <p>
 * <b> levelOneDone </b> This boolean stores whether or not the user has completed level 1. If this is true, users
 * can access level 2.
 * <p>
 * <b> levelTwoDone </b> This boolean stores whether or not the user has completed level 2. If this is true, users
 * can access level 3.
 * <p>
 * <b> whichMenu </b> This int stores which menu is supposed to be constructed.
 * <p>
 * <b> name </b> This String stores the name
 */ 
public class Menus extends JPanel implements KeyListener
{
  private GridBagLayout gbl = new GridBagLayout();
  private GridBagConstraints gbc = new GridBagConstraints();
  private JFrame j;
  private static boolean levelOneDone = false, levelTwoDone = false;
  private int whichMenu;
  private String name;
  /**
   * The class constructor has a parameter pass to see which menu is going to be set up. If the pass is 0, the Main
   * Menu panel will be set up. Otherwise, the Level Select panel will be set up.
   * @param whichMenu An int used to determine which menu panel to set up.
   * @param jf used to pass in the JFrame
   */ 
  public Menus(int whichMenu, JFrame jf)
  {
    super();
    setPreferredSize(new Dimension( 1000,810));
    setLayout(gbl);
    gbc.insets = new Insets(100,0,0,0);
    this.whichMenu = whichMenu;
    if(whichMenu ==0)
      setUpMain();
    else
      setUpLevel();
    j = jf;
    setFocusable(true);
    addKeyListener(this);
  }
  
  /**
   * This deals with what happens when the user types something. It is an override of the method in KeyListener.
   * @param e This is a reference variable to KeyEvent.
   */ 
  public void keyTyped(KeyEvent e)
  {}
  /**
   * This deals with what happens when the user presses a key. It is an override of the method in KeyListener.
   * @param e This is a reference variable to KeyEvent.
   */ 
  public void keyPressed(KeyEvent e)
  {}
  /**
   * This deals with what happens when the user releases a key. It is an override of the method in KeyListener. This one
   * has all the code because if the user holds down a key, keyPressed would be called and thus, could result in
   * multiple happenings. However, a user can only release a key once so this would not have that same problem. The if
   * statements are to see if the user has released a certain key. The if statements are also used to determine which
   * action to do depending on which menu is currently being displayed.
   * @param ke This is a reference variable to KeyEvent.
   */ 
  public void keyReleased(KeyEvent ke) {
    boolean cancel = false;
    if (ke.getKeyCode() == KeyEvent.VK_1)
    {
      j.remove(Menus.this);
      if(whichMenu ==0)
        j.add(new InstructionScreen(j));
      else
      {
        while(true)
        {
        name = JOptionPane.showInputDialog("Please enter your name:");
        if(name == null)
        {
          cancel = true;
          break;
        }
        if(name.length() > 0)
          break;
        JOptionPane.showMessageDialog(null, "Your name cannot be blank!", "Error!", JOptionPane.ERROR_MESSAGE);
        revalidate();
        repaint();
      }
      if(!cancel)
        j.add(new BasketFun(1,"back1", new Color (37,177,77) , j, name));
      }
      j.revalidate();
      j.repaint();
    }
    else if (ke.getKeyCode() == KeyEvent.VK_2)
    {
      j.remove(Menus.this);
      if(whichMenu ==0)
        j.add(new Menus(1,j));
      else
      {
        if(levelOneDone)
        {
          while(true)
          {
          name = JOptionPane.showInputDialog("Please enter your name:");
          if(name == null)
          {
            cancel = true;
            break;
          }
          if(name.length() > 0)
            break;
          JOptionPane.showMessageDialog(null, "Your name cannot be blank!", "Error!", JOptionPane.ERROR_MESSAGE);
          revalidate();
          repaint();
        }
        if(!cancel)
          j.add(new BasketFun(2,"back2", new Color (0,126,255), j, name));
        }
        else
          JOptionPane.showMessageDialog(null, "This level is locked! Play the previous level to unlock this one.", "Error!", JOptionPane.ERROR_MESSAGE);       
      }
      j.revalidate();
      j.repaint();
    }
    else if (ke.getKeyCode() == KeyEvent.VK_3)
    {
      if(whichMenu ==0)
      {
        HighScores h = new HighScores(j);
        h.setUpHighScoresPanel();
        j.remove(Menus.this);
        j.add(h);
      }
      else
      { 
        if(levelTwoDone)
        {
          while(true)
          {
            name = JOptionPane.showInputDialog("Please enter your name:");
            if(name == null)
            {
              cancel = true;
              break;
            }
            if(name.length() > 0)
              break;
            JOptionPane.showMessageDialog(null, "Your name cannot be blank!", "Error!", JOptionPane.ERROR_MESSAGE);
            revalidate();
            repaint();
          }
          if(!cancel)
            j.add(new BasketFun(3,"back3", new Color (37,177,77), j, name));
        }
        else
          JOptionPane.showMessageDialog(null, "This level is locked! Play the previous level to unlock this one.", "Error!", JOptionPane.ERROR_MESSAGE);
      }
      j.revalidate();
      j.repaint();
    }
    else if (ke.getKeyCode() == KeyEvent.VK_4)
    {
      j.remove(Menus.this);
      if(whichMenu ==0)
        j.add(new Goodbye());
      else
        j.add(new Menus(0,j));
      j.revalidate();
      j.repaint();
    }
    else
      if(ke.getKeyCode() == KeyEvent.VK_F1)
    {
      try 
      {
        Runtime.getRuntime().exec("hh.exe ./Resources/GameHelp.chm");
      }
      catch(IOException i)
      {
      }
    }}
  
  /** Purpose: The purpose of this method is to 
    * paint the panel. It draws the sky, some clouds, and adds the text.
    * @param g Graphics allows use to the Graphics class.
    */
  public void paintComponent (Graphics g)
  {
    
    super.paintComponent(g);
    g.setColor (Colours.skyB);
    g.fillRect(0,0,1000,900);
    
    g.setColor (Color.white);
    g.fillOval(120,40,200,30);
    g.fillOval(650,40,120,30);
    
    g.fillOval(120,540,200,30);
    g.fillOval(190,560,220,40);
    g.fillOval(140,520,90,40);
    
    g.fillOval(680,540,120,30);
    g.fillOval(690,550,220,40);
    g.fillOval(710,520,90,70);
    
    for (int i=320;i<800;i=i+340)
    {
      g.fillOval(400,i,200,50);
      g.fillOval(290,i+30,300,60);
      g.fillOval(530,i+30,200,50);
      g.fillOval(410,i+50,200,50);
      g.fillOval(270,i+60,200,30);
    }
    
    g.fillOval(700,200,200,50);
    g.fillOval(690,230,300,60);
    g.fillOval(830,230,200,50);
    g.fillOval(710,250,200,50);
    g.fillOval(670,260,200,30);
    
    g.fillOval(150,200,200,50);
    g.fillOval(40,230,300,60);
    g.fillOval(280,230,200,50);
    g.fillOval(160,250,200,50);
    g.fillOval(20,260,200,30);
    
    try{
      BufferedImage logo = ImageIO.read(getClass().getResource("/Resources/"+"Title.jpg"));
      g.drawImage(logo,400,0,null);
      
    }
    catch(Exception e){
    }
    //clouds
    g.fillOval(30,40,70,20);
    g.fillOval(60,20,70,50);
    g.fillOval(80,30,70,20);
    g.fillOval(140,40,70,30);
    
    g.fillOval(680,20,70,50);
    g.fillOval(620,30,70,20);
    g.fillOval(680,40,70,30);
    g.fillOval(700,40,80,30);
    
    requestFocus();
  }
  
  /**
   * This will setup the Main Menu screen. The Main Menu screen will have a title, a button to view instructions
   * a button to go to level select to play the game, a button to view the high scores, and a button to exit the game.
   * The parameter for the inner methods is e - a reference variable for ActionEvent.
   */ 
  private void setUpMain()
  {
    JButton inst = new JButton("Instructions"),play = new JButton("Play Game");
    JButton high = new JButton("High Scores"),exit = new JButton("Exit Game");
    JButton chm=new JButton("Help Manual");
    setSize (800, 800);
    setVisible (true);
    gbc.weighty=0;
    gbc.anchor = gbc.LINE_START;
    gbc.gridy = 1;
    add(inst,gbc);
    inst.addActionListener (new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        j.remove(Menus.this);
        j.add(new InstructionScreen(j));
        j.revalidate();
        j.repaint();
      }});
    
    gbc.gridy=2;
    add(play,gbc);
    play.addActionListener (new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        j.remove(Menus.this);
        j.add(new Menus(1,j));
        j.revalidate();
        j.repaint();
      }});
    
    gbc.gridy=3;
    add(high,gbc);
    high.addActionListener (new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        HighScores h = new HighScores(j);
        h.setUpHighScoresPanel();
        j.remove(Menus.this);
        j.add(h);
        j.revalidate();
        j.repaint();
      }});
    
    gbc.gridy=4;
    add(chm,gbc);
    chm.addActionListener (new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        try 
        {
          Runtime.getRuntime().exec("hh.exe ./Resources/GameHelp.chm");
        }
        catch(IOException i)
        {
        }
      }});
    
    gbc.gridy=5;
    add(exit,gbc);
    exit.addActionListener (new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        j.remove(Menus.this);
        j.add(new Goodbye());
        j.revalidate();
        j.repaint();
      }});
    revalidate();
    repaint();
  }
  /**
   * This method returns the value of levelOneDone.
   * @return boolean that reyurns whether or not level is done
   */ 
  public static boolean getLevelOneDone()
  {
    return levelOneDone;
  }
  /**
   * This method returns the value of levelTwoDone.
   * @return boolean that reyurns whether or not level is done
   */ 
  public static boolean getLevelTwoDone()
  {
    return levelTwoDone;
  }
  /**
   * This method allows the BasketFun class to set the variable levelOneDone to true. This happens when the user
   * completes level 1.
   */ 
  public static void setLevelOneTrue()
  {
    levelOneDone = true;
  }
  /**
   * This method allows the BasketFun class to set the variable levelTwoDone to true. This happens when the user
   * completes level 2.
   */ 
  public static void setLevelTwoTrue()
  {
    levelTwoDone = true;
  }
  /**
   * This will set up the Level Select screen. The Level Select screen will have a title, 3 buttons for level
   * difficulty, and 3 levels of gameplay for each level of difficulty. When the user clicks a level difficulty button,
   * if it is unlocked, the 3 levels of gameplay buttons will appear. If the user clicks one and it isn't locked, the
   * user will proceed to the game screen. Otherwise, an error message will pop up.
   * The parameter for the inner methods is e - a reference variable for ActionEvent.
   */ 
  private void setUpLevel()
  {
    JButton exit = new JButton("Back to Main Menu");
    JButton levelOne = new JButton("Level 1"), levelTwo = new JButton("Level 2"), levelThree = new JButton("Level 3");
    gbc.weighty=1;
    gbc.anchor = GridBagConstraints.LINE_START;
    gbc.gridy=1;
    gbc.gridx=2;
    add(levelOne,gbc);
    levelOne.addActionListener (new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        boolean cancel = false;
        while(true)
        {
          name = JOptionPane.showInputDialog("Please enter your name:");
          if(name == null)
          {
            cancel = true;
            break;
          }
          if(name.length() > 0)
            break;
          JOptionPane.showMessageDialog(null, "Your name cannot be blank!", "Error!", JOptionPane.ERROR_MESSAGE);
          revalidate();
          repaint();
        }
        if(!cancel)
        {
          j.remove(Menus.this);
          j.add(new BasketFun(1,"back1", new Color (37,177,77) , j, name));
          j.revalidate();
          j.repaint();
        }
      }});
    gbc.gridy=2;
    add(levelTwo,gbc);
    levelTwo.addActionListener (new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        boolean cancel = false;
        if(levelOneDone)
        {
          while(true)
          {
            name = JOptionPane.showInputDialog("Please enter your name:");
            if(name == null)
            {
              cancel = true;
              break;
            }
            if(name.length() > 0)
              break;
            JOptionPane.showMessageDialog(null, "Your name cannot be blank!", "Error!", JOptionPane.ERROR_MESSAGE);
            revalidate();
            repaint();
          }
          if(!cancel)
          {
            j.remove(Menus.this);
            j.add(new BasketFun(2,"back2", new Color (0,126,255), j, name));
            j.revalidate();
            j.repaint();
          }
        }
        else
          JOptionPane.showMessageDialog(null, "This level is locked! Play the previous level to unlock this one.", "Error!", JOptionPane.ERROR_MESSAGE);
      }});
    
    gbc.gridy=3;
    add(levelThree,gbc);
    levelThree.addActionListener (new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        boolean cancel = false;
        if(levelTwoDone)
        {
          while(true)
          {
            name = JOptionPane.showInputDialog("Please enter your name:");
            if(name == null)
            {
              cancel = true;
              break;
            }
            if(name.length() > 0)
              break;
            JOptionPane.showMessageDialog(null, "Your name cannot be blank!", "Error!", JOptionPane.ERROR_MESSAGE);
            revalidate();
            repaint();
          }
          if(!cancel)
          {
            j.remove(Menus.this);
            j.add(new BasketFun(3,"back3", new Color (37,177,77), j, name));
            j.revalidate();
            j.repaint();
          }
        }
        else
          JOptionPane.showMessageDialog(null, "This level is locked! Play the previous level to unlock this one.", "Error!", JOptionPane.ERROR_MESSAGE);
      }});
    
    gbc.gridy=4;
    add(exit,gbc);
    exit.addActionListener (new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        j.remove(Menus.this);
        j.add(new Menus(0,j));
        j.revalidate();
        j.repaint();
      }});
    revalidate();
    repaint();
  }
}