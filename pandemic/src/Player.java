import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

// Player Class
// Handles all functions of the user
public class Player extends GameObject
{
	private Handler handler;
	
	private GameObject map;
	
	private Color color;
	
	// Constructor to initialize x position, y position, new ID, sets size and imports handler
	public Player (int x, int y, ID id, Handler handler)
	{
		super (x, y, id);
		this.handler = handler;
		
		for (int index = 0; index < handler.object.size(); index ++)
			if (handler.object.get(index).getID() == ID.Map)
				map = handler.object.get(index);
		
		size = 20;
		
		// Set color and doors_open to false
		color = Color.white;
		doors_open = false;
	}
	
	// getBounds Method - Determines if another object is in bounds
	public Rectangle getBounds ()
	{
		return new Rectangle (x, y, size, size );
	}
	 
	// Tick Method - Update position and size after refresh
	public void tick ()
	{
		x += vel_x;
		y += vel_y;
		
		border ();
		
		collision();
	}
	
	// border Mehtod - Restricts player move across borders
	public void border ()
	{		
		// If player is not within the doors / channel proteins, restrict movement accordingly
		if (x < 375 || x > 425)
		{
			if (y > 80 && y < 500)
			{
				x = Game.clamp(x, 0, (Game.WIDTH - 5) - size);
				y = Game.clamp(y, 100, 500 - size);
			}
			
			else if (y < 80)
			{
				x = Game.clamp(x, 0, (Game.WIDTH - 5) - size);
				y = Game.clamp(y, 0, 75 - size);
			}
			
			else if (y > 500)
			{
				x = Game.clamp(x, 0, (Game.WIDTH - 5) - size);
				y = Game.clamp(y, 525, (Game.HEIGHT - 29) - size);
			}
		}
		
		// If player is to the left of the cell holding the potion, restrict the movement accordingly
		if (x < 1000)
		{
			if (y < 200 && y > 75)
			{
				x = Game.clamp(x, 0, 1000 - size);
				y = Game.clamp(y, 0, Game.HEIGHT);
			}
		}
		
		else
		{
			if (y < 200 && y > 75)
			{
				if (doors_open == false)
				{
					x = Game.clamp(x, 0, Game.WIDTH - (size - 25));
					y = Game.clamp(y, 200, Game.HEIGHT - (size - 25));
				}
				
				else
				{
					x = Game.clamp(x, 1025, (Game.WIDTH - 5) - size);
					y = Game.clamp(y, 0, (Game.HEIGHT - 29) - size);
				}
			}
		}
		
		x = Game.clamp(x, 0, (Game.WIDTH - 5) - size);
		y = Game.clamp(y, 0, (Game.HEIGHT - 29) - size);
	}
	
	// collision Method - Determines if a game object comes in contact with the player and performs appropriate functions
	public void collision ()
	{
		// Index through the game objects
		for (int index = 0; index < handler.object.size(); index ++)
		{
			GameObject temp_object = handler.object.get(index);
			
			// If player collides with the virus or infector, decrease health
			if ((temp_object.getID() == ID.Virus || temp_object.getID() == ID.MasterVirus) && getBounds().intersects(temp_object.getBounds()))
				HUD.HEALTH -= 5;
			
			else if (temp_object.getID() == ID.Infector && getBounds().intersects(temp_object.getBounds()))
			{
				HUD.HEALTH -= 5;
				handler.removeObject(temp_object);
			}
			
			// If player meets the oxygen atoms, increase player's size
			else if (temp_object.getID() == ID.Oxygen && getBounds().intersects(temp_object.getBounds()) && size <= 250)
			{
				size += 1;
				handler.removeObject(temp_object);
			}
			
			// If player encounters the lever, open the doors
			else if (temp_object.getID() == ID.Button && getBounds().intersects(temp_object.getBounds()))
			{
				handler.removeObject(temp_object);
				handler.addObject(new Lever (Game.WIDTH - 50, 25, ID.Button, "Lever2.png"));
			
				for (int counter = 41; counter <= 47; counter ++)
					map.map [7][counter] = 'O';
 				
				doors_open = true;
			}
			
			// If the player meets the potion, allow it to attack the master virus
			else if (temp_object.getID() == ID.Potion && getBounds().intersects(temp_object.getBounds()))
			{
				HUD.have_potion = true;
				handler.removeObject(temp_object);
				
				color = Color.green;
			}
			
			// If the player meets the ATP bar, increase its health and size
			else if (temp_object.getID() == ID.ATP && getBounds().intersects(temp_object.getBounds()))
			{
				handler.removeObject(temp_object);
				
				HUD.HEALTH += 2;
				size += 5;
			}
		}
	}
	
	// Render Method - Renders the object every tick
	public void render (Graphics g)
	{	
		g.setColor(color);		
		g.fillOval(x, y, size, size);
	}
}