import javax.swing.ImageIcon;




public class Cursor extends ScreenObject
{
	//properties
	//ImageIcon icon = new ImageIcon("s_images/shooter.png");
	
	public Direction direction; 
	
	
	public Cursor(){	
		number = -5;
		image = new ImageIcon("s_images/shooter.png");
		x = (1200 - image.getIconWidth())/2;
		y = 600-60-image.getIconHeight();
		direction = Direction.STATIC;
	}
	
	public void move(){
		if(direction==Direction.UP && y>-20)
			y -=10;
		if(direction==Direction.DOWN  && y<height-image.getIconHeight())
			y +=10;
		if(direction==Direction.LEFT && x>-20)
			x -=10;
		if(direction==Direction.RIGHT && x<width-image.getIconWidth())
			x +=10;
	}
	
	
}
