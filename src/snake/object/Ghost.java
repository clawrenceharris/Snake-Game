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


public class Ghost extends GameObject {
	public static int timer = 50;

	public Ghost(int x, int y, Handler handler) {
		super(x, y, Tag.GHOST, handler);
		spawnTimes[0] = 100;
		spawnTimes[1] = 200;
		spawnTimes[2] = 500;
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
		g2d.setColor(Color.green);
		g2d.setStroke(new BasicStroke(1f));

		g2d.drawRect(x + 5, y + 5 , width/2, height/2);
		
		g2d.setStroke(new BasicStroke(2f));
		g2d.setColor(Color.white);
		g2d.drawRect(x , y , width, height);

		
	}



	@Override
	public void collision(Player player) {
		timer = 50;
		player.isGhost = true;
				
		
	}	
				
	
	
}