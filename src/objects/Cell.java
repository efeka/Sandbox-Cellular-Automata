package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import framework.GameObject;
import framework.MouseInput;
import framework.ObjectId;

public class Cell extends GameObject {

	public int row, col;

	private int direction = -1;

	public Cell(int x, int y, int row, int col, ObjectId id) {
		super(x, y, id);
		this.row = row;
		this.col = col;
	}

	@Override
	public void tick() {
		if (id == ObjectId.Sand) {
			if (Grid.getDown(this) != null) {
				if (Grid.getDown(this).getId() == ObjectId.Empty) {
					Grid.getDown(this).setId(ObjectId.Sand);
					id = ObjectId.Empty;
				}
				else if (Grid.getDown(this).getId() == ObjectId.Water) {
					Grid.getDown(this).setId(ObjectId.Sand);
					setId(ObjectId.Water);
				}
				else {
					if (Grid.getDownLeft(this) != null && Grid.getDownLeft(this).getId() == ObjectId.Empty) {
						Grid.getDownLeft(this).setId(ObjectId.Sand);
						id = ObjectId.Empty;
					}
					else if (Grid.getDownLeft(this) != null && Grid.getDownLeft(this).getId() == ObjectId.Water) {
						Grid.getDownLeft(this).setId(ObjectId.Sand);
						setId(ObjectId.Water);
					}
					else if (Grid.getDownRight(this) != null && Grid.getDownRight(this).getId() == ObjectId.Empty) {
						Grid.getDownRight(this).setId(ObjectId.Sand);
						id = ObjectId.Empty;
					}
					else if (Grid.getDownRight(this) != null && Grid.getDownRight(this).getId() == ObjectId.Water) {
						Grid.getDownRight(this).setId(ObjectId.Sand);
						setId(ObjectId.Water);
					}
				}
			}
		}
		else if (id == ObjectId.Water) {
			if (Grid.getDown(this) != null && Grid.getDown(this).getId() == ObjectId.Empty) {
				Grid.getDown(this).setId(ObjectId.Water);
				setId(ObjectId.Empty);
			}
			else if (Grid.getDownLeft(this) != null && Grid.getDownLeft(this).getId() == ObjectId.Empty) {
				Grid.getDownLeft(this).setId(ObjectId.Water);
				setId(ObjectId.Empty);
			}
			else if (Grid.getDownRight(this) != null && Grid.getDownRight(this).getId() == ObjectId.Empty) {
				Grid.getDownRight(this).setId(ObjectId.Water);
				setId(ObjectId.Empty);
			}
			else {
				if (direction == -1) {
					if (Grid.getLeft(this) != null && Grid.getLeft(this).getId() == ObjectId.Empty) {
						Grid.getLeft(this).setId(ObjectId.Water);
						Grid.getLeft(this).setDirection(-1);
						setId(ObjectId.Empty);
						direction = 0;
					}
					else if (Grid.getRight(this) != null && Grid.getRight(this).getId() == ObjectId.Empty) {
						Grid.getRight(this).setId(ObjectId.Water);
						Grid.getRight(this).setDirection(1);
						setId(ObjectId.Empty);
						direction = 0;
					}
				}
				else if (direction == 1) {
					if (Grid.getRight(this) != null && Grid.getRight(this).getId() == ObjectId.Empty) {
						Grid.getRight(this).setId(ObjectId.Water);
						Grid.getRight(this).setDirection(1);
						setId(ObjectId.Empty);
						direction = 0;
					}
					else if (Grid.getLeft(this) != null && Grid.getLeft(this).getId() == ObjectId.Empty) {
						Grid.getLeft(this).setId(ObjectId.Water);
						Grid.getLeft(this).setDirection(-1);
						setId(ObjectId.Empty);
						direction = 0;
					}
				}
				else {
					if (Grid.getLeft(this) != null && Grid.getLeft(this).getId() == ObjectId.Empty) {
						Grid.getLeft(this).setId(ObjectId.Water);
						Grid.getLeft(this).setDirection(-1);
						setId(ObjectId.Empty);
						direction = 0;
					}
					else if (Grid.getRight(this) != null && Grid.getRight(this).getId() == ObjectId.Empty) {
						Grid.getRight(this).setId(ObjectId.Water);
						Grid.getRight(this).setDirection(1);
						setId(ObjectId.Empty);
						direction = 0;
					}
				}
			}
		}

	}

	@Override
	public void render(Graphics g) {
		if (id == ObjectId.Sand) { 
			g.setColor(Color.yellow);
			g.fillRect(x, y, 4, 4);
		}
		else if (id == ObjectId.Water) { 
			int randomColor = (int) (Math.random() * 2);
			if (randomColor == 0)
				g.setColor(Color.blue);
			else
				g.setColor(new Color(45, 45, 255));
			g.fillRect(x, y, 4, 4);
		}
		else if (id == ObjectId.Wood) {
			g.setColor(new Color(193, 60, 60));
			g.fillRect(x, y, 4, 2);
			g.setColor(new Color(170, 35, 35));
			g.fillRect(x, y + 2, 4, 2);
		}
	}

	@Override
	public Rectangle getBounds() {		
		return new Rectangle(x, y, 4, 4);
	}
	
	public void setDirection(int direction) {
		this.direction = direction;
	}

}