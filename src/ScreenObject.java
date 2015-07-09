import java.util.Random;

import javax.swing.ImageIcon;

import java.awt.*;


public class ScreenObject {
	//screen width and height
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	protected double width = screenSize.getWidth();
	protected double height = screenSize.getHeight();
	protected boolean hit = false;
	protected int number; //number associated with object
	protected int x, y; //location of image
	protected ImageIcon image; //image
	//protected Random random; //a random generator
	//protected int nx, ny; // location of the number of object
	
	//constructors
	public ScreenObject() {}
	
	/*********methods************/
	public void move(){}
	//public boolean checkIfHit(){}
	//public doWhenHit(){}
	
	
	//getters
	public int getX()
	{
		return x;
	}	
	public int getY()
	{
		return y;
	}
	public ImageIcon getImage(){
		return image;
	}
	public boolean isHit()
	{
		return hit;
	}
	public int getNumber()
	{
		return number;
	}
	
	
	
	//setters
	public void setX(int x)
	{
		this.x = x;
	}
	public void setY(int y)
	{
		this.y = y;
	}
	public void hit()
	{
		hit = true;
	}
	public void setNumber(int number)
	{
		this.number = number;
	}

}
