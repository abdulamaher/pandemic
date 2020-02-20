import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

// Menu Class
// Allows user to navigate the program
public class Menu implements MouseListener
{
	private Game game;
	private Handler handler;
	
	private Random rand = new Random ();
	
	private Font arial = new Font ("arial", 3, 70);
	private	Font arial2 = new Font ("arial", 1, 45);

	private	Color maroon = new Color (180, 0, 0);
	
	// Constructor - Import the game and handler
	public Menu (Game game, Handler handler)
	{
		this.game = game;
		this.handler = handler;
	}
	
	// mouseClicked Method - Activates when mouse is pressed and released
	public void mouseClicked (MouseEvent e)
	{
		// Determine position of cursor
		int mouse_x = e.getX(), mouse_y = e.getY();
		
		// If game is in the menu, determine which button is pressed and perform appropriate action
		if (game.getState() == 2)
		{
			if (mouseOver(mouse_x, mouse_y, 500, 300, 200, 64))
			{				
				game.changeState(1);
				game.initializeGame();
			}
			
			else if (mouseOver(mouse_x, mouse_y, 500, 400, 200, 64))
			{
				game.changeState(3);
				menuBackground ();
			}
			
			else if (mouseOver(mouse_x, mouse_y, 500, 500, 200, 64))
				System.exit(0);
		}
		
		else if (game.getState() == 3)
		{
			if (mouseOver(mouse_x, mouse_y, 500, 550, 200, 64))
			{
				game.changeState(2);
				
				menuBackground ();
			}
		}
		
		else if (game.getState() == 4)
		{				
			if (mouseOver(mouse_x, mouse_y, 475, 350, 275, 64))
			{
				game.changeState(2);
				menuBackground ();
			}
			
			else if (mouseOver(mouse_x, mouse_y, 475, 450, 275, 64))
			{
				game.changeState(1);
				game.initializeGame();
			}
		}
	}
	
	// mouseOver Method - Determines if the cursor is within the passed parameters
	private boolean mouseOver (int m_x, int m_y, int x, int y, int width, int height)
	{
		boolean mouseOver = false;
		
		if (m_x > x && m_x < x + width)
		{
			if (m_y > y && m_y < y + height)
				mouseOver = true;
		}
		
		else
			mouseOver = false;
		
		return mouseOver;
	}
	
	// menuBackground Method - Adds Menu Objects to the handler
	public void menuBackground ()
	{
		handler.removeAll();
		
		handler.addObject(new Map (Game.WIDTH, Game.HEIGHT, 25, 25, ID.Map));
		
		for (int counter = 0; counter < 8; counter ++)
			handler.addObject(new MenuVirus (rand.nextInt(Game.WIDTH - 50), rand.nextInt(Game.HEIGHT - 50), ID.MenuVirus));
	}
	
	public void tick ()
	{
		
	}
	
	// Render Method - Renders the menu screen every tick
	public void render (Graphics g)
	{	
		if (game.getState() == 2)
		{	
			g.setColor(maroon);
			g.fillRect(250, 50, 765, 100);
			
			g.setColor(Color.white);
			g.drawRect(250, 50, 765, 100);
			
			g.setColor(maroon);
			g.fillRect(500, 300, 200, 64);
			
			g.setColor(Color.white);
			g.drawRect(500, 300, 200, 64);
			
			g.setColor(maroon);
			g.fillRect(500, 400, 200, 64);
			
			g.setColor(Color.white);
			g.drawRect(500, 400, 200, 64);
			
			g.setColor(maroon);
			g.fillRect(500, 500, 200, 64);
			
			g.setColor(Color.white);
			g.drawRect(500, 500, 200, 64);
			
			g.setFont(arial);
			g.setColor(Color.white);
			
			g.drawString("Pandemic Virus Game", 260, 120);
			
			g.setFont(arial2);
			
			g.drawString("Play", 555, 345);
			g.drawString("Help", 555, 445);
			g.drawString("Quit", 555, 545);
		}
		
		else if (game.getState() == 3)
		{
			Font arial = new Font ("arial", 1, 55);
			Font arial2 = new Font ("arial", 1, 45);
			
			g.setFont(arial);
			g.setColor(Color.white);
			
			g.setColor(maroon);
			g.fillRect(365, 70, 485, 64);
			
			g.setColor(Color.white);
			g.drawRect(364, 70, 485, 64);
			
			g.drawString("Game Instuctions", 378, 120);
			
			g.setColor(maroon);
			g.fillRect(500, 550, 200, 64);
			
			g.setColor(Color.white);
			g.drawRect(500, 550, 200, 64);
			
			g.setFont(arial2);
			g.drawString("Back", 550, 597);
			
			g.setColor(maroon);
			g.fillRect(100, 170, 1000, 290);
			
			g.setColor(Color.white);
			g.drawRect(100, 170, 1000, 290);
			
			g.setFont(new Font ("arial", 1, 25));
			g.drawString("A pandemic has gripped the world. You must destroy the virus to save humanity.", 110, 195);
			g.drawString("Move around the map using the arrow keys.", 145, 240);
			g.drawString("Press the spacebar to shoot antibodies.", 145, 275);
			g.drawString("Consume the oxygen atoms to gain size and antibodies.", 145, 310);
			g.drawString("Consume the ATP molecules (chocolate bar) to gain health and size.", 145, 345);
			g.drawString("Pull the lever to access the potion in order to destroy the virus.", 145, 380);
			
			g.drawString("Beware of the virus atoms which will decrease your health", 110, 435);
		}
		
		else if (game.getState() == 4)
		{			
			Font arial = new Font ("arial", 1, 55);
			Font arial2 = new Font ("arial", 1, 45);
			
			g.setColor(maroon);
			g.fillRect(460, 70, 325, 64);
			
			g.setColor(Color.white);
			g.drawRect(460, 70, 325, 64);
			
			g.setFont(arial);
			g.drawString("Game Over", 475, 120);
			
			g.setColor(maroon);
			g.fillRect(475, 350, 275, 64);
			
			g.setColor(Color.white);
			g.drawRect(475, 350, 275, 64);
			
			g.setColor(Color.blue);
			g.fillRect(375, 190, 500, 100);
			
			g.setColor(Color.white);
			g.drawRect(375, 190, 500, 100);
			
			g.setFont(arial);
			g.drawString("Score: " + HUD.score, 490, 260);
			
			g.setFont(arial2);
			g.drawString("Menu", 555, 397);
			
			g.setColor(maroon);
			g.fillRect(475, 450, 275, 64);
			
			g.setColor(Color.white);
			g.drawRect(475, 450, 275, 64);
			
			g.drawString("Play Again", 500, 497);
		}
	}

	public void mouseEntered(MouseEvent e)
	{	
	}

	public void mouseExited(MouseEvent e)
	{		
	}

	public void mousePressed(MouseEvent e)
	{	
	}
	
	public void mouseReleased (MouseEvent e)
	{	
	}
}
