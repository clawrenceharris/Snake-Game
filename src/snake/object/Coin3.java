package snake.object;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import snake.main.Handler;

public class Coin3 extends Coin{
	public Coin3(int x, int y, Handler handler) {
		super(x, y, handler);
		value = 10;
		speed = 5;
		
	}
	
	public void render(Graphics2D g2d) {
		g2d.setColor(Color.MAGENTA.brighter());
		g2d.drawOval(x, y, width, height);
		
		g2d.setColor(Color.white);
		g2d.drawOval(x - (width/ 2), y - (height/ 2), width * 2, height * 2);

	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	@Override
	public void tick() {
		x += speed;
		y += speed;
	}

	@Override
	public void collision(Player player) {
		// TODO Auto-generated method stub
		
	}
}