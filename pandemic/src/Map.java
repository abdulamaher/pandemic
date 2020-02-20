import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

// Map Class
// Creates and draws a map on the screen
public class Map extends GameObject
{
	protected char [][] map;
	
	private int rows, columns, width, height;
	
	// Constructor - Sets the number of rows, columns, width, height, and id of the map
	public Map (int rows, int columns, int width, int height, ID id)
	{
		// Call the GameObject constructor
		super (rows, columns, width, height, id);
		
		this.rows = rows;
		this.columns = columns;
		
		this.width = width;
		this.height = height;
	}
	
	public void tick ()
	{
		
	}
	
	// Render Method - Renders the map every tick with borders and doors
	// Double nested loops were not used due to laggy player movement
	// darkGray boxes = borders
	public void render (Graphics g)
	{
		for (int c = 0; c < columns; c ++)
		{
			g.setColor(Color.red);
			g.drawRect(c * width, 0 * height, width, height);
		}
		
		for (int c = 0; c < columns; c ++)
		{
			g.setColor(Color.red);
			g.drawRect(c * width, 1 * height, width, height);
		}
		
		for (int c = 0; c < columns; c ++)
		{
			g.setColor(Color.red);
			g.drawRect(c * width, 2 * height, width, height);
		}
		
		for (int c = 0; c < columns; c ++)
		{
			if (getMap(3, c) != 'D')
			{
				g.setColor(Color.darkGray);
				g.fillRect(c * width, 3 * height, width, height);
			}
			
			g.setColor(Color.red);
			g.drawRect(c * width, 3 * height, width, height);
		}
		
		g.setColor(Color.darkGray);
		g.fillRect(40 * width, 4 * height, width, height);
		
		for (int c = 0; c < columns; c ++)
		{	
			g.setColor(Color.red);
			g.drawRect(c * width, 4 * height, width, height);
		}
		
		g.setColor(Color.darkGray);
		g.fillRect(40 * width, 5 * height, width, height);
		
		for (int c = 0; c < columns; c ++)
		{
			g.setColor(Color.red);
			g.drawRect(c * width, 5 * height, width, height);
		}
		
		g.setColor(Color.darkGray);
		g.fillRect(40 * width, 6 * height, width, height);
		
		for (int c = 0; c < columns; c ++)
		{
			g.setColor(Color.red);
			g.drawRect(c * width, 6 * height, width, height);
		}
		
		g.setColor(Color.darkGray);
		g.fillRect(40 * width, 7 * height, width, height);
		
		for (int c = 0; c < columns; c ++)
		{
			if (getMap(7, c) == 'O')
			{
				g.setColor(Color.green);
				g.fillRect(c * width, 7 * height, width, height);
			}
			
			else if (c >= 40 && c <= 47)
			{
				g.setColor(Color.darkGray);
				g.fillRect(c * width, 7 * height, width, height);
			}
			
			g.setColor(Color.red);
			g.drawRect(c * width, 7 * height, width, height);
		}
		
		for (int c = 0; c < columns; c ++)
		{
			g.setColor(Color.red);
			g.drawRect(c * width, 8 * height, width, height);
		}
		
		for (int c = 0; c < columns; c ++)
		{
			g.setColor(Color.red);
			g.drawRect(c * width, 9 * height, width, height);
		}
		
		for (int c = 0; c < columns; c ++)
		{
			g.setColor(Color.red);
			g.drawRect(c * width, 10 * height, width, height);
		}
		
		for (int c = 0; c < columns; c ++)
		{
			g.setColor(Color.red);
			g.drawRect(c * width, 11 * height, width, height);
		}
		
		for (int c = 0; c < columns; c ++)
		{
			g.setColor(Color.red);
			g.drawRect(c * width, 12 * height, width, height);
		}
		
		for (int c = 0; c < columns; c ++)
		{
			g.setColor(Color.red);
			g.drawRect(c * width, 13 * height, width, height);
		}
		
		for (int c = 0; c < columns; c ++)
		{
			g.setColor(Color.red);
			g.drawRect(c * width, 14 * height, width, height);
		}
		
		for (int c = 0; c < columns; c ++)
		{
			g.setColor(Color.red);
			g.drawRect(c * width, 15 * height, width, height);
		}
		
		for (int c = 0; c < columns; c ++)
		{
			g.setColor(Color.red);
			g.drawRect(c * width, 16 * height, width, height);
		}
		
		for (int c = 0; c < columns; c ++)
		{
			g.setColor(Color.red);
			g.drawRect(c * width, 17 * height, width, height);
		}
		
		for (int c = 0; c < columns; c ++)
		{
			g.setColor(Color.red);
			g.drawRect(c * width, 18 * height, width, height);
		}
		
		for (int c = 0; c < columns; c ++)
		{
			g.setColor(Color.red);
			g.drawRect(c * width, 19 * height, width, height);
		}
		
		for (int c = 0; c < columns; c ++)
		{
			if (getMap(20, c) != 'D')
			{
				g.setColor(Color.darkGray);
				g.fillRect(c * width, 20 * height, width, height);
			}
			
			g.setColor(Color.red);
			g.drawRect(c * width, 20 * height, width, height);
		}
		
		for (int c = 0; c < columns; c ++)
		{
			g.setColor(Color.red);
			g.drawRect(c * width, 21 * height, width, height);
		}
		
		for (int c = 0; c < columns; c ++)
		{
			g.setColor(Color.red);
			g.drawRect(c * width, 22 * height, width, height);
		}
		
		for (int c = 0; c < columns; c ++)
		{
			g.setColor(Color.red);
			g.drawRect(c * width, 23 * height, width, height);
		}
		
		for (int c = 0; c < columns; c ++)
		{
			g.setColor(Color.red);
			g.drawRect(c * width, 24 * height, width, height);
		}
		
		for (int c = 0; c < columns; c ++)
		{
			g.setColor(Color.red);
			g.drawRect(c * width, 25 * height, width, height);
		}
		
		for (int c = 0; c < columns; c ++)
		{
			g.setColor(Color.red);
			g.drawRect(c * width, 26 * height, width, height);
		}
	}

	public Rectangle getBounds()
	{
		return null;
	}
}
