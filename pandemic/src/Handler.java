import java.awt.Graphics;
import java.util.LinkedList;

// Handler Class - Groups and performs game functions on all game objects
public class Handler
{
	// LinkedList of GameObjects to be accessed in an easier way
	LinkedList <GameObject> object = new LinkedList <GameObject> ();
	
	// Tick Method - Updates the handler objects every refresh
	public void tick ()
	{
		for (int index = 0; index < object.size(); index ++)
		{
			GameObject temp_object = object.get(index);
			
			temp_object.tick();
		}
	}
	
	// Render Method - Renders the handler objects every tick
	public void render (Graphics g)
	{
		try
		{
			for (int index = 0; index < object.size(); index ++)
			{
				GameObject temp_object = object.get(index);
				temp_object.render(g);
			}
		}
		
		catch (Exception e)
		{
			
		}
	}
	
	// addObject Method - Add a new object to the handler
	public void addObject (GameObject object)
	{
		this.object.add(object);
	}
	
	// removeObject Method - Remove an object from the handler
	public void removeObject (GameObject object)
	{
		this.object.remove(object);
	}
	
	// Removes all objecrs from the handler
	public void removeAll ()
	{
		object.clear();
	}
}