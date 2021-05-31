package objects;

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
		cells[50][50].setId(ObjectId.Water);
	}

	@Override
	public void tick() {
		for (int i = cells.length - 1; i >= 0; i--) {
			for (int j = 0; j < cells[i].length; j++) {
				cells[i][j].tick();

				if (MouseInput.leftPressed || MouseInput.rightPressed) { 
					if (cells[i][j].getBounds().contains(MouseInput.x, MouseInput.y)) {
						ObjectId selectedId;
						if (MouseInput.leftPressed)
							selectedId = ObjectId.Sand;
						else
							selectedId = ObjectId.Water;
						
						cells[i][j].setId(selectedId);

//						if (cells[i][j].getId() == selectedId) {
//							Cell[] neighbors = new Cell[8];
//							neighbors[0] = getLeft(cells[i][j]);
//							neighbors[1] = getRight(cells[i][j]);
//							neighbors[2] = getUp(cells[i][j]);
//							neighbors[3] = getDown(cells[i][j]);
//							neighbors[4] = getTopLeft(cells[i][j]);
//							neighbors[5] = getTopRight(cells[i][j]);
//							neighbors[6] = getDownLeft(cells[i][j]);
//							neighbors[7] = getDownRight(cells[i][j]);
//
//							for (int k = 0; k < neighbors.length; k++) 
//								if (neighbors[k] != null)
//									neighbors[k].setId(selectedId);
//						}	
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
