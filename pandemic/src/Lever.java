import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

// Lever Class
// Allows the user to access the potion when the lever is pulled down
public class Lever extends GameObject
{
	private BufferedImage image;
	
	// Constructor to initialize x position, y position, new ID, sets size and imports image
	public Lever (int x, int y, ID id, String image)
	{
		super (x, y, id);

		size = 25;
		
		vel_x = 0;
		vel_y = 0;
		
		try 
		{
			this.image = ImageIO.read(new File (image));
		} 
		
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	// Tick Method - Updates the position every refresh
	public void tick ()
	{
		x += vel_x;
		y += vel_y;
	}
	
	// Render Method - Renders the object every tick
	public void render (Graphics g)
	{
		g.drawImage(image, x, y, null);
	}

	// getBounds Method - Determines if another object is in bounds
	public Rectangle getBounds()
	{
		return new Rectangle (x, y, size, size);
	}
}
