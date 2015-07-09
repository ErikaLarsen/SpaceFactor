import java.awt.*;
import java.awt.List;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.lang.Math;


public class Main {

	public static void main(String[] args) {
		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		
		JPanel p1 = new SpaceFactor1();
		
		JFrame frame = new JFrame("SpaceFactor");
		frame.add(p1);
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}


class SpaceFactor1 extends JPanel 
{

	
	private boolean gameover = false;
	
	//Things on the screen
	private Cursor shooter = new Cursor();
	private Torpedoship torpedoships[] = new Torpedoship[2];
	private Deathstar deathstar;
	private LinkedList<Bullet> bullets = new LinkedList<Bullet>();
	javax.swing.Timer timer = new javax.swing.Timer(10, new TimerListener());
	
	private class TimerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			
			repaint();
			shooter.move();
			torpedoships[0].moveL();
			torpedoships[1].moveR();
		}
		
	}
	
	//constructor
	public SpaceFactor1()
	{
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		
		deathstar = new Deathstar(this);
		torpedoships[0] = new Torpedoship("s_images/torpedoL.png", -100);
		torpedoships[1] = new Torpedoship("s_images/torpedoR.png", width-100);
		setBackground(Color.BLACK);
		setFont(new Font("Courier", Font.BOLD, 30));
		//add listeners
		setFocusable(true);
		addKeyListener(new CursorListener());
		addKeyListener(new TriggerListener());
		timer.start();
	}
	class CursorListener extends KeyAdapter
	{
		public void keyPressed(KeyEvent e){
			switch (e.getKeyCode()){
			
				case KeyEvent.VK_W: shooter.direction = Direction.UP; break;
				case KeyEvent.VK_S: shooter.direction = Direction.DOWN; break;
				case KeyEvent.VK_D: shooter.direction = Direction.RIGHT; break;
				case KeyEvent.VK_A: shooter.direction = Direction.LEFT; break;
				case KeyEvent.VK_SPACE: bullets.addLast(new Bullet(shooter));			
			}
		}
	}
	class TriggerListener extends KeyAdapter
	{
		public void keyReleased(KeyEvent e)
		{
			switch (e.getKeyCode())
			{
				case KeyEvent.VK_W: shooter.direction = Direction.STATIC; break;
				case KeyEvent.VK_S: shooter.direction = Direction.STATIC; break;
				case KeyEvent.VK_D: shooter.direction = Direction.STATIC; break;
				case KeyEvent.VK_A: shooter.direction = Direction.STATIC; break;
			}
		}
	}
	
	/**********methods****************/
	protected void paintComponent(Graphics g)
	{        
		super.paintComponent(g);
		//Graphics2D g2 = (Graphics2D) g;
	

		
		//draw Things
		g.drawImage(shooter.getImage().getImage(), shooter.getX(), shooter.getY(), this);
		g.drawImage(deathstar.getImage().getImage(), deathstar.getX(), deathstar.getY(), this);
		g.drawImage(torpedoships[0].getImage().getImage(), torpedoships[0].getX(), torpedoships[0].getY(), this);
		g.drawImage(torpedoships[1].getImage().getImage(), torpedoships[1].getX(), torpedoships[1].getY(), this);
		
		
		g.setColor(Color.red);		
		FontMetrics fm = g.getFontMetrics();
		g.drawString(String.valueOf(torpedoships[0].getNumber()), torpedoships[0].getX()+35, torpedoships[0].getY()+35);
		g.drawString(String.valueOf(torpedoships[1].getNumber()), torpedoships[1].getX()+31, torpedoships[1].getY()+38);
		g.drawString(String.valueOf(deathstar.getNumber()), deathstar.getX()+100, deathstar.getY()+200);
		
		
		if(shooter.getNumber() >= 0)
		{
			g.drawString(String.valueOf(shooter.getNumber()), shooter.getX()+26, shooter.getY()+46);
		}
		
		
		 /*
		for(int i=0; i<things.length; i++)
			for(int j=0; j<things[i].length; j++)
				g.drawImage(things[i][j].getImage().getImage(), things[i][j].getX(), things[i][j].getY(), this);
				*/
		//draw and move bullets
		g.setColor(Color.green);
		
		ListIterator<Bullet> iterator = bullets.listIterator();
		Bullet bullet;
		while(iterator.hasNext())
		{
			bullet = iterator.next();
			g.fillRect(bullet.getX(), bullet.getY(), 4, 12);
			checkCollision(bullet);
			bullet.move();
			if (bullet.getY()<20 || bullet.isHit())
				iterator.remove();
		}
		
	}
	
	public void checkCollision(Bullet bullet)
	{
		if(bullet.getX()>deathstar.getX()+67 && (bullet.getX()<deathstar.getX()+deathstar.getImage().getIconWidth()-67
				&& bullet.getY()<deathstar.getY()+deathstar.getImage().getIconHeight()-130))
		{
			deathstar.hit(shooter.getNumber());
			bullet.hit();
		}
		
		if(bullet.getX()>torpedoships[0].getX() && bullet.getX()<torpedoships[0].getX()+torpedoships[0].getImage().getIconWidth()
				&& bullet.getY()>torpedoships[0].getY() && 
				bullet.getY()<torpedoships[0].getY()+torpedoships[0].getImage().getIconHeight()-20)
		{
			shooter.setNumber(torpedoships[0].getNumber());
			bullet.hit();
		}
		
		if(bullet.getX()>torpedoships[1].getX() && bullet.getX()<torpedoships[1].getX()+torpedoships[1].getImage().getIconWidth()
				&& bullet.getY()>torpedoships[1].getY() && 
				bullet.getY()<torpedoships[1].getY()+torpedoships[1].getImage().getIconHeight()-20)
		{
			shooter.setNumber(torpedoships[1].getNumber());
			bullet.hit();
		}
		
		
	}
	public void gameOver()
	{
		gameover = true;
	}
	
}
		
	
	

	

	

	
	
	
	











