package snake.object;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import snake.main.GameObject;
import snake.main.Handler;
import snake.main.PlayerState;
import snake.main.Tag;


public class Magnet extends GameObject{
	
	public static int timer = 100;
	
	public Magnet(int x, int y,  Handler handler) {
		super(x, y, Tag.MAGNET, handler);
		spawnTimes[0] = 100;
		spawnTimes[1] = 200;
		spawnTimes[2] = 500;
	}
	
	public void render(Graphics2D g2d) {

		g2d.setColor(Color.red);
		g2d.fillOval(x + 7, y + 6, width /2, height /2);
		g2d.setStroke(new BasicStroke(1));
		g2d.setColor(Color.white);
		g2d.drawOval(x, y, width, height);
		
		g2d.setStroke(new BasicStroke(1));
		g2d.setColor(Color.white);
		g2d.drawOval(x - 5, y - 5, width + 10, height + 10);
		
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
		isActivated = true;
		timer = 100;
		player.isMagnet = true;
		
		
		
		
		
	}
}
