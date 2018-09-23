import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/** Purpose: The purpose of this class is to animate and draw the 
  * company's intro and the game's intro. The game's logo will 
  * go to the centre of the screen from offscreen.
  * The the game's intro will happen. The game's intro consists 
  * of a series of baskets and the game's title.
  * <b>Instance variables: </b>
  * <p>
  * <b> logo </b> This creates our logo.
  * <p>
  * <b> t </b> This creates the timer used for the delay.
  * <p>
  * <b> count </b> This moves the images used in the splashscreen.
  * <p>
  * <b> j</b> This allows access to the JFrame
  * <p>
  * <b> s </b> This variable allows us to access a JPanel
  * 
  * @author Top Of The Stack(C Liu) 
  * @version 1 05.13.16
  * 
  * Modified by Top Of The Stack(C Liu) on 06.02.16. spent 3 hours
  * added Timer delay, as Thread.sleep causes issues. Added 
  * count variable in order to use Timer, added Timer var
  * in order to add delay, added logo in order to import logo.
  * added actionPerformed method in order to create delay.
  * Added better graphics.
  * 
  * Modified by Top Of The Stack(C Liu) on 06.06.16. spent 5 mins
  * added a jpg in order to make splashscreen more visually appealing.
  * changed value that stops count value from incrementing.
  * 
  * 
  * @author of modification (C Liu) on 06.08.16
  * @version 4 06.08.16 Spent  10 mins
  * edited stop values in order to center graphics
  * 
  */
public class SplashScreen extends JPanel implements ActionListener {
    private BufferedImage logo=null;
    Timer t=new Timer(10,this);
    int count=-100;
    JFrame j;
    JPanel s = this;
    /**Purpose: The purpose of this method is to stop the timer
      * once the image reaches a certain x-value.
      * 
      * @param a ActionEvent passes in an action event.
      */
    public void actionPerformed(ActionEvent a) {
        count=count+5;
        if (count==1190)
        {
            t.stop();
            j.remove(s);
            j.add(new Menus(0,j));
            j.revalidate();
            j.repaint();
        }
        repaint();
    }
    
    /**Purpose: The purpose of this constructor is to construct the
      * JFrame, set the size, and add this panel to the JFrame. 
      * It then allows the screen to be visible.
      *  @param j JFrame used to pass in the JFrame
      */
    public SplashScreen(JFrame j) { 
        this.j = j;
        this.setPreferredSize(new Dimension( 200,200));
        j.add(this);
        t.start();
    }
    
    /** Purpose: The purpose of this method is to 
      * paint the panel. It draws the sky, the sun, 
      * and a series of animated baskets.
      * @param g Graphics allows use to the Graphics class.
      */
    public void paintComponent (Graphics g)
    {
        super.paintComponent(g);
        try{
            g.setColor (Colours.skyB);
            g.fillRect(0,0,1000,900);
            BufferedImage c = ImageIO.read(getClass().getResource("/Resources/Cloud.jpg"));
            logo = ImageIO.read(getClass().getResource("/Resources/logo.jpg"));
            BufferedImage logo1 = ImageIO.read(getClass().getResource("/Resources/Title2.jpg"));
            
            g.drawImage(logo,count,200,null);
            g.drawImage(c,count+100,500,null);
            g.drawImage(c,count-200,25,null);
            g.drawImage(logo1,count-1000,100,null);
            
            g.setColor (Colours.skyB);
            g.fillRect(count-1,200,1,200);
        }
        catch(Exception e){
        }
    }
}
