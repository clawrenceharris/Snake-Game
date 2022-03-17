package snake.main;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class Renderer {
	
	public Renderer() {
		
	}
	public void render(ArrayList<GameObject> objects, Graphics2D g2d) {
		for(GameObject object : objects) {
			object.render(g2d);
		}
	}
}
