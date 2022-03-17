package snake.object;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import snake.main.Game;
import snake.main.Handler;

public class Coin1 extends Coin{
	public Coin1(int x, int y, Handler handler) {
		super(x, y, handler);
		
		value = 2;
		color = new Color(242, 242, 109);
		
	}
	
	public void render(Graphics2D g2d) {
		int width = Game.UNIT_SIZE - 5;
		int height = Game.UNIT_SIZE - 5;
		
		g2d.setStroke(new BasicStroke(1.8f));
		g2d.setColor(Color.yellow);
		g2d.fillOval(x+3, y+3 , width, height);
		g2d.drawOval(x, y, this.width, this.height);

		g2d.setColor(Color.black);
		g2d.fillOval(x+6, y+6, width-6, height-6);

	
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
		player.coinsCollected++;

		
	}
}





