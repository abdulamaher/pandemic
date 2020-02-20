import java.awt.Graphics;
import java.awt.Rectangle;

// Abstract Game Object Class
// Groups all the game objects under one class
public abstract class GameObject
{
	// Variables regarding the object
	protected int x, y;
	protected ID id;
	
	protected int vel_x, vel_y;
	
	protected int size;
	
	public char map [][];
	
	protected boolean alive, doors_open;
	
	// Game Object constructor
	public GameObject (int x, int y, ID id)
	{
		this.x = x;
		this.y = y;
		
		this.id = id;
		
		alive = true;
	}
	
	// Map Constructor
	public GameObject (int rows, int columns, int width, int height, ID id)
	{
		this.id = id;
		
		map = new char [rows][columns];
		
		for (int r = 0; r < rows; r ++)
		{
			for (int c = 0; c < columns; c ++)
			{
				if (r == 3 || r == 20 || (r == 4 && c == 40) ||(r == 5 && c == 40) || (r == 6 && c == 40) || (r == 7 && c == 40) || (r == 7 && (c > 40 && c < 47)))
				{
					if (c == 15 || c == 16 || c == 17)
						map[r][c] = 'D';
		     
					else
						map[r][c] = 'W';
				}
		    
				else
					map[r][c] = ' ';
			}
		}
	}
	
	// Abstract tick () and render () methods
	public abstract void tick ();	
	public abstract void render (Graphics g);
	
	// Use the Rectangle class to handle collision between player and virus
	public abstract Rectangle getBounds();
	
	public void setX (int x)
	{
		this.x = x;
	}
	
	public void setY (int y)
	{
		this.y = y;
	}
	
	public int getX ()
	{
		return x;
	}
	
	public int getY ()
	{
		return y;
	}
	
	public void setID (ID id)
	{
		this.id = id;
	}
	
	public ID getID ()
	{
		return id;
	}
	
	public void setVelX (int vel_x)
	{
		this.vel_x = vel_x;
	}
	
	public void setVelY (int vel_y)
	{
		this.vel_y = vel_y;
	}
	
	public int getVelX ()
	{
		return vel_x;
	}
	
	public int getVelY ()
	{
		return vel_y;
	}
	
	public int getSize ()
	{
		return size;
	}
	
	public void changeSize ()
	{
		size -= 3;
		
		if (size <= 5)
			alive = false;
	}
	
	public char getMap (int row, int column)
	{
		return map [row] [column];
	}
}