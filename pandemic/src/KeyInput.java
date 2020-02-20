import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// KeyInput Class
// Deals with the movement of the user using the arrow keys
public class KeyInput extends KeyAdapter
{
	private Handler handler;
	
	private boolean [] key_down = new boolean [4];
	
	// Constructor - import handler and set key down to false
	public KeyInput (Handler handler)
	{
		this.handler = handler;
		
		for (int index = 0; index < key_down.length; index ++)
			key_down [index] = false;
	}
	
	// keyPressed Method - Activated when a key is pressed
	public void keyPressed (KeyEvent e)
	{
		// Store which key was pressed
		int key = e.getKeyCode();
		
		// Index through the game objects
		for (int index = 0; index < handler.object.size(); index ++)
		{
			GameObject temp_object = handler.object.get(index);
			
			// If the object is player, determine which key is pressed and set velocity accordingly
			if (temp_object.getID() == ID.Player)
			{
				if (key == KeyEvent.VK_UP)
				{
					temp_object.setVelY (-5);
					key_down [0] = true;
				}
				
				if (key == KeyEvent.VK_DOWN)
				{
					temp_object.setVelY (5);
					key_down [1] = true;
				}
					
				if (key == KeyEvent.VK_LEFT)
				{
					temp_object.setVelX (-5);
					key_down [2] = true;
				}
				
				if (key == KeyEvent.VK_RIGHT)
				{
					temp_object.setVelX (5);
					key_down [3] = true;
				}
				
				// If the user presses the spacebar and has Antibodies, shoot the Antibody
				if (key == KeyEvent.VK_SPACE && (temp_object.getSize() >= 35))
				{
					handler.addObject(new Antibody (temp_object.getX(), temp_object.getY(), ID.Antibody, handler));
					temp_object.changeSize();
				}
			}
		}
	}
	
	// keyReleased Method - Activated when a key is released
	public void keyReleased (KeyEvent e)
	{
		// Store which key is pressed
		int key = e.getKeyCode();
		
		// Index through the objects of the handler
		for (int index = 0; index < handler.object.size(); index ++)
		{
			GameObject temp_object = handler.object.get(index);
			
			// If the game object is player, determine which key was released and update the velocity
			if (temp_object.getID() == ID.Player)
			{
				if (key == KeyEvent.VK_UP)
					key_down [0] = false;
				
				if (key == KeyEvent.VK_DOWN)
					key_down [1] = false;
				
				if (key == KeyEvent.VK_LEFT)
					key_down [2] = false;
				
				if (key == KeyEvent.VK_RIGHT)
					key_down [3] = false;
				
				// Control Vertical Movement Velocity
				if (key_down [0] == false && key_down [1] == false)
					temp_object.setVelY(0);
				
				// Control Horizontal Movement Velocity
				if (key_down [2] == false && key_down [3] == false)
					temp_object.setVelX(0);
			}
		}
		
		if (key == KeyEvent.VK_ESCAPE)
			System.exit(1);
	}
}
