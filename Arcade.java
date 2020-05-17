import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;


public class Arcade extends JPanel 
    {
	
        private float movingDegree = 0;
        private float vel = 1f;
        private JButton button;
        private int x, y;
        private int targetX, targetY;
        private Graphics2D g2d;
        private double innerDiameter = 40.0;
        private int diameter;
        private double targetDegree = 90.0;
        private boolean remainingInGame = true;
        private int count = 0;
        private JLabel countBox;
        private JTextField countField;
        private JPanel panel;
        
        public Arcade() 
        {          
        	Frame frame2 = new Frame();
        	button = new JButton("Tap");
        	Timer timer = new Timer(10, new MyButtonListener1());
            timer.start();   
            
        	button.addActionListener(new MyButtonListener1());
        	
        	countBox = new JLabel("Your score is ");
        	countBox.setLocation(targetX, targetY);
        	
        	countField = new JTextField(3);
        	countField.setLocation(targetX, targetY);
        	
        	panel = new JPanel();
        	panel.setLayout(new GridLayout(3,1));
        	
        	panel.add(button);
        	panel.add(countBox);
        	panel.add(countField);
        	
        	add(panel,BorderLayout.CENTER);

        	if(remainingInGame == false)
        		message();
        }
       
        
        public Dimension getPreferredSize() 
        {
            return new Dimension(500, 500);
        }

        public void paintComponent(Graphics g) 
        {
        	//moving circle
            super.paintComponent(g);
            
            g2d = (Graphics2D) g.create();
            
            diameter = Math.min(getWidth(), getHeight());
            
            x = (getWidth() - diameter) / 2;
            y = (getHeight() - diameter) / 2;

            g2d.setColor(Color.CYAN);
            g2d.fillOval(x, y, diameter, diameter);
            g2d.setColor(Color.RED);
                  
            Point p = getPointOnCircle(movingDegree, (diameter / 2) - (innerDiameter / 2));
            g2d.fillOval(x + p.x - (int) (innerDiameter / 2), y + p.y - (int) (innerDiameter / 2), 
            			(int) innerDiameter, (int) innerDiameter);
            
            //target Point
            Point rP = getPointOnCircle(targetDegree, (diameter / 2) - (innerDiameter / 2));
            g2d.setColor(Color.GREEN);
            g2d.fillOval(rP.x - (int) (innerDiameter / 2),  rP.y - (int) (innerDiameter / 2), 
        			(int) innerDiameter, (int) innerDiameter);
            
            g2d.dispose();
        }
        
        public Point getPointOnCircle(double deg, double rad) 
        {
            int x = Math.round(getWidth() / 2);
            int y = Math.round(getHeight() / 2);

            double rads = Math.toRadians(deg - 90);
  
            int xPosy = Math.round((float) (x + Math.cos(rads) * rad));
            int yPosy = Math.round((float) (y + Math.sin(rads) * rad));

            return new Point(xPosy, yPosy);
        }
       // make it so the degrees go from 359-> 0 -> 1
       // and 1-> 0 ->359
	   public void equalize()
	   {
		   if (vel > 0)
		   {
			   if (movingDegree == 360.0)
			   {
				   movingDegree = 0;
			   }
		   }
		   
		   if ( vel < 0)
		   {
			   if(movingDegree < 0)
			   {
				   movingDegree = 359;
			   }
		   }
	   }
       
	   public void stillInGame()
	   {
		   if((movingDegree < targetDegree + 10 && movingDegree > targetDegree - 10))
		   {
			   System.out.println("Hit");			   
		   }
		   else remainingInGame = false;
	   }
	   
	   
	   private void message()
	   {
		   JOptionPane.showMessageDialog(null, "GAME OVER");
		
				System.exit(0);
			
	   }
	   // This Function is the bulk of the algorithm
        class MyButtonListener1 implements ActionListener
        {
        	 public void actionPerformed(ActionEvent e) 
             { 
        		 equalize();
        		 
        		//Whenever the button is clicked
        		if(e.getSource() == button)
        		{
	        		stillInGame();
	        		targetDegree = (float) Math.random()*361;
	        		
	        		//change direction
	        		vel = -vel;
	        		
	        		if(remainingInGame)
	        			count++;
	        		
	        		countField.setText(""+count);
        		}
        		
        		movingDegree += vel;
        		// if u missed it will pause
        		if(remainingInGame)
        		repaint();
        		if(remainingInGame == false)
        			message();	
             }
        } 
    }