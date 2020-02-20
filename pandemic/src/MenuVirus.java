import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

// Menu Virus
// Roams around the screen behind the menu
public class MenuVirus extends GameObject
{	
	private Color color;

	private Random rand;
	
	// Constructor to initialize x position, y position, new ID, sets size
	public MenuVirus (int x, int y, ID id)
	{
		super (x, y, id);
		
		rand = new Random ();
		color = new Color (rand.nextInt (255), rand.nextInt (255), rand.nextInt (255));
		
		vel_x = rand.nextInt (5) + 1;
		vel_y = rand.nextInt (5) + 1;
		
		size = 45;
	}
	
	// Tick Method - Updates the velocities every refresh
	public void tick ()
	{
		x += vel_x;
		y += vel_y;
		
		if (y <= 0 || y >= Game.HEIGHT - 40)
			vel_y *= -1;
		
		if (x <= 0 || x >= Game.WIDTH - 16)
			vel_x *= -1;
	}
	
	// Render Method - Renders the object every tick
	public void render (Graphics g)
	{
		g.setColor(color);
		g.fillOval(x, y, size, size);
	}

	// getBounds Method - Determines if another object is in bounds
	public Rectangle getBounds()
	{
		return new Rectangle (x, y, size, size);
	}
}
