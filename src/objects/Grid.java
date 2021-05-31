package objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import framework.GameObject;
import framework.MouseInput;
import framework.ObjectId;
import window.GameMain;

public class Grid extends GameObject {

	public static Cell[][] cells;

	public Grid(int x, int y, ObjectId id) {
		super(x, y, id);

		int rows = GameMain.SCREEN_HEIGHT / 4;
		int cols = GameMain.SCREEN_WIDTH / 4;
		cells = new Cell[rows][cols];

		for (int i = 0; i < cols; i++) 
			for (int j = 0; j < rows; j++) 
				cells[j][i] = new Cell(i * 4, j * 4, j, i, ObjectId.Empty);
		
	}

	@Override
	public void tick() {
		for (int i = cells.length - 1; i >= 0; i--) {
			for (int j = 0; j < cells[i].length; j++) {
				cells[i][j].tick();
					
				if ((MouseInput.leftPressed || MouseInput.rightPressed) && MouseInput.y > 51) { 
					if (cells[i][j].getBounds().contains(MouseInput.x, MouseInput.y)) {
						if (MouseInput.leftPressed && cells[i][j].getId() == ObjectId.Empty)
							cells[i][j].setId(ToolsMenu.selectedTool);
						else if (MouseInput.rightPressed)
							cells[i][j].setId(ObjectId.Empty);
						
						for (int m = i; m < i + MouseInput.cursorSize / 4; m++) {
							for (int n = j; n < j + MouseInput.cursorSize / 4; n++) {
								try {
									if (MouseInput.leftPressed && cells[m][n].getId() == ObjectId.Empty)
										cells[m][n].setId(ToolsMenu.selectedTool);
									else if (MouseInput.rightPressed)
										cells[m][n].setId(ObjectId.Empty);
								} catch(ArrayIndexOutOfBoundsException ignored) {}
							}
						}
					}
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		for (int i = 0; i < cells.length; i++)
			for (int j = 0; j < cells[i].length; j++)
				cells[i][j].render(g);
	}

	public static Cell getRight(Cell cell) {
		try {
			return cells[cell.row][cell.col + 1];
		} catch(ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	public static Cell getLeft(Cell cell) {
		try {
			return cells[cell.row][cell.col - 1];
		} catch(ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	public static Cell getUp(Cell cell) {
		try {
			return cells[cell.row - 1][cell.col];
		} catch(ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	public static Cell getDown(Cell cell) {
		try {
			return cells[cell.row + 1][cell.col];
		} catch(ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	public static Cell getTopLeft(Cell cell) {
		try {
			return cells[cell.row - 1][cell.col - 1];
		} catch(ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	public static Cell getTopRight(Cell cell) {
		try {
			return cells[cell.row - 1][cell.col + 1];
		} catch(ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	public static Cell getDownLeft(Cell cell) {
		try {
			return cells[cell.row + 1][cell.col - 1];
		} catch(ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	public static Cell getDownRight(Cell cell) {
		try {
			return cells[cell.row + 1][cell.col + 1];
		} catch(ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}

}