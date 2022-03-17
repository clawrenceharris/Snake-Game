package snake.object;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import snake.main.Game;
import snake.main.GameObject;
import snake.main.Handler;
import snake.main.PlayerState;
import snake.main.STATE;
import snake.main.Tag;

public class Freeze extends GameObject {
	public static int timer = 20;

	public  boolean isActivated;
	public Freeze(int x, int y, Handler handler) {
		super(x, y, Tag.FREEZE, handler);
		
		isRemovable = false;
		
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
		
		int[] xPos = {x + (width/ 2), (x + width) + 5, x + (width / 2), x - 5  };
		int[] yPos = {(y) - 5, y + height /2, (y + height) + 5, y + height / 2  };
		g2d.setStroke(new BasicStroke(0.3f));

		g2d.setColor(Color.cyan.darker());
		g2d.drawPolygon(xPos, yPos, 4);
		
		
		g2d.setColor(Color.cyan);

		g2d.setStroke(new BasicStroke(1f));
		g2d.draw3DRect(x , y , width, height, true);


		
	}


	@Override
	public void collision(Player player) {
		timer--;
		player.isFreeze = true;
		isActivated = true;
		if(timer <= 0) {
			
			if(Game.gameState == STATE.Game) {
				handler.removeObject(this);;
			}
			isActivated = false;
			player.isFreeze = false;
			
			player.isNormal = true;
			timer = 20;
			
			
		}
			
		
	}
	
}
