
import javax.swing.JComponent;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Color;

public class RandomPoint extends JComponent
{
    // Make instance variable(s) to hold references to
    // any objects you need to refer to.
    
    // Overload the constructor to pass reference(s) to 
    // any objects that you need to refer to.
	
	private int xPosy;
	private int yPosy;
    public RandomPoint(float deg, float rad)
    {
    	Random rng = new Random();
    	int r = rng.nextInt(50);
    	int x = Math.round(getWidth() / 2);
        int y = Math.round(getHeight() / 2);
        
        double rads = Math.toRadians(deg - 90);

        xPosy = Math.round((float) (x + Math.cos(r) * rad));
        yPosy = Math.round((float) (y + Math.sin(r) * rad));
    }
    
    public void paintComponent(Graphics g)
    {
        // Point random = getRandomPoint(degrees, (diameter / 2f) - (innerDiameter / 2));
    	// g2d.fillOval(random.x - (int) (innerDiameter / 2), random.y - (int) (innerDiameter / 2), 
    	// (int) innerDiameter, (int) innerDiameter);
    	
        g.setColor(Color.BLUE);
        g.fillOval(xPosy - (int) (40 / 2), yPosy - (int) (40 / 2), 
            	(int) 40, (int) 40);
    }
}
