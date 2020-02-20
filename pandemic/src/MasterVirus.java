import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

// MasterVirus Class
// Main enemy, must be defeated for game to be won
public class MasterVirus extends GameObject
{
	private int timer;
	
	// Constructor to initialize x position, y position, new ID, sets size
	public MasterVirus (int x, int y, ID id)
	{
		super (x, y, id);
		
		vel_x = 0;
		vel_y = 0;
		
		size = 50;
		timer = 0;
	}
	
	// Tick Method - Update object characteristics every refresh
	public void tick ()
	{
		x += vel_x;
		y += vel_y;
		
		if (y <= 0 || y >= Game.HEIGHT - 75)
			vel_y *= -1;
		
		if (x <= 0 || x >= Game.WIDTH - 50)
			vel_x *= -1;
		
		timer += 1;
		
		if (timer >= 500)
		{
			timer = 0;
			size += 3;
			
			x -= 3;
			y -= 3;
		}
	}

	// Render Method - Renders the object every tick
	public void render (Graphics g)
	{
		g.setColor(Color.red);
		g.fillOval(x, y, size, size);
	}

	// getBounds Method - Determines if another object is in bounds
	public Rectangle getBounds()
	{
		return new Rectangle (x, y, size, size);
	}
}
