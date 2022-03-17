package snake.object;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import snake.main.GameObject;
import snake.main.Handler;
import snake.main.Tag;

public class OneWay  extends GameObject{
	public int timesEntered;
	public OneWay(int x, int y, Handler handler) {
		super(x, y, Tag.ONEWAY, handler);
		isActivated = false;
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
		g2d.setColor(new Color(205, 127, 50));
		g2d.setStroke(new BasicStroke(3f));
		
		if(!isActivated) {
			g2d.draw3DRect(x, y, width, height,true);
		}
		else {
			g2d.fill3DRect(x, y, width, height,true);
			
		}
		
		
		
		
	}
	@Override
	public void collision(Player player) {
		
		isActivated = true;
		timesEntered += 1;
		
		if(isActivated && timesEntered == 2) {
			player.isDead = true;

		}
		
	}
}
