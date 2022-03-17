package snake.object;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import snake.main.Game;
import snake.main.GameObject;
import snake.main.Handler;
import snake.main.STATE;
import snake.main.Tag;

public class Apple extends GameObject{

	public Apple(int x, int y, Handler handler) {
		super(x, y, Tag.APPLE, handler);
		isRemovable = true;
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
		
		
		g2d.setColor(Color.red);
		g2d.fill3DRect(x, y, width, height, true);
		
	}



	@Override
	public void collision(Player player) {
		player.applesEaten++;
		if(Game.gameState == STATE.Game)
			player.length += 4;
		else
			player.length++;
		
		
	}



	


	

	
	
}
