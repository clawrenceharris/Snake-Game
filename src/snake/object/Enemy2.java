package snake.object;

import java.awt.Color;
import java.awt.Rectangle;

import snake.main.GameObject;
import snake.main.Handler;
import snake.main.Tag;

public class Enemy2 extends Enemy {
	public Enemy2(int x, int y,  Handler handler) {
		super(x, y, new Color(205, 127, 50), handler);
		direction = "LEFT";
		isRemovable = false;
		handler.addObject( new OneWay(x, y, handler));
		

	}
	@Override
	public Rectangle getBounds() {
	
		return new Rectangle(x, y, width, height);
	}
	@Override
	public boolean canMove(int x, int y) {
		
		GameObject object = handler.getObject(x, y);
		if(object != null )
			
			if(object.getTag() == Tag.ONEWAY)
				if(!object.isActivated())
					return true;
		
		
		return false;
	}
	
	@Override
	public void tick() {
		handler.processEnemyCollsions(this);
		move();
	}
	
		
	

}
