
public class Bullet extends ScreenObject{
	private Cursor shooter;
	
	public Bullet(Cursor shooter)
	{
		x = shooter.getX() + shooter.getImage().getIconWidth()/2 - 2;
		y = shooter.getY() + shooter.getImage().getIconHeight() - 70;
		this.shooter = shooter;
	}
	
	public void setShooter(Cursor shooter)
	{
		this.shooter = shooter;
	}
	
	public void move()
	{
		y -= 3;
	}


}
