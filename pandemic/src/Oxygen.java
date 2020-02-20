import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

// Oxygen Class
// Allows the player to increase in size after consumption
public class Oxygen extends GameObject
{
	// Constructor to initialize x position, y position, new ID, sets size and imports image
	public Oxygen (int x, int y, ID id)
	{
		super (x, y, id);
		
		vel_x = 0;
		vel_y = 0;
	}
	
	public void tick ()
	{
	}
	
	// Render Method - Renders the object every tick
	public void render (Graphics g)
	{
		g.setColor(Color.BLUE);
		g.fillOval(x, y, 10, 10);
	}

	// getBounds Method - Determines if another object is in bounds
	public Rectangle getBounds ()
	{
		return new Rectangle (x, y, 32, 32);
	}
}