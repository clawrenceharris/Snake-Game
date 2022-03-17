package snake.object;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import snake.main.GameObject;
import snake.main.Handler;
import snake.main.Tag;

public abstract class Coin extends GameObject {
	protected int value;
	public Coin(int x, int y, Handler handler) {
		super(x, y, Tag.COIN, handler);
	}

	@Override
	public abstract Rectangle getBounds();
		
	

	@Override
	public abstract void tick();
		
	

	@Override
	public abstract void  render(Graphics2D g2d);
	
	public int getValue() {
		return value;
	}
	
	
	
	
	
	
	
	

}
