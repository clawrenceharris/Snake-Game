package snake.object;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import snake.main.Game;
import snake.main.GameObject;
import snake.main.Handler;
import snake.main.Tag;

public class Enemy1 extends Enemy {
	public Enemy1(int x, int y,  Handler handler) {
		super(x, y, Color.blue, handler);
		isRemovable = false;
		direction = "LEFT";
	}
	@Override
	public Rectangle getBounds() {
		
		return new Rectangle(x, y, width, height);
	}

	
	

}
