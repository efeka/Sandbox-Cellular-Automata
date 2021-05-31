package objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import framework.GameObject;
import framework.MouseInput;
import framework.ObjectId;
import window.GameMain;

public class ToolsMenu extends GameObject {
	
	private ArrayList<ObjectId> tools = new ArrayList<ObjectId>();
	private ArrayList<Rectangle> buttons = new ArrayList<Rectangle>();
	private ArrayList<Color> colors = new ArrayList<Color>();
	
	private int selectedIndex;
	public static ObjectId selectedTool;
	
	private int toolSize = 25;
	private int spacing = 10;

	public ToolsMenu(int x, int y, int width, int height, ObjectId id) {
		super(x, y, width, height, id);
		tools.add(ObjectId.Sand);
		colors.add(Color.yellow);
		
		tools.add(ObjectId.Water);
		colors.add(Color.blue);
		
		tools.add(ObjectId.Wood);
		colors.add(new Color(193, 60, 60));
		
		selectedIndex = tools.size() / 2;
		selectedTool = tools.get(selectedIndex);
		
		int leftOffset = (GameMain.SCREEN_WIDTH - (tools.size() * toolSize + (tools.size() - 1) * spacing)) / 2;
		for (int i = 0; i < tools.size(); i++) {
			buttons.add(new Rectangle(leftOffset, 10, toolSize, toolSize));
			leftOffset += (toolSize + spacing);
		}
	}

	@Override
	public void tick() {
		if (MouseInput.leftPressed) {
			for (int i = 0; i < buttons.size(); i++) {
				if (buttons.get(i).contains(MouseInput.x, MouseInput.y)) {
					selectedIndex = i;
					selectedTool = tools.get(selectedIndex);
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(0, 0, 0, 50));
		g.fillRect(0, 0, GameMain.SCREEN_WIDTH, 50);
		
		Graphics2D g2d = (Graphics2D) g;
		for (int i = 0; i < buttons.size(); i++) {
			g2d.setColor(colors.get(i));
			g2d.fill(buttons.get(i));
			
			if (i == selectedIndex) {
				g2d.setColor(Color.white);
				g2d.draw(buttons.get(i));
			}
		}
		
		g.setColor(Color.white);
		g.drawLine(0, 50, GameMain.SCREEN_WIDTH, 50);
		
		g.setColor(Color.white);
		g.setFont(new Font("", Font.PLAIN, 11));
		g.drawString("Left mouse: Draw", 5, 15);
		g.drawString("Right mouse: Erase", 5, 30);
		g.drawString("Mouse wheel: Change cursor size", 5, 45);
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}

}
