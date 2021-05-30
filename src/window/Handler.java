package window;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import framework.GameObject;

public class Handler {

	public BufferedImage level1 = null;

	public static final int BOTTOM_LAYER = 0;
	public static final int MIDDLE_LAYER = 1;
	public static final int TOP_LAYER = 2;
	public static final int MENU_LAYER = 3;

	public ArrayList<GameObject> layer1 = new ArrayList<GameObject>();
	public ArrayList<GameObject> layer2 = new ArrayList<GameObject>();
	public ArrayList<GameObject> layer3 = new ArrayList<GameObject>();
	public ArrayList<GameObject> menuLayer = new ArrayList<GameObject>();

	public Handler() {
		BufferedImageLoader loader = new BufferedImageLoader();
		//level = loader.loadImage("/level.png");
		//loadImageLevel(level);
	}

	public void loadImageLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();

		for (int xx = 0; xx < h; xx++) {
			for (int yy = 0; yy < w; yy++) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
//				if (red == 160 && green == 171 && blue == 177)
//					addObject(new Rock(xx * 32, yy * 32, this, ObjectId.Rock), BOTTOM_LAYER);
			}
		}
	}

	public void tick() {
		for (int i = 0; i < layer2.size(); i++) 
			layer2.get(i).tick();
		for (int i = 0; i < layer1.size(); i++) 
			layer1.get(i).tick();
		for (int i = 0; i < layer3.size(); i++) 
			layer3.get(i).tick();
		for (int i = 0; i < menuLayer.size(); i++) 
			menuLayer.get(i).tick();
		
	}

	public void render(Graphics g) {
		for (int i = 0; i < layer1.size(); i++) 
			layer1.get(i).render(g);
		for (int i = 0; i < layer2.size(); i++) 
			layer2.get(i).render(g);
		for (int i = 0; i < layer3.size(); i++) 
			layer3.get(i).render(g);
		for (int i = 0; i < menuLayer.size(); i++) 
			menuLayer.get(i).render(g);
	}

	public void addObject(GameObject object, int layer) {
		switch(layer) {
		case BOTTOM_LAYER:
			layer1.add(object);
			break;
		case MIDDLE_LAYER:
			layer2.add(object);
			break;
		case TOP_LAYER:
			layer3.add(object);
			break;
		case MENU_LAYER:
			menuLayer.add(object);
			break;
		}
	}
	
	public void addObject(GameObject object, int layer, int index) {
		switch(layer) {
		case BOTTOM_LAYER:
			layer1.add(index, object);
			break;
		case MIDDLE_LAYER:
			layer2.add(index, object);
			break;
		case TOP_LAYER:
			layer3.add(index, object);
			break;
		case MENU_LAYER:
			menuLayer.add(index, object);
			break;
		}
	}

	public void removeObject(GameObject object) {
		if (layer2.contains(object)) {
			layer2.remove(object);
			return;
		}

		if (layer1.contains(object)) {
			layer1.remove(object);
			return;
		}

		if (layer3.contains(object)) {
			layer3.remove(object);
			return;
		}
		
		if (menuLayer.contains(object)) {
			menuLayer.remove(object);
			return;
		}
	}

}
