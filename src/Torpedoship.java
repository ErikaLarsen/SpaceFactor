import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.ImageIcon;


public class Torpedoship extends ScreenObject {
	
	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	int width = gd.getDisplayMode().getWidth();
	int height = gd.getDisplayMode().getHeight();
	
	
	public Torpedoship(String file, int xPosition)
	{
		x = xPosition;
		y = 100;
		image = new ImageIcon(file);
		number = (int)(Math.random()*10)%(10-2)+2;
	}
	

	
	public void moveR()
	{
		x -= 4;
		y += 1;
		if(x < -image.getIconWidth()-50)
		{
			number = (int)(Math.random()*10)%(10-2)+2;
			x = width-100;
			y = 100;
		}
	}
	
	public void moveL()
	{
		x += 4;
		y += 1;
		if(x > width+image.getIconWidth()+50)
		{
			number = (int)(Math.random()*10)%(10-2)+2;
			x = -100;
			y = 100;
		}
	}
	
	
	

	
	
	
	
	
}
