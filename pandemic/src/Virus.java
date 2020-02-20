import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

// Virus Class
// Shoots viroids / infectors at the user and does damage when in contact
// Guards the lever
public class Virus extends GameObject
{	
	// Constructor to initialize x position, y position, new ID, sets size and imports image
	public Virus (int x, int y, ID id)
	{
		super (x, y, id);
		
		vel_x = 0;
		vel_y = 0;
		
		size = 65;
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
		g.setColor(Color.magenta);
		g.fillOval(x, y, size, size);
	}

	// getBounds Method - Determines if another object is in bounds
	public Rectangle getBounds()
	{
		return new Rectangle (x, y, size, size);
	}
}