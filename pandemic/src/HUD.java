import java.awt.Color;
import java.awt.Graphics;

// HUD Class
// Handles the score, level, and health of the user
public class HUD
{
	private Game game;
	
	private Handler handler;
	
	// Import the game and handler
	public HUD (Game game, Handler handler)
	{
		this.game = game;
		this.handler = handler;
	}
	
	// Set the health to 100, score to 0, and level to 1
	public static int HEALTH = 100;
	private int green_val = 255;
	
	public static int score = 0, level = 1;
	
	// Set have_potion to false
	public static boolean have_potion = false;
	
	// Tick Method - Updates the health and score every refresh
	public void tick ()
	{	
		if (HEALTH > 0)
		{
			HEALTH = Game.clamp(HEALTH, 0, 100);
		
			green_val = Game.clamp(green_val, 0, 255);
			green_val = HEALTH * 2;
		
			score += 1;
		}
	}
	
	// Render Method - Renders the health bar every tick
	public void render (Graphics g)
	{
		g.setColor (Color.gray);
		g.fillRect (15, 15, 200, 32);
		
		g.setColor (new Color (75, green_val, 0));
		g.fillRect (15, 15, HEALTH * 2, 32);
		
		g.setColor (Color.white);
		g.drawRect (15, 15, 200, 32);
		
		g.drawString("Score: " + score, 15, 64);
		g.drawString("Level: " + level, 15, 80);
	}
	
	public void score (int score)
	{
		HUD.score = score;
	}
	
	public int getScore ()
	{
		return score;
	}
	
	public void setLevel (int level)
	{
		this.level = level;
	}
	
	public int getLevel ()
	{
		return level;
	}
}