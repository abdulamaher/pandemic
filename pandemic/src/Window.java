import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

// Window Class
// Creates new window to display program
public class Window extends Canvas
{
	private static final long serialVersionUID = 1L;

	// Constructor - Set the width, height, title of window
	public Window (int width, int height, String title, Game game)
	{
		JFrame frame = new JFrame (title);
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		// Add the game to the window
		frame.add(game);
		
		frame.setVisible(true);
		
		// Start the game
		game.start();
	}
}