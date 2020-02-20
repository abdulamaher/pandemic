/* Authors: Abdula Maher, Qusai Rehab
 
 * Project: Summative Project
 * Description: A culminating assignment designed to incorporate all aspects of Java programming learned in 12U
 
 * The user attempts to defeat a master virus that gets larger through time by obtaining a potion
 * 
 * For: Mr. Jay, ICS4U
 
 * Date Finished: Monday, June 18, 2018
*/

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.Random;

// Main Game Class
// Create and instantiates the window and game
public class Game extends Canvas implements Runnable
{	
	private static final long serialVersionUID = 1L;

	// Static variables defining the windows size
	public static final int WIDTH = 1200, HEIGHT = 700;
	
	// Private variables to be used with the game
	private Thread thread;
	private boolean running = false;
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	
	private Random rand;
	
	// Initialization of game state - changes over the course of the program
	// 1 = Play, 2 = Menu, 3 = Help, 4 = Game Over Screen
	private int game_state = 2;
	
	// Constructor for new Game object
	public Game ()
	{		
		// Create new Handler
		// Will incorporate and perform functions on all the game objects
		handler = new Handler ();
		
		// Create new Menu
		// Opening Screen for the user with options
		menu = new Menu (this, handler);

		// Add mouse and key listeners to the program
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);
		
		// Create new 1200 x 700 window to display the program
		new Window (WIDTH, HEIGHT, "Pandemic", this);
		
		// Create new instances of a HUD and Spawner
		// HUD manages the health, level, and the score
		// Spawner handles appearance of game objects
		hud = new HUD (this, handler);
		spawner = new Spawn (handler, hud);
		
		rand = new Random ();
		
		// If the game is in play mode, initialize the game
		if (game_state == 1)
			initializeGame ();
		
		// If the game is in menu mode, add the menu and menu screen viruses to the handler
		else
		{
			handler.addObject(new Map (WIDTH, HEIGHT, 25, 25, ID.Map));
			
			for (int counter = 0; counter < 8; counter ++)
				handler.addObject(new MenuVirus (rand.nextInt(WIDTH - 50), rand.nextInt(HEIGHT - 50), ID.MenuVirus));
		}
	}
	
	// Start Method - Starts new thread and initializes running to true
	public synchronized void start ()
	{
		thread = new Thread (this);
		thread.start();
		
		running = true;
	}
	
	// Stop Method - Stops thread and sets running to false
	public synchronized void stop ()
	{
		try
		{
			thread.join();
			running = false;
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	// Imported Game Loop
	// Checks whether enough time has passed to refresh the game (1 / 60 seconds)
	// And checks whether enough time has passed to update refresh the FPS counter (1 second)
	public void run ()
	{
		this.requestFocus();
		
		long last_time = System.nanoTime();
		
		double amount_of_ticks = 60.0;
		double ns =  1000000000 / amount_of_ticks;
		double delta = 0;
		
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		while (running)
		{
			long now = System.nanoTime();
			delta += (now - last_time) / ns;
			
			last_time = now;
			
			while (delta >= 1)
			{
				tick ();
				delta --;
			}
			
			if (running)
				render ();
			
			frames += 1;
			
			if (System.currentTimeMillis() - timer > 1000)
			{
				timer += 1000;
				
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		
		stop ();
	}
	
	// Tick Method - Called every second to update game objects
	private void tick ()
	{
		// Update the handler
		handler.tick();
		
		// If the game is in play mode
		if (game_state == 1)
		{
			// Update the HUD and Spawner
			hud.tick();	
			spawner.tick();
			
			// If the user is not alive
			if (HUD.HEALTH <= 0)
			{
				// Set the health to 100
				HUD.HEALTH = 100;
				
				// Put the game into 'Game Over' mode
				game_state = 4;
				
				// Remove all objects from the handler
				handler.removeAll();
				handler.addObject(new Map (Game.WIDTH, Game.HEIGHT, 25, 25, ID.Map));
			}
			
			// If the user is alive determine if the master virus is alive
			// and change the game mode if needed
			else
			{
				for (int index = 0; index < handler.object.size(); index ++)
				{
					if (handler.object.get(index).getID() == ID.MasterVirus)
					{
						if (handler.object.get(index).alive == false)
						{
							game_state = 4;
						
							handler.removeAll();
							handler.addObject(new Map (Game.WIDTH, Game.HEIGHT, 25, 25, ID.Map));
						}
					}
				}
			}
		}
		
		// If the game is in menu mode, update the menu
		else if (game_state == 2)
			menu.tick();
	}
	
	// Render Method - Renders the game objects on the screen every tick
	private void render ()
	{
		BufferStrategy bufferSt = this.getBufferStrategy();
		
		if (bufferSt == null)
		{
			this.createBufferStrategy(3);
			
			return;
		}
		
		Graphics g = bufferSt.getDrawGraphics();
		
		// Create black screen
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		// Render all objects according to game state
		handler.render(g);
		
		if (game_state == 1)
			hud.render (g);
		
		else if (game_state == 2 || game_state == 3 || game_state == 4)
			menu.render(g);
		
		g.dispose();
		bufferSt.show();
	}
	
	// Clamp Method - Determines if variables is within the boundaries and returns appropriate position
	public static int clamp (int variable, int minimum, int maximum)
	{
		if (variable >= maximum)
			return variable = maximum;
		
		else if (variable <= minimum)
			return variable = minimum;
		
		else
			return variable;
	}
	
	// initializeGame Method - Initializes all of the game objects
	public void initializeGame ()
	{
		// Remove all elements from the handler 
		handler.removeAll();
		
		// Add new game objects
		handler.addObject(new Map (Game.WIDTH, Game.HEIGHT, 25, 25, ID.Map));
		
		handler.addObject(new Player (0, 100, ID.Player, handler));
		
		handler.addObject(new MasterVirus (1100, 600, ID.MasterVirus));
		handler.addObject(new Virus (Game.WIDTH - 175, 7, ID.Virus));
		
		handler.addObject(new Lever (Game.WIDTH - 50, 25, ID.Button, "Lever1.png"));
		handler.addObject(new Potion (Game.WIDTH - 50, 110, ID.Potion, "Potion.png"));
		
		for (int counter = 0; counter < 20; counter ++)
			handler.addObject(new Oxygen (rand.nextInt(Game.WIDTH), rand.nextInt (Game.HEIGHT), ID.Oxygen));
	}
	
	// changeState Method - Changes the game state
	public void changeState (int state)
	{
		game_state = state;
	}
	
	// getState Method - Returns the game state
	public int getState ()
	{
		return game_state;
	}
	
	// Main Method - Create new Game
	public static void main (String [] args)
	{
		new Game ();
	}
}