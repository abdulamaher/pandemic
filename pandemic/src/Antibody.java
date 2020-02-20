import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

// AntiBody Class
// Shoots down viroid and does damage to the viruses
public class Antibody extends GameObject
{
	private Handler handler;

	// Constructor to initialize x position, y position, size, new ID, and import the handler
	public Antibody (int x, int y, ID id, Handler handler)
	{
		super (x, y, id);
		
		this.handler = handler;
		
		size = 20;
		
		vel_x = 10;
		vel_y = 0;
	}
	
	// Tick Method - Update the velocity and determine if there contact
	public void tick ()
	{
		x += vel_x;
		y += vel_y;
		
		collision();
	}
	
	// Collision Method - Determines if there is contact with a game object
	//					  and performs appropriate functions
	public void collision ()
	{
		// Index through all the handler object
		for (int index = 0; index < handler.object.size(); index ++)
		{
			GameObject temp_object = handler.object.get(index);
			
			// If the object is the infector / viroid, remove the viroid
			if (temp_object.getID() == ID.Infector && getBounds().intersects(temp_object.getBounds()))
			{
				handler.removeObject(temp_object);
				remove();
			}
			
			// If the object is the virus, do damage
			else if (temp_object.getID() == ID.Virus && getBounds().intersects(temp_object.getBounds()))
			{
				temp_object.changeSize();
				remove();
			}
			
			// If the object is the master virus, determine if user has potion, and then do damage
			else if (temp_object.getID() == ID.MasterVirus && getBounds().intersects(temp_object.getBounds()))
			{
				if (HUD.have_potion == true)
				{
					temp_object.changeSize ();
					remove ();
				}
				
				else
					remove();
			}
		}
	}
	
	// remove Method - Removes the Antibody from the screen
	public void remove ()
	{
		for (int index = 0; index < handler.object.size(); index ++)
		{
			GameObject temp_object = handler.object.get(index);
			
			if (temp_object.getID() == ID.Antibody)
			{
				handler.removeObject(temp_object);
			}
		}
	}
	
	// Render Method - Renders the Antibody on the screen every tick
	public void render (Graphics g)
	{
		if (HUD.have_potion == false)
			g.setColor(Color.white);
		
		else
			g.setColor(Color.green);
		
		g.fillOval(x, y, size, size);
	}

	// getBounds - Determines if another object is in bounds
	public Rectangle getBounds()
	{
		return new Rectangle (x, y, size, size);
	}
}