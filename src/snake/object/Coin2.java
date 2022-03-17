package snake.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import snake.main.Handler;

public class Coin2 extends Coin{
	
	public Coin2(int x, int y, Handler handler) {
		super(x, y, handler);
		value = 5;
		
	}
	
	public void render(Graphics2D g2d) {
		g2d.setColor(Color.blue);
		g2d.drawOval(x, y, width, height);

	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void collision(Player player) {
		// TODO Auto-generated method stub
		
	}

	
	
}
