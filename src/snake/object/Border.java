package snake.object;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import snake.main.GameObject;
import snake.main.Handler;
import snake.main.Tag;

public class Border  extends GameObject{
	public Border(int x, int y, Handler handler) {
		super(x, y, Tag.BORDER, handler);
		isRemovable = false;
	}
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics2D g2d) {
		
		
		g2d.setColor(new Color(14, 32, 61));
		g2d.fill3DRect(x, y, width, height,true);
		
		
		
	}
	@Override
	public void collision(Player player) {
		player.setDead();

		
	}

}
