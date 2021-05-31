package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import framework.GameObject;
import framework.ObjectId;
import window.GameMain;

public class ToolsMenu extends GameObject {
	
	private ArrayList<ObjectId> tools = new ArrayList<ObjectId>();
	private ArrayList<Rectangle> buttons = new ArrayList<Rectangle>();
	
	private int toolSize = 25;
	private int spacing = 10;

	public ToolsMenu(int x, int y, int width, int height, ObjectId id) {
		super(x, y, width, height, id);
		tools.add(ObjectId.Sand);
		tools.add(ObjectId.Water);
		tools.add(ObjectId.Wood);
		tools.add(ObjectId.Empty);
		
		int leftOffset = (GameMain.SCREEN_WIDTH - (tools.size() * toolSize + (tools.size() - 1) * spacing)) / 2;
		for (int i = 0; i < tools.size(); i++) {
			buttons.add(new Rectangle(leftOffset, 10, toolSize, toolSize));
			leftOffset += (toolSize + spacing);
		}
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.drawLine(GameMain.SCREEN_WIDTH / 2, 0, GameMain.SCREEN_WIDTH / 2, 10);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.blue);
		for (int i = 0; i < buttons.size(); i++)
			g2d.fill(buttons.get(i));
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}

}
