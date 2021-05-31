package framework;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseInput implements MouseListener, MouseMotionListener, MouseWheelListener {
	
	public static int x = 0, y = 0;
	public static boolean leftPressed = false, rightPressed = false;
	
	private static final int MIN_CURSOR_SIZE = 4;
	private static final int MAX_CURSOR_SIZE = 100;
	public static int cursorSize = 24;
	
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1)
			leftPressed = true;
		if (e.getButton() == MouseEvent.BUTTON3)
			rightPressed = true;
	}
	
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) 
			leftPressed = false;
		if (e.getButton() == MouseEvent.BUTTON3)
			rightPressed = false;
	}

	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}
	
	public void mouseDragged(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}
	
	public boolean getPressed() { return leftPressed; }
	public void setPressed(boolean pressed) { leftPressed = pressed; }
	public int getX() { return x; }
	public int getY() { return y; }

	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		cursorSize -= e.getWheelRotation() * 12;
		if (cursorSize > MAX_CURSOR_SIZE)
			cursorSize = MAX_CURSOR_SIZE;
		if (cursorSize < MIN_CURSOR_SIZE)
			cursorSize = MIN_CURSOR_SIZE;
	}

}
