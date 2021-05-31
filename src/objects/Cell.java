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
				else {
					if (Grid.getDownLeft(this) != null && Grid.getDownLeft(this).getId() == ObjectId.Empty) {
						Grid.getDownLeft(this).setId(ObjectId.Sand);
						id = ObjectId.Empty;
					}
					else if (Grid.getDownRight(this) != null && Grid.getDownRight(this).getId() == ObjectId.Empty) {
						Grid.getDownRight(this).setId(ObjectId.Sand);
						id = ObjectId.Empty;
					}
				}
			}
		}
		else if (id == ObjectId.Water) {
			if (Grid.getDown(this) != null && Grid.getDown(this).getId() == ObjectId.Empty) {
				System.out.println("a");
				Grid.getDown(this).setId(ObjectId.Water);
				setId(ObjectId.Empty);
			}
			else if (Grid.getDownLeft(this) != null && Grid.getDownLeft(this).getId() == ObjectId.Empty) {
				System.out.println("b");
				Grid.getDownLeft(this).setId(ObjectId.Water);
				setId(ObjectId.Empty);
			}
			else if (Grid.getDownRight(this) != null && Grid.getDownRight(this).getId() == ObjectId.Empty) {
				System.out.println("c");
				Grid.getDownRight(this).setId(ObjectId.Water);
				setId(ObjectId.Empty);
			}
			else {
				if (Grid.getLeft(this) != null && Grid.getLeft(this).getId() == ObjectId.Empty)
					direction = -1;
				else if (Grid.getRight(this) != null && Grid.getRight(this).getId() == ObjectId.Empty)
					direction = 1;
				else
					direction = 0;
				
				if (direction == -1) {
					System.out.println("d");
					Grid.getLeft(this).setId(ObjectId.Water);
					setId(ObjectId.Empty);
				}
				else if (direction == 1) {
					System.out.println("e");
					Grid.getRight(this).setId(ObjectId.Water);
					setId(ObjectId.Empty);
				}
			}
		}
		//		else if (id == ObjectId.Water) {
		//			if (Grid.getDown(this) != null) {
		//				if (Grid.getDown(this).getId() == ObjectId.Empty) { //if below is empty
		//					Grid.getDown(this).setId(ObjectId.Water);
		//					id = ObjectId.Empty;
		//				} 
		//				else { //if below is not empty
		//					if (Grid.getDownLeft(this) != null && Grid.getDownLeft(this).getId() == ObjectId.Empty) { //if down left is empty
		//						Grid.getDownLeft(this).setId(ObjectId.Water);
		//						id = ObjectId.Empty;
		//					}
		//					else if (Grid.getDownRight(this) != null && Grid.getDownRight(this).getId() == ObjectId.Empty) { //if down left is not empty and if down right is empty
		//						Grid.getDownRight(this).setId(ObjectId.Water);
		//						id = ObjectId.Empty;
		//					}
		//					else { //if below, down left and down right is not empty
		//						if (Grid.getLeft(this) != null || Grid.getRight(this) != null) {
		//							if (Grid.getLeft(this) != null && Grid.getLeft(this).getId() == ObjectId.Empty) {
		//								Grid.getLeft(this).setId(ObjectId.Water);
		//								id = ObjectId.Empty;
		//							}
		//							else if (Grid.getRight(this) != null && Grid.getRight(this).getId() == ObjectId.Empty) {
		//								Grid.getRight(this).setId(ObjectId.Water);
		//								id = ObjectId.Empty;
		//							}
		//						}
		//					}
		//				}
		//			}
		//		}

	}

	@Override
	public void render(Graphics g) {
		if (id == ObjectId.Sand) { 
			g.setColor(Color.yellow);
			g.fillRect(x, y, 4, 4);
		}
		else if (id == ObjectId.Water) { 
			g.setColor(Color.blue);
			g.fillRect(x, y, 4, 4);
		}
	}

	@Override
	public Rectangle getBounds() {		
		return new Rectangle(x, y, 4, 4);
	}

}
