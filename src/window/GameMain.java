package window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import framework.MouseInput;

@SuppressWarnings("serial")
public class GameMain extends Canvas implements Runnable {
	
	public static int SCREEN_WIDTH = 700;
	public static int SCREEN_HEIGHT = 600;
	
	private boolean running = false;
	private Thread thread;
	
	private void init() {
//		handler = new Handler();

		MouseInput mouse = new MouseInput();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
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
		//handler.tick();
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
		g.setColor(new Color(30, 30, 60));
		g.fillRect(0, 0, getWidth(), getHeight());

		//handler.render(g);			

		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		new Window(SCREEN_WIDTH, SCREEN_HEIGHT, "Sandbox Cellular Automata", new GameMain());
	}

}
