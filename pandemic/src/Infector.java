import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

// Infector (Viroid) Class
// Spawns from virus and does damage to user
public class Infector extends GameObject
{
	private GameObject player;
	
	private Color color;
	
	// Constructor to initialize x position, y position, new ID, set size, and import image
	public Infector (int x, int y, int size, ID id, Handler handler, Color color)
	{
		super (x, y, id);
		
		// Create instace of player
		for (int index = 0; index < handler.object.size(); index ++)
		{
			if (handler.object.get(index).getID() == ID.Player)
				player = handler.object.get(index);
		}
		
		// Set size and color
		this.size = size;
		this.color = color;
	}
	
	// Tick Method - Updates the velocities every refresh
	public void tick ()
	{
		x += vel_x;
		y += vel_y;
		
		follow ();
		
		x = Game.clamp(x, 0, Game.WIDTH - 75);
		y = Game.clamp(y, 0, Game.HEIGHT - 75);
		
		if (y <= 0 || y >= Game.HEIGHT - 75)
			vel_y *= -1;
		
		if (x <= 0 || x >= Game.WIDTH - 50)
			vel_x *= -1;
	}
	
	// Sets the velocity of the infector according to the user's position
	public void follow ()
	{
		if (x > (player.getX()))
			vel_x = -1;
		
		else if (x < player.getX())
			vel_x = 1;
		
		else
			vel_x = 0;
		
		if (y > player.getY())
			vel_y = -1;
		
		else if (y < player.getY())
			vel_y = 1;
		
		else
			vel_y = 0;
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
