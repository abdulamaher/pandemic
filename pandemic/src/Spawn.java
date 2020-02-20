import java.awt.Color;
import java.util.Random;

// Spawn Class
// Deals with the appearance of objects on the map
public class Spawn
{
	private Handler handler;
	private HUD hud;
	
	private int score_keeper = 0, energy_timer = 0;
	
	private Random rand = new Random ();
	
	// Import the HUD and handler
	public Spawn (Handler handler, HUD hud)
	{
		this.handler = handler;
		this.hud = hud;
	}
	
	// Tick Method - Updates variables after every refresh
	public void tick ()
	{
		// Increase the timers
		score_keeper += 1;
		energy_timer += 1;
		
		// If the energy timer reaches 750, determine level, and spawn ATP bar accordingly
		if (energy_timer >= 750)
		{
			energy_timer = 0;
			
			if (HUD.level <= 3)
				handler.addObject(new ATP (rand.nextInt(Game.WIDTH - 50), rand.nextInt(Game.HEIGHT - 110), ID.ATP, "Energy.png"));
			
			else if (HUD.level <= 7)
			{
				handler.addObject(new ATP (rand.nextInt(Game.WIDTH - 50), rand.nextInt(Game.HEIGHT - 110), ID.ATP, "Energy.png"));
				
				ATP atp = new ATP (rand.nextInt(1), rand.nextInt(Game.HEIGHT - 110), ID.ATP, "Energy.png");
				handler.addObject(atp);
				
				atp.setVelX(1);
			}
			
			else
			{
				handler.addObject(new ATP (rand.nextInt(Game.WIDTH - 50), rand.nextInt(Game.HEIGHT - 110), ID.ATP, "Energy.png"));
				
				ATP atp = new ATP (rand.nextInt(1), rand.nextInt(Game.HEIGHT) + 110, ID.ATP, "Energy.png");
				handler.addObject(atp);
				
				atp.setVelX(1);
				atp.setVelY(1);
			}
		}
		
		// If the score counter reaches 1000, update the level and spawn new viroid
		if (score_keeper >= 1000)
		{
			score_keeper = 0;
			hud.setLevel(hud.getLevel() + 1);
			
			for (int counter = 0; counter <= 12; counter ++)
				handler.addObject(new Oxygen (rand.nextInt(Game.WIDTH - 25), rand.nextInt(Game.HEIGHT - 25), ID.Oxygen));
			
			handler.addObject(new Infector (1100, 600, 30, ID.Infector, handler, Color.red));
			
			for (int index = 0; index < handler.object.size(); index ++)
			{
				if (handler.object.get(index).getID() == ID.Virus)
				{
					if (handler.object.get(index).alive)
						handler.addObject(new Infector (Game.WIDTH - 175, 20, 20, ID.Infector, handler, Color.magenta));
				}
			}
		}
	}
}
