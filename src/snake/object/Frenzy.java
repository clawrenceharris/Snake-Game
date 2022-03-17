package snake.object;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import snake.main.GameObject;
import snake.main.Handler;
import snake.main.PlayerState;
import snake.main.Tag;

public class Frenzy extends GameObject {
	public static int timer = 100;
	
	public Frenzy(int x, int y, Handler handler) {
		super(x, y, Tag.FRENZY, handler);
	
	}
	
	
	public static void reset() {
		timer = 100;
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
		Random rand = new Random();
		
		Color[] colors = {Color.CYAN, Color.PINK, Color.ORANGE, Color.WHITE, Color.RED, Color.GREEN, Color.YELLOW};
		
		int[] xPos = {x + (width/ 2), (x + width) + 5, x + (width / 2), x - 5  };
		int[] yPos = {(y) - 5, y + height /2, (y + height) + 5, y + height / 2  };
		g2d.setStroke(new BasicStroke(2f));

		g2d.setColor(Color.white);
		g2d.drawPolygon(xPos, yPos, 4);

		g2d.drawRect(x, y, width, height);
		

		g2d.setColor(colors[rand.nextInt(colors.length)]);

		g2d.fillRect(x , y  , width, height);
		
		g2d.setColor(Color.white);

		g2d.setStroke(new BasicStroke(1f));
		g2d.drawRect(x , y , width, height);
		
	}
	
	public static void drawBar(Graphics g2d){
			Random rand = new Random();
			g2d.drawRect(200, 15, 200, 30);		

			g2d.setColor(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
			g2d.fillRect(200, 15, 2 * Frenzy.timer, 30);

		
	}
	
	public void collision(Player player) {
		timer = 100;
		isActivated = true;
		Tag objectsToRemove[] = {Tag.FREEZE, Tag.MAGNET, Tag.GHOST};
		handler.removeAllObjects(objectsToRemove);
		player.isNormal = false;
		player.isFrenzy = true;
		player.isGhost = false;
		
			

	
		
	}
			
			
	
	
}
