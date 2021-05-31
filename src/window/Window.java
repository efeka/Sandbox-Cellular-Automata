package window;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Window {
	
	private JFrame frame;
	
	public static final int CURSOR_BRUSH = 0;
	public static final int CURSOR_DEFAULT = 1;

	public Window(int w, int h, String title, GameMain game) {
		game.setPreferredSize(new Dimension(w, h));
		game.setMaximumSize(new Dimension(w, h));
		game.setMinimumSize(new Dimension(w, h));
		
		frame = new JFrame(title);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		frame.setCursor(frame.getToolkit().createCustomCursor(new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "null"));
		
		game.start();
	}
	
	public void changeCursor(int cursorId) {
		if (cursorId == CURSOR_DEFAULT)
			frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		else if (cursorId == CURSOR_BRUSH)
			frame.setCursor(frame.getToolkit().createCustomCursor(new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "null"));
	}
}

