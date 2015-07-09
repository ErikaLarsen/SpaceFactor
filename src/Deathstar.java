import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.ImageIcon;


public class Deathstar extends ScreenObject 
{
	
	private SpaceFactor1 game;
	private int starIndex = 0;
	private int hits = 0;
	private ImageIcon stars[] = new ImageIcon[6];
	private ImageIcon flashes[] = new ImageIcon[8];

	private ImageIcon hitStar1;
	private ImageIcon hitStar2;
	public Deathstar(SpaceFactor1 game)
	{
		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		this.game = game;
		number = (int)(Math.random()*100)%98+2;
		initializeStars();
		initializeFlashes();

		image = stars[0];
		
		x = width/2 - image.getIconWidth()/2;
		y = -30;
		
	}
	
	public synchronized void hit(int factor)
	{
		if(factor>0 && number%factor==0)
		{
			new Flasher().start();
			number = (int)(Math.random()*100)%98+2;
		}
		
	}
		
		
	private class Flasher extends Thread implements Runnable
	{
		private int flash;
		public void run() {
			flash(starIndex);
			hits++;
			if((hits == 3 || starIndex == 0) && starIndex < 5)
			{
				starIndex++;
				hits = 0;
			}
			image = stars[starIndex];
		}
		
		
		
		//method
		private void flash(int i)
		{
			flash = 0;
			switch(i)
			{
				case 0:
				case 1: hitStar1 = flashes[0];
						hitStar2 = flashes[1];
						break;
				case 2: hitStar1 = flashes[2];
						hitStar2 = flashes[3];
						break;
				case 3: hitStar1 = flashes[4];
						hitStar2 = flashes[5];
						break;
				case 4: hitStar1 = flashes[6];
						hitStar2 = flashes[7];	
						break;
			}
			
			
			
			while (flash <8)
			{
				if(flash%2 == 0)
				{
					image = hitStar1;
					flash++;
					try {
						Thread.sleep(80);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				else
				{
					image = hitStar2;
					flash++;
					try {
						Thread.sleep(90);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		
	}
	
	
	public void initializeStars()
	{
		stars[0] = new ImageIcon("s_images/deathstar_1.0.png");
		stars[1] = new ImageIcon("s_images/deathstar_1.4.png");
		stars[2] = new ImageIcon("s_images/deathstar_2.png");
		stars[3] = new ImageIcon("s_images/deathstar_3.png");
		stars[4] = new ImageIcon("s_images/deathstar_4.png");
		stars[5] = new ImageIcon("s_images/deathstar_5.png");
	}
	
	public void initializeFlashes()
	{
		flashes[0] = new ImageIcon("s_images/deathstar_1.1.png");
		flashes[1] = new ImageIcon("s_images/deathstar_1.2.png");
		flashes[2] = new ImageIcon("s_images/deathstar_2.2.png");
		flashes[3] = new ImageIcon("s_images/deathstar_2.1.png");
		flashes[4] = new ImageIcon("s_images/deathstar_3.1.png");
		flashes[5] = new ImageIcon("s_images/deathstar_3.2.png");
		flashes[6] = new ImageIcon("s_images/deathstar_4.png");
		flashes[7] = new ImageIcon("s_images/deathstar_3.2.png");
	}
	
	
	
	public int getNumber()
	{
		return number;
	}
	
	
	
	
	
}

