package snake.object;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import snake.main.GameObject;
import snake.main.Handler;
import snake.main.Tag;

public class Finish extends GameObject{
	
	public Finish(int x, int y, Handler handler) {
		super(x, y, Tag.FINISH, handler);
	}
	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics2D g2d) {
		g2d.setColor(Color.white);
		g2d.setStroke(new BasicStroke(1f));
		g2d.fillRect(x, y, width, height);
		
	}
	@Override
	public void collision(Player player) {
		player.isAtFinish = true;
			

		 		
	}
	
	
}
