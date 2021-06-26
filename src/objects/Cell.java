package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import framework.GameObject;
import framework.ObjectId;

public class Cell extends GameObject {

	public int row, col;

	private int direction = -1;
	
	private int randomizeColor = (int) (Math.random() * 4);

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
					if (Grid.getLeft(this) != null && Grid.getLeft(this).getId() == ObjectId.Empty && Grid.getRight(this) != null && Grid.getRight(this).getId() == ObjectId.Empty) {
						int randomDir = (int) (Math.random() * 2);
						if (randomDir == 3) {
							Grid.getLeft(this).setId(ObjectId.Water);
							Grid.getLeft(this).setDirection(-1);
							setId(ObjectId.Empty);
							direction = 0;
						}
						else {
							Grid.getRight(this).setId(ObjectId.Water);
							Grid.getRight(this).setDirection(1);
							setId(ObjectId.Empty);
							direction = 0;
						}
					}
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
		else if (id == ObjectId.Lava) {
			ArrayList<Cell> woodNeighbors = Grid.getNeighbors(this, ObjectId.Wood);
			for (int i = 0; i < woodNeighbors.size(); i++) {
				int burnChance = (int) (Math.random() * 20);
				if (burnChance == 0)
					Grid.cells[woodNeighbors.get(i).row][woodNeighbors.get(i).col].setId(ObjectId.Empty);
				if (burnChance == 1)
					setId(ObjectId.Empty);
			}

			ArrayList<Cell> waterNeighbors = Grid.getNeighbors(this, ObjectId.Water);
			for (int i = 0; i < waterNeighbors.size(); i++) {
				int rockChance = (int) (Math.random() * 10);
				if (rockChance == 0) {
					setId(ObjectId.Empty);
					Grid.cells[waterNeighbors.get(i).row][waterNeighbors.get(i).col].setId(ObjectId.Rock);
				}
			}

			ArrayList<Cell> gunpowderNeighbors = Grid.getNeighbors(this, ObjectId.Gunpowder);
			for (int i = 0; i < gunpowderNeighbors.size(); i++) {
				int igniteChanceAbove = (int) (Math.random() * 10);
				int igniteChanceBelow = (int) (Math.random() * 20);
				if (gunpowderNeighbors.get(i).row < row) {
					if (igniteChanceAbove < 3) {
						Grid.cells[gunpowderNeighbors.get(i).row][gunpowderNeighbors.get(i).col].setId(ObjectId.Lava);
					}
				}
				else {
					if (igniteChanceBelow == 0) {
						Grid.cells[gunpowderNeighbors.get(i).row][gunpowderNeighbors.get(i).col].setId(ObjectId.Lava);
					}
				}
				
				int burnoutChance = (int) (Math.random() * 10);
				if (burnoutChance == 0)
					setId(ObjectId.Empty);
			}

			if (id == ObjectId.Lava) {
				if (Grid.getDown(this) != null && Grid.getDown(this).getId() == ObjectId.Empty) {
					Grid.getDown(this).setId(ObjectId.Lava);
					setId(ObjectId.Empty);
				}
				else if (Grid.getDownLeft(this) != null && Grid.getDownLeft(this).getId() == ObjectId.Empty) {
					Grid.getDownLeft(this).setId(ObjectId.Lava);
					setId(ObjectId.Empty);
				}
				else if (Grid.getDownRight(this) != null && Grid.getDownRight(this).getId() == ObjectId.Empty) {
					Grid.getDownRight(this).setId(ObjectId.Lava);
					setId(ObjectId.Empty);
				}
				else {
					if (direction == -1) {
						if (Grid.getLeft(this) != null && Grid.getLeft(this).getId() == ObjectId.Empty) {
							Grid.getLeft(this).setId(ObjectId.Lava);
							Grid.getLeft(this).setDirection(-1);
							setId(ObjectId.Empty);
							direction = 0;
						}
						else if (Grid.getRight(this) != null && Grid.getRight(this).getId() == ObjectId.Empty) {
							Grid.getRight(this).setId(ObjectId.Lava);
							Grid.getRight(this).setDirection(1);
							setId(ObjectId.Empty);
							direction = 0;
						}
					}
					else if (direction == 1) {
						if (Grid.getRight(this) != null && Grid.getRight(this).getId() == ObjectId.Empty) {
							Grid.getRight(this).setId(ObjectId.Lava);
							Grid.getRight(this).setDirection(1);
							setId(ObjectId.Empty);
							direction = 0;
						}
						else if (Grid.getLeft(this) != null && Grid.getLeft(this).getId() == ObjectId.Empty) {
							Grid.getLeft(this).setId(ObjectId.Lava);
							Grid.getLeft(this).setDirection(-1);
							setId(ObjectId.Empty);
							direction = 0;
						}
					}
					else {
						if (Grid.getLeft(this) != null && Grid.getLeft(this).getId() == ObjectId.Empty) {
							Grid.getLeft(this).setId(ObjectId.Lava);
							Grid.getLeft(this).setDirection(-1);
							setId(ObjectId.Empty);
							direction = 0;
						}
						else if (Grid.getRight(this) != null && Grid.getRight(this).getId() == ObjectId.Empty) {
							Grid.getRight(this).setId(ObjectId.Lava);
							Grid.getRight(this).setDirection(1);
							setId(ObjectId.Empty);
							direction = 0;
						}
					}
				}
			}
		}
		else if (id == ObjectId.Gunpowder) {
			if (Grid.getDown(this) != null) {
				if (Grid.getDown(this).getId() == ObjectId.Empty) {
					Grid.getDown(this).setId(ObjectId.Gunpowder);
					id = ObjectId.Empty;
				}
				else if (Grid.getDown(this).getId() == ObjectId.Water) {
					Grid.getDown(this).setId(ObjectId.Gunpowder);
					setId(ObjectId.Water);
				}
				else {
					if (Grid.getDownLeft(this) != null && Grid.getDownLeft(this).getId() == ObjectId.Empty) {
						Grid.getDownLeft(this).setId(ObjectId.Gunpowder);
						id = ObjectId.Empty;
					}
					else if (Grid.getDownLeft(this) != null && Grid.getDownLeft(this).getId() == ObjectId.Water) {
						Grid.getDownLeft(this).setId(ObjectId.Gunpowder);
						setId(ObjectId.Water);
					}
					else if (Grid.getDownRight(this) != null && Grid.getDownRight(this).getId() == ObjectId.Empty) {
						Grid.getDownRight(this).setId(ObjectId.Gunpowder);
						id = ObjectId.Empty;
					}
					else if (Grid.getDownRight(this) != null && Grid.getDownRight(this).getId() == ObjectId.Water) {
						Grid.getDownRight(this).setId(ObjectId.Gunpowder);
						setId(ObjectId.Water);
					}
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		if (id == ObjectId.Sand) { 
			g.setColor(new Color(220, 220, 180));
			g.fillRect(x, y, 4, 4);
			g.setColor(new Color(221, 229, 0));
			g.fillRect(x, y, 2, 2);
			g.fillRect(x + 2, y + 2, 2, 2);
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
			g.setColor(new Color(170, 60, 60));
			g.fillRect(x, y, 4, 2);
			g.setColor(new Color(130, 35, 35));
			g.fillRect(x, y + 2, 4, 2);
		}
		else if (id == ObjectId.Lava) {
			int randomColor = (int) (Math.random() * 2);
			if (randomColor == 0)
				g.setColor(new Color(255, 0, 0));
			else
				g.setColor(new Color(250, 90, 0));
			g.fillRect(x, y, 4, 4);
		}
		else if (id == ObjectId.Rock) {
			switch (randomizeColor) {
			case 0:
				g.setColor(new Color(129, 130, 150));
				break;
			case 1:
				g.setColor(new Color(98, 103, 114));
				break;
			case 2:
				g.setColor(new Color(78, 83, 91));	
				break;
			case 3:
				g.setColor(new Color(91, 91, 91));
				break;
			}
			g.fillRect(x, y, 4, 4);
		}
		else if (id == ObjectId.Gunpowder) {
			g.setColor(Color.DARK_GRAY);
			g.fillRect(x, y, 4, 4);
			g.setColor(Color.black);
			g.fillRect(x, y, 2, 2);
			g.fillRect(x + 2, y + 2, 2, 2);
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