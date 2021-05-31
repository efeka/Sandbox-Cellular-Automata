package window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import framework.MouseInput;
import framework.ObjectId;
import objects.Grid;
import objects.ToolsMenu;

@SuppressWarnings("serial")
public class GameMain extends Canvas implements Runnable {

	public static int SCREEN_WIDTH = 700;
	public static int SCREEN_HEIGHT = 600;

	private static Window window;
	MouseInput mouse;
	
	private boolean running = false;
	private Thread thread;

	private Grid grid;
	private Handler handler;

	private void init() {
		grid = new Grid(0, 0, ObjectId.Grid);
		handler = new Handler();

		mouse = new MouseInput();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		addMouseWheelListener(mouse);

		handler.addObject(new ToolsMenu(SCREEN_WIDTH / 2, 0, 200, 200, ObjectId.Menu), Handler.MENU_LAYER);
	}

	public synchronized void start() {
		if (running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public void run() { 
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;

			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				//System.out.println("FPS: " + frames);
				frames = 0;
				updates = 0;
			}
		}
	}

	private void tick() {
		handler.tick();
		grid.tick();

		if (MouseInput.y < 50)
			window.changeCursor(Window.CURSOR_DEFAULT);
		else
			window.changeCursor(Window.CURSOR_BRUSH);
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;

		//background
		g.setColor(new Color(70, 70, 70));
		g.fillRect(0, 0, getWidth(), getHeight());

		handler.render(g);	
		grid.render(g);

		if (MouseInput.y >= 50) {
			g.setColor(new Color(255, 255, 255, 150));
			int mouseX = MouseInput.x;
			int mouseY = MouseInput.y;
			
			while(mouseX % 4 != 0)
				mouseX--;
			while(mouseY % 4 != 0)
				mouseY--;

			g.drawRect(mouseX, mouseY, MouseInput.cursorSize, MouseInput.cursorSize);
		}
		
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		window = new Window(SCREEN_WIDTH, SCREEN_HEIGHT, "Sandbox Cellular Automata", new GameMain());
	}

}
