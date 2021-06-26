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
	private ArrayList<Color> colors1 = new ArrayList<Color>();
	private ArrayList<Color> colors2 = new ArrayList<Color>();
	
	private int selectedIndex;
	public static ObjectId selectedTool;
	
	private int toolSize = 25;
	private int spacing = 10;

	public ToolsMenu(int x, int y, int width, int height, ObjectId id) {
		super(x, y, width, height, id);
		tools.add(ObjectId.Sand);
		colors1.add(new Color(220, 220, 180));
		colors2.add(new Color(221, 229, 0));
		
		tools.add(ObjectId.Water);
		colors1.add(new Color(45, 45, 255));
		colors2.add(Color.blue);
		
		tools.add(ObjectId.Wood);
		colors1.add(new Color(170, 60, 60));
		colors2.add(new Color(130, 35, 35));
		
		tools.add(ObjectId.Lava);
		colors1.add(new Color(250, 90, 0));
		colors2.add(Color.red);
		
		tools.add(ObjectId.Rock);
		colors1.add(new Color(98, 103, 114));
		colors2.add(new Color(78, 83, 91));
		
		tools.add(ObjectId.Gunpowder);
		colors1.add(Color.DARK_GRAY);
		colors2.add(Color.black);
		
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
			g2d.setColor(colors1.get(i));
			g2d.fill(new Rectangle(buttons.get(i).x, buttons.get(i).y + toolSize / 2, toolSize / 2, toolSize / 2));
			g2d.fill(new Rectangle(buttons.get(i).x + toolSize / 2, buttons.get(i).y, toolSize / 2, toolSize / 2));
			g2d.setColor(colors2.get(i));
			g2d.fill(new Rectangle(buttons.get(i).x, buttons.get(i).y, toolSize / 2, toolSize / 2));
			g2d.fill(new Rectangle(buttons.get(i).x + toolSize / 2, buttons.get(i).y + toolSize / 2, toolSize / 2, toolSize / 2));
			
			if (i == selectedIndex) {
				g2d.setColor(Color.white);
				g2d.draw(new Rectangle(buttons.get(i).x, buttons.get(i).y, toolSize - 1, toolSize - 1));
			}
		}
		
		g.setColor(Color.white);
		g.drawLine(0, 50, GameMain.SCREEN_WIDTH, 50);
		
		g.setColor(Color.white);
		g.setFont(new Font("", Font.PLAIN, 11));
		g.drawString("Left mouse: Draw", 5, 15);
		g.drawString("Right mouse: Erase", 5, 30);
		g.drawString("Mouse wheel: Change cursor size", 5, 45);
		
		g.drawString(selectedTool + "", GameMain.SCREEN_WIDTH / 2 - selectedTool.toString().length() * 3, 48);
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}

}
